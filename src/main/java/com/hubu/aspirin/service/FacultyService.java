package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.model.dto.FacultyDTO;
import com.hubu.aspirin.model.entity.Faculty;

import java.util.List;

public interface FacultyService extends IService<Faculty> {
    /**
     * 根据学院名获取编号
     */
    String getNumberByName(String name);

    /**
     * 根据编号获取学院名
     */
    String getNameByNumber(String number);

    /**
     * 获取所有学院
     */
    List<FacultyDTO> getList();

}
