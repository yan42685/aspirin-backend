package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.common.ApplicationSwtich;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.converter.StudentConverter;
import com.hubu.aspirin.enums.CourseTypeEnum;
import com.hubu.aspirin.enums.ElectiveStatusEnum;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.hubu.aspirin.mapper.GradeMapper;
import com.hubu.aspirin.mapper.StudentCourseDetailMapper;
import com.hubu.aspirin.mapper.StudentMapper;
import com.hubu.aspirin.model.dto.*;
import com.hubu.aspirin.model.entity.Grade;
import com.hubu.aspirin.model.entity.Student;
import com.hubu.aspirin.model.entity.StudentCourseDetail;
import com.hubu.aspirin.service.CourseDetailService;
import com.hubu.aspirin.service.GradeService;
import com.hubu.aspirin.service.StudentCourseDetailService;
import com.hubu.aspirin.service.StudentService;
import com.hubu.aspirin.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CourseDetailService courseDetailService;
    @Autowired
    StudentCourseDetailService studentCourseDetailService;
    @Autowired
    StudentCourseDetailMapper studentCourseDetailMapper;
    @Autowired
    GradeMapper gradeMapper;
    @Autowired
    GradeService gradeService;

    @Override
    public StudentDTO getInformation() {
        Student student = getCurrentStudent();
        String number = student.getNumber();
        return getDtoByNumber(number);
    }

    @Override
    public StudentDTO updateInformation(StudentModifiableDTO dto) {
        Student currentStudent = getCurrentStudent();
        StudentConverter.INSTANCE.updateEntityFromModifiableDto(dto, currentStudent);
        updateById(currentStudent);
        return getDtoByNumber(currentStudent.getNumber());
    }

    @Override
    public StudentDTO getDtoByNumber(String number) {
        return studentMapper.getDtoByNumber(number);
    }

    @Override
    public IPage<StudentDTO> pageByQueryDto(Integer current, Integer size, StudentQueryDTO info) {
        Page<StudentDTO> page = new Page<>(current, size);
        return studentMapper.page(page, info);
    }

    @Override
    public List<CourseDetailDTO> availableCourseDetailList(Integer semester, CourseTypeEnum courseType) {
        if (!ApplicationSwtich.electEnabled) {
            throw new KnownException(ExceptionEnum.FUNCTION_DISABLED);
        }
        Student student = getCurrentStudent();
        String specialtyNumber = student.getSpecialtyNumber();
        return courseDetailService.studentAvailableCourseList(specialtyNumber, semester, courseType);
    }

    @Override
    public List<CourseDetailDTO> getCourseSchedule(Integer semester) {
        Student student = getCurrentStudent();
        String number = student.getNumber();
        return studentCourseDetailService.studentCourseSchedule(number, semester);
    }

    @Override
    public List<CourseDetailDTO> electCourse(Long courseDetailId) {
        Student student = getCurrentStudent();
        String studentNumber = student.getNumber();
        String courseNumber = courseDetailService.getCourseNumberById(courseDetailId);
        Integer studentSemester = student.getSemester();

        LambdaQueryWrapper<Grade> queryWrapper = new LambdaQueryWrapper<Grade>()
                .eq(Grade::getStudentNumber, studentNumber)
                .eq(Grade::getCourseNumber, courseNumber);
        Grade gradeRecord = gradeService.getOne(queryWrapper);
        // 检查是否已选该课程
        if (gradeRecord != null) {
            throw new KnownException(ExceptionEnum.COURSE_HAS_BEEN_CHOSEN);
        }
        CourseDetailDTO courseDetailDTO = courseDetailService.getDtoById(courseDetailId);
        Integer courseSemester = courseDetailDTO.getSemester();
        // 检查学期是否匹配
        if (!studentSemester.equals(courseSemester)) {
            throw new KnownException(ExceptionEnum.COURSE_STUDENT_SEMESTER_MISMATCH);
        }
        // 检查时间是否没有冲突
        Integer dayOfTheWeek = courseDetailDTO.getDayOfTheWeek();
        Integer schedulingTime = courseDetailDTO.getSchedulingTime();
        CourseDetailDTO sameTimeElective = studentCourseDetailMapper.OneByStudentNumberAndDayOfTheWeekAndSchedulingTime(studentNumber, dayOfTheWeek, schedulingTime);
        if (sameTimeElective != null) {
            throw new KnownException(ExceptionEnum.STUDENT_NOT_AVAILABLE);
        }

        // 添加选课记录
        StudentCourseDetail electiveRecord = new StudentCourseDetail()
                .setStatus(ElectiveStatusEnum.CHOSEN)
                .setCourseDetailId(courseDetailId)
                .setStudentNumber(studentNumber);
        studentCourseDetailService.save(electiveRecord);

        // 添加空成绩记录
        Grade grade = new Grade()
                .setStudentNumber(studentNumber)
                .setCourseNumber(courseNumber);
        gradeService.save(grade);
        return getCourseSchedule(studentSemester);
    }

    @Override
    public List<CourseDetailDTO> dropCourse(Long courseDetailId) {
        Student student = getCurrentStudent();
        String number = student.getNumber();
        Integer semester = student.getSemester();

        QueryWrapper<StudentCourseDetail> queryWrapper = new QueryWrapper<StudentCourseDetail>()
                .eq("course_detail_id", courseDetailId)
                .eq("student_number", number)
                .eq("status", ElectiveStatusEnum.CHOSEN);
        StudentCourseDetail previous = studentCourseDetailService.getOne(queryWrapper);
        if (previous == null) {
            throw new KnownException(ExceptionEnum.DROP_COURSE_NOT_FOUND);
        }
        studentCourseDetailService.removeById(previous);

        StudentCourseDetail current = new StudentCourseDetail();
        current.setCourseDetailId(courseDetailId)
                .setStudentNumber(number)
                .setStatus(ElectiveStatusEnum.DROPPED);
        // 用保存是为了出现多条退课记录
        studentCourseDetailService.save(current);

        // 删除成绩记录
        String courseNumber = courseDetailService.getCourseNumberById(courseDetailId);
        LambdaQueryWrapper<Grade> lambdaQueryWrapper = new LambdaQueryWrapper<Grade>()
                .eq(Grade::getStudentNumber, student.getNumber())
                .eq(Grade::getCourseNumber, courseNumber);
        gradeService.remove(lambdaQueryWrapper);

        return getCourseSchedule(semester);
    }

    @Override
    public IPage<CourseDropDTO> pageCourseDropRecord(Integer current, Integer size) {
        Student student = getCurrentStudent();
        String number = student.getNumber();
        Integer semester = student.getSemester();
        return studentCourseDetailService.pageCourseDropRecord(current, size, number, semester);
    }

    @Override
    public IPage<GradeDTO> pageGrade(Integer current, Integer size, Integer semester) {
        Page<GradeDTO> page = new Page<>(current, size);
        String number = getCurrentStudent().getNumber();
        return gradeMapper.pageDtoByStudentNumberAndSemester(page, number, semester, ElectiveStatusEnum.CHOSEN.getValue());
    }

    private Student getCurrentStudent() {
        String username = UserUtils.getCurrentUsername();
        QueryWrapper<Student> queryWrapper = new QueryWrapper<Student>().eq("username", username);
        return getOne(queryWrapper);
    }

}
