package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.model.entity.Specialty;

import java.util.List;

public interface SpecialtyService extends IService<Specialty> {
    /**
     * 根据专业名获取编号
     */
    String getNumberByName(String name);

    /**
     * 获取所有专业名
     */
    List<String> getAllNames();

    /**
     * 获取学院包含的专业名
     */
    List<String> getAllNamesByFacultyName(String facultyName);
}
