package com.hubu.aspirin.mapper;

import com.hubu.aspirin.model.bo.StudentBO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentMapperTest {
    @Test
    void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }

    @Autowired
    StudentMapper studentMapper;

    @Test
    void getBoByNumber() {
        String number = "jixinrjgc001";
        StudentBO studentBO = studentMapper.getBoByNumber(number);
        System.out.println(studentBO);
        Assertions.assertNotNull(studentBO);
    }
}
    