package com.hubu.aspirin.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpecialtyMapperTest {
    @Test
    void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }

    @Autowired
    SpecialtyMapper specialtyMapper;

    @Test
    void getAllNamesByFacultyName() {
        String facultyName = "计算机与信息工程学院";
        String specialtyName = "软件工程";
        List<String> specialtyNameList = specialtyMapper.getAllNamesByFacultyName(facultyName);
        Assertions.assertTrue(specialtyNameList.contains(specialtyName));
    }
}
    