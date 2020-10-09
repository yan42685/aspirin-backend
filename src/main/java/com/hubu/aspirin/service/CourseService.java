package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.enums.CourseTypeEnum;
import com.hubu.aspirin.model.dto.CourseDTO;
import com.hubu.aspirin.model.dto.ModifiableCourseDTO;
import com.hubu.aspirin.model.entity.Course;
import org.springframework.web.multipart.MultipartFile;

public interface CourseService extends IService<Course> {
    /**
     * 查看全部课程
     */
    IPage<CourseDTO> getAllPage(Integer current, Integer size);

    /**
     * 根据专业编号查看课程
     */
    IPage<CourseDTO> getAllPageBySpecialtyNumber(Integer current, Integer size, String specialtyNumber);

    /**
     * 根据专业编号和课程分类查看课程
     */
    IPage<CourseDTO> getAllPageBySpecialtyNumberAndCourseType(Integer current, Integer size, String specialtyNumber, CourseTypeEnum courseType);

    /**
     * 添加课程
     */
    CourseDTO createOne(ModifiableCourseDTO modifiableCourseDTO);

    /**
     * 修改课程
     */
    CourseDTO updateByNumber(String originalNumber, ModifiableCourseDTO modifiableCourseDTO);

    /**
     * 删除课程
     */
    boolean deleteByNumber(String number);

    /**
     * 修改课程图标
     */
    String modifyIconByNumber(String number, MultipartFile file);
}
