package com.hubu.aspirin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hubu.aspirin.model.dto.StudentDTO;
import com.hubu.aspirin.model.dto.StudentQueryDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentMapperTest {
    @Test
    void checkTestEnvironment() {
        Assertions.assertTrue(true);  // 应该总是返回true, 排除测试环境的问题
    }

    @Autowired
    StudentMapper studentMapper;

    @Test
    void page() {
//        StudentQueryDTO dto = new StudentQueryDTO();
////        dto.setAdmissionYear(2018);
//        dto.setFacultyNumber("jixin");
//        IPage<StudentDTO> page = studentMapper.page(new Page<>(1, 4), dto);
//        System.out.println(page.getRecords().size());

    }
}
    