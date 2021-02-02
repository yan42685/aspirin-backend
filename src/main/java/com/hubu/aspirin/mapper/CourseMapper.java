package com.hubu.aspirin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hubu.aspirin.model.entity.Course;
import org.apache.ibatis.annotations.Param;

public interface CourseMapper extends BaseMapper<Course> {

    IPage<Course> queryPage(Page<Course> page, @Param("specialtyNumber") String specialtyNumber, @Param("courseType") Integer courseType, @Param("courseNameOrNumber") String courseNameOrNumber);

}
