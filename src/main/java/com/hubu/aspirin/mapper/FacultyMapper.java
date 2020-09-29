package com.hubu.aspirin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hubu.aspirin.model.entity.Faculty;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FacultyMapper extends BaseMapper<Faculty> {
    @Select("select distinct name from faculty")
    List<String> getAllNames();
}
