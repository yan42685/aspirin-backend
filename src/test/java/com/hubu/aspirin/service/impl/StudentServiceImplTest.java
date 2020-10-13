package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.enums.CourseTypeEnum;
import com.hubu.aspirin.enums.ElectiveStatusEnum;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.hubu.aspirin.mapper.GradeMapper;
import com.hubu.aspirin.mapper.StudentMapper;
import com.hubu.aspirin.model.dto.CourseDTO;
import com.hubu.aspirin.model.dto.CourseDetailDTO;
import com.hubu.aspirin.model.dto.GradeDTO;
import com.hubu.aspirin.model.entity.Student;
import com.hubu.aspirin.model.entity.StudentCourseDetail;
import com.hubu.aspirin.service.CourseDetailService;
import com.hubu.aspirin.service.StudentCourseDetailService;
import com.hubu.aspirin.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


    @Test
    void dropCourseTest() {
    }
}
    