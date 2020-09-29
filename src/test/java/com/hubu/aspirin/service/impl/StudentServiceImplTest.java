package com.hubu.aspirin.service.impl;

import com.hubu.aspirin.converter.StudentConverter;
import com.hubu.aspirin.mapper.StudentMapper;
import com.hubu.aspirin.model.bo.StudentBO;
import com.hubu.aspirin.model.dto.StudentDTO;
import com.hubu.aspirin.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


    @Test
    void getBoByNumber() {
    }
}
    