package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.model.bo.StudentBO;
import com.hubu.aspirin.model.dto.StudentDTO;
import com.hubu.aspirin.model.entity.Student;

public interface StudentService extends IService<Student> {
    /**
     * 根据编号获取个人信息
     */
    StudentDTO getDtoByNumber(String number);

    /**
     * 获取自身个人信息
     */
    StudentDTO getInformation();

    /**
     * 根据编号获取获取BO
     */
    StudentBO getBoByNumber(String number);

    /**
     * 根据id获取BO
     */
    StudentBO getBoById(Long id);

    /**
     * 根据真名和学号搜索
     */
    IPage<StudentBO> pageBoByNumberOrRealName(Integer current, Integer size, String queryString);
}
