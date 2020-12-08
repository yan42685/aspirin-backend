package com.hubu.aspirin.service.impl;

import com.hubu.aspirin.mapper.GradeMapper;
import com.hubu.aspirin.mapper.StudentCourseDetailMapper;
import com.hubu.aspirin.mapper.StudentMapper;
import com.hubu.aspirin.service.CourseDetailService;
import com.hubu.aspirin.service.GradeService;
import com.hubu.aspirin.service.StudentCourseDetailService;
import com.hubu.aspirin.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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

    }
}
    