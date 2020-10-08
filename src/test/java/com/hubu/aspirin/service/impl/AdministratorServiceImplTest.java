package com.hubu.aspirin.service.impl;

import com.hubu.aspirin.enums.GenderEnum;
import com.hubu.aspirin.model.dto.BulletinDTO;
import com.hubu.aspirin.model.dto.StudentManagementDTO;
import com.hubu.aspirin.service.AdministratorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdministratorServiceImplTest {
    @Test
    void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }

    @Autowired
    AdministratorService administratorService;

    @Test
    void pageStudentByNumberOrRealName() {
    }

    @Test
    void updateStudent() {
    }
}

    