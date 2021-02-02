package com.hubu.aspirin.controller;

import com.hubu.aspirin.service.AdministratorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdministratorControllerTest {
    @Test
    void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }

    @Autowired
    AdministratorService administratorService;

    @Test
    void getApplicationSwitchStatus() {
    }
}
    