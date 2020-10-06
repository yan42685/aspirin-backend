package com.hubu.aspirin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hubu.aspirin.enums.CourseTypeEnum;
import com.hubu.aspirin.model.entity.Course;
import org.apache.ibatis.annotations.Param;

public interface CourseMapper extends BaseMapper<Course> {
    IPage<Course> getAllPageBySpecialtyNumber(Page<Course> page, @Param("specialtyNumber") String specialtyNumber);

    IPage<Course> getAllPageBySpecialtyNumberAndCourseType(Page<Course> page, @Param("specialtyNumber") String specialtyNumber, @Param("courseType") CourseTypeEnum courseType);

}
