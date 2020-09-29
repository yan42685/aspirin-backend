package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.model.bo.StudentBO;
import com.hubu.aspirin.model.dto.StudentDTO;
import com.hubu.aspirin.model.entity.Student;

public interface StudentService extends IService<Student> {
    /**
     * 根据编号获取个人信息
     */
    StudentDTO getInformationByNumber(String number);

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

}
