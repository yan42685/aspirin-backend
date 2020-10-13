package com.hubu.aspirin.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hubu.aspirin.enums.ElectiveStatusEnum;
import com.hubu.aspirin.model.dto.GradeDTO;
import com.sun.org.apache.xerces.internal.xni.grammars.Grammar;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GradeMapperTest {
    @Test
    void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }

    @Autowired
    GradeMapper gradeMapper;

    @Test
    void listAll() {
        String number = "201822111710101";
        Page<GradeDTO> page = new Page<>(1, 4);
        gradeMapper.pageDtoByStudentNumberAndSemester(page, number, 1, ElectiveStatusEnum.CHOSEN.getValue());
    }
}
    