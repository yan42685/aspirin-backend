package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.model.dto.StudentDTO;
import com.hubu.aspirin.model.dto.StudentQueryDTO;
import com.hubu.aspirin.model.entity.Student;

public interface StudentService extends IService<Student> {

    /**
     * 获取自身个人信息
     */
    StudentDTO getInformation();

    /**
     * 根据编号获取获取DTO
     */
    StudentDTO getDtoByNumber(String number);

    /**
     * 根据id获取DTO
     */
    StudentDTO getDtoById(Long id);

    /**
     * 根据真名和学号搜索
     */
    IPage<StudentDTO> pageByQueryDto(Integer current, Integer size, StudentQueryDTO studentQueryDTO);
}
