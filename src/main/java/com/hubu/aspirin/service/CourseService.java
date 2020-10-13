package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.enums.CourseTypeEnum;
import com.hubu.aspirin.model.dto.CourseDTO;
import com.hubu.aspirin.model.dto.CourseModifiableDTO;
import com.hubu.aspirin.model.entity.Course;
import org.springframework.web.multipart.MultipartFile;

public interface CourseService extends IService<Course> {

    /**
     * 根据专业编号和课程分类查看课程
     */
    IPage<CourseDTO> queryPage(Integer current, Integer size, String specialtyNumber, CourseTypeEnum courseType, String nameOrNumber);

    /**
     * 添加课程
     */
    CourseDTO createOne(CourseModifiableDTO courseModifiableDTO);

    /**
     * 修改课程
     */
    CourseDTO updateByNumber(String originalNumber, CourseModifiableDTO courseModifiableDTO);

    /**
     * 删除课程
     */
    boolean deleteByNumber(String number);

    /**
     * 修改课程图标
     */
    String updateIconByNumber(String number, MultipartFile file);



}
