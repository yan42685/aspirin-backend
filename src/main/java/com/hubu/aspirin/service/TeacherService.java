package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.model.dto.TeacherDTO;
import com.hubu.aspirin.model.entity.Teacher;

public interface TeacherService extends IService<Teacher> {
    /**
     * 根据编号获取个人信息
     */
    TeacherDTO getDtoByNumber(String number);

    /**
     * 获取自身个人信息
     */
    TeacherDTO getInformation();

}
