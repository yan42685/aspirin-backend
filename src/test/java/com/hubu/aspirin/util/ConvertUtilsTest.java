package com.hubu.aspirin.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConvertUtilsTest {
    @Test
    void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }


    @Test
    void administratorGetByNumber() {
        Assertions.assertEquals(ConvertUtils.facultyGetNumberByName("计算机与信息工程学院"), "jixin");
    }
}
    