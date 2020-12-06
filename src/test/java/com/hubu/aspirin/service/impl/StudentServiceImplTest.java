package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.enums.ElectiveStatusEnum;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.hubu.aspirin.mapper.GradeMapper;
import com.hubu.aspirin.mapper.StudentCourseDetailMapper;
import com.hubu.aspirin.mapper.StudentMapper;
import com.hubu.aspirin.model.dto.CourseDetailDTO;
import com.hubu.aspirin.model.entity.Grade;
import com.hubu.aspirin.model.entity.StudentCourseDetail;
import com.hubu.aspirin.service.CourseDetailService;
import com.hubu.aspirin.service.GradeService;
import com.hubu.aspirin.service.StudentCourseDetailService;
import com.hubu.aspirin.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class StudentServiceImplTest {
    @Test
    void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }

    @Autowired
    StudentService studentService;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CourseDetailService courseDetailService;
    @Autowired
    StudentCourseDetailService studentCourseDetailService;
    @Autowired
    GradeMapper gradeMapper;
    @Autowired
    GradeService gradeService;
    @Autowired
    StudentCourseDetailMapper studentCourseDetailMapper;
    @Autowired
    StudentServiceImpl studentServiceImpl;


    @Test
    @Transactional
    void dropCourseTest() {
        String studentNumber = "201822111933444";
        Integer studentSemester = 1;

        LambdaQueryWrapper<Grade> queryWrapper = new LambdaQueryWrapper<Grade>()
                .eq(Grade::getStudentNumber, studentNumber)
                .eq(Grade::getCourseDetailId, 1L);
        Grade gradeRecord = gradeService.getOne(queryWrapper);
        // 检查是否已选该课程
        if (gradeRecord != null) {
            throw new KnownException(ExceptionEnum.COURSE_HAS_BEEN_CHOSEN);
        }
        CourseDetailDTO courseDetailDTO = courseDetailService.getDtoById(1L);
        Integer courseSemester = courseDetailDTO.getSemester();
        // 检查学期是否匹配
        if (!studentSemester.equals(courseSemester)) {
            throw new KnownException(ExceptionEnum.COURSE_STUDENT_SEMESTER_MISMATCH);
        }
        // 检查时间是否没有冲突
        Integer dayOfTheWeek = courseDetailDTO.getDayOfTheWeek();
        Integer schedulingTime = courseDetailDTO.getSchedulingTime();
        CourseDetailDTO sameTimeElective = studentCourseDetailMapper.OneByStudentNumberAndDayOfTheWeekAndSchedulingTime(studentNumber, dayOfTheWeek, schedulingTime, ElectiveStatusEnum.CHOSEN.getValue());
        if (sameTimeElective != null) {
            throw new KnownException(ExceptionEnum.STUDENT_NOT_AVAILABLE);
        }

        // 添加选课记录
        StudentCourseDetail electiveRecord = new StudentCourseDetail()
                .setStatus(ElectiveStatusEnum.CHOSEN)
                .setCourseDetailId(1L)
                .setStudentNumber(studentNumber);
        studentCourseDetailService.save(electiveRecord);

        // 添加空成绩记录
        Grade grade = new Grade()
                .setStudentNumber(studentNumber)
                .setCourseDetailId(1L);
        gradeService.save(grade);

        List<CourseDetailDTO> courseSchedule = studentServiceImpl.getCourseSchedule(1);
        for (CourseDetailDTO detailDTO : courseSchedule) {
            System.out.println(detailDTO);
        }
    }
}
    