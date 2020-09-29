package com.hubu.aspirin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hubu.aspirin.model.entity.Specialty;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SpecialtyMapper extends BaseMapper<Specialty> {
    @Select("select distinct name from specialty")
    List<String> getAllNames();

    List<String> getAllNamesByFacultyName(String facultyName);
}
