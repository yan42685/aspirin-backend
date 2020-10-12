package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.enums.CourseTypeEnum;
import com.hubu.aspirin.enums.ElectiveStatusEnum;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.hubu.aspirin.mapper.StudentCourseDetailMapper;
import com.hubu.aspirin.mapper.StudentMapper;
import com.hubu.aspirin.model.dto.CourseDetailDTO;
import com.hubu.aspirin.model.dto.CourseDropDTO;
import com.hubu.aspirin.model.dto.StudentDTO;
import com.hubu.aspirin.model.dto.StudentQueryDTO;
import com.hubu.aspirin.model.entity.CourseDetail;
import com.hubu.aspirin.model.entity.Student;
import com.hubu.aspirin.model.entity.StudentCourseDetail;
import com.hubu.aspirin.service.CourseDetailService;
import com.hubu.aspirin.service.StudentCourseDetailService;
import com.hubu.aspirin.service.StudentService;
import com.hubu.aspirin.util.UserUtils;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    public StudentDTO getInformation() {
        Student student = getCurrentStudent();
        String number = student.getNumber();
        return getDtoByNumber(number);
    }

    @Override
    public StudentDTO getDtoByNumber(String number) {
        return studentMapper.getDtoByNumber(number);
    }

    @Override
    public StudentDTO getDtoById(Long id) {
        return studentMapper.getDtoById(id);
    }

    @Override
    public IPage<StudentDTO> pageByQueryDto(Integer current, Integer size, StudentQueryDTO info) {
        Page<StudentDTO> page = new Page<>(current, size);
        return studentMapper.page(page, info);
    }

    @Override
    public List<CourseDetailDTO> availableCourseDetailList(Integer semester, CourseTypeEnum courseType) {
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

    // TODO: 选课前检测冲突，避免时间冲突
    @Override
    public List<CourseDetailDTO> electCourse(Long courseDetailId) {
        Student student = getCurrentStudent();
        String number = student.getNumber();
        Integer semester = student.getSemester();
        QueryWrapper<StudentCourseDetail> queryWrapper = new QueryWrapper<StudentCourseDetail>()
                .eq("course_detail_id", courseDetailId)
                .eq("student_number", number)
                .eq("status", ElectiveStatusEnum.CHOSEN);
        StudentCourseDetail previous = studentCourseDetailService.getOne(queryWrapper);
        if (previous != null) {
            throw new KnownException(ExceptionEnum.COURSE_HAS_BEEN_CHOSEN);
        }

        StudentCourseDetail studentCourseDetail = new StudentCourseDetail();
        studentCourseDetail
                .setStatus(ElectiveStatusEnum.CHOSEN)
                .setCourseDetailId(courseDetailId)
                .setStudentNumber(number);
        studentCourseDetailService.save(studentCourseDetail);
        return getCourseSchedule(semester);
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

        return getCourseSchedule(semester);
    }

    @Override
    public IPage<CourseDropDTO> pageCourseDropRecord(Integer current, Integer size) {
        Student student = getCurrentStudent();
        String number = student.getNumber();
        Integer semester = student.getSemester();
        return studentCourseDetailService.pageCourseDropRecord(current, size, number, semester);
    }

    private Student getCurrentStudent() {
        String username = UserUtils.getCurrentUsername();
        QueryWrapper<Student> queryWrapper = new QueryWrapper<Student>().eq("username", username);
        return getOne(queryWrapper);
    }

}
