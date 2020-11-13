package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.model.dto.SpecialtyDTO;
import com.hubu.aspirin.model.entity.Specialty;

import java.util.List;

public interface SpecialtyService extends IService<Specialty> {
    /**
     * 根据专业名获取编号
     */
    String getNumberByName(String name);

    /**
     * 获取所有专业
     */
    List<SpecialtyDTO> getList();

    /**
     * 获取学院包含的专业
     */
    List<SpecialtyDTO> getListByFacultyNumber(String facultyNumber);

    /**
     * 获取专业所有入学年份
     */
    List<Integer> getAllAdmissionYear(String number);
}
