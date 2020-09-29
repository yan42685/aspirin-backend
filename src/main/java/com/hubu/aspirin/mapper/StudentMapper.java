package com.hubu.aspirin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hubu.aspirin.model.bo.StudentBO;
import com.hubu.aspirin.model.entity.Student;

public interface StudentMapper extends BaseMapper<Student> {

    StudentBO getBoByNumber(String number);

    StudentBO getBoById(Long id);

    IPage<StudentBO> pageBoByNumberOrRealName(Page<StudentBO> page, String queryString);
}
