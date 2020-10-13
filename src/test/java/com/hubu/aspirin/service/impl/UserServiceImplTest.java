package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hubu.aspirin.converter.BulletinConverter;
import com.hubu.aspirin.mapper.BulletinMapper;
import com.hubu.aspirin.model.dto.BulletinDTO;
import com.hubu.aspirin.model.entity.Bulletin;
import com.hubu.aspirin.service.AdministratorService;
import com.hubu.aspirin.service.BulletinService;
import com.hubu.aspirin.service.GradeService;
import com.hubu.aspirin.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


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
    @Autowired
    BulletinService bulletinService;
    @Autowired
    UserService userService;
    @Autowired
    BulletinMapper bulletinMapper;

    @Test
    void simpleRegister() {
        Assertions.assertEquals(3, 3);
    }

    @Test
    void getBulletinPage() {
    }
}
    