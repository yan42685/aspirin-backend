package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.model.entity.Faculty;

import java.util.List;

public interface FacultyService extends IService<Faculty> {
    /**
     * 根据学院名获取编号
     */
    String getNumberByName(String name);

    /**
     * 获取所有学院名
     */
    List<String> getAllNames();

}
