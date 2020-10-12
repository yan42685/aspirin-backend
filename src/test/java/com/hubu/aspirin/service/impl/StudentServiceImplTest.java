package com.hubu.aspirin.service.impl;

import com.hubu.aspirin.enums.CourseTypeEnum;
import com.hubu.aspirin.mapper.StudentMapper;
import com.hubu.aspirin.model.dto.CourseDTO;
import com.hubu.aspirin.model.dto.CourseDetailDTO;
import com.hubu.aspirin.service.CourseDetailService;
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


    @Test
    void getBoByNumber() {
        List<CourseDetailDTO> courseDetailDTOS = courseDetailService.listBySpecialtyNumberAndCourseType("0000", CourseTypeEnum.COMMON_COMPULSORY);
        System.out.println(courseDetailDTOS.size());
    }
}
    