package com.hubu.aspirin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hubu.aspirin.model.bo.StudentBO;
import com.hubu.aspirin.model.entity.Student;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper extends BaseMapper<Student> {

    StudentBO getBoByNumber(String number);

    StudentBO getBoById(Long id);

    // NOTE: 如果只有一个参数，xml里可以直接写参数名, 否则涂药@Param注解
    IPage<StudentBO> pageBoByNumberOrRealName(Page<StudentBO> page, @Param("queryString") String queryString);
}
