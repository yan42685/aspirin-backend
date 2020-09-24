package com.hubu.aspirin.service.impl;

import com.hubu.aspirin.model.entity.Administrator;
import com.hubu.aspirin.model.entity.User;
import com.hubu.aspirin.service.AdministratorService;
import com.hubu.aspirin.service.GradeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;


@SpringBootTest
class UserServiceImplTest {
    @Test
    void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }

    @Autowired
    AdministratorService administratorService;
    @Autowired
    GradeService gradeService;

    @Test
    void simpleRegister() {
        Assertions.assertEquals(3, 3);
    }
}
    