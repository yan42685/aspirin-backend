package com.hubu.aspirin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hubu.aspirin.model.bo.StudentBO;
import com.hubu.aspirin.model.entity.Student;

public interface StudentMapper extends BaseMapper<Student> {

    StudentBO getBoByNumber(String number);

    StudentBO getBoById(Long id);

}
