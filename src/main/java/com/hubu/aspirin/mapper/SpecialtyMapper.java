package com.hubu.aspirin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hubu.aspirin.model.entity.Specialty;

import java.util.List;

public interface SpecialtyMapper extends BaseMapper<Specialty> {

    List<Specialty> getAllNamesByFacultyName(String facultyName);
}
