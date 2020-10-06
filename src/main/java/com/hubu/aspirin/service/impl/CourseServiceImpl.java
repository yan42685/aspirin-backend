package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.converter.CourseConverter;
import com.hubu.aspirin.enums.CourseTypeEnum;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.hubu.aspirin.mapper.CourseMapper;
import com.hubu.aspirin.model.dto.CourseDTO;
import com.hubu.aspirin.model.dto.ModifiableCourseDTO;
import com.hubu.aspirin.model.entity.Course;
import com.hubu.aspirin.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public IPage<CourseDTO> getAllPage(Integer current, Integer size) {
        Page<Course> page = new Page<>(current, size);
        IPage<Course> coursePage = page(page);
        return CourseConverter.INSTANCE.entityToDtoPage(coursePage);
    }

    @Override
    public IPage<CourseDTO> getAllPageBySpecialtyNumber(Integer current, Integer size, String specialtyNumber) {
        Page<Course> page = new Page<>(current, size);
        IPage<Course> coursePage = courseMapper.getAllPageBySpecialtyNumber(page, specialtyNumber);
        return CourseConverter.INSTANCE.entityToDtoPage(coursePage);
    }

    @Override
    public IPage<CourseDTO> getAllPageBySpecialtyNumberAndCourseType(Integer current, Integer size, String specialtyNumber, CourseTypeEnum courseType) {
        Page<Course> page = new Page<>(current, size);
        IPage<Course> coursePage = courseMapper.getAllPageBySpecialtyNumberAndCourseType(page, specialtyNumber, courseType);
        return CourseConverter.INSTANCE.entityToDtoPage(coursePage);
    }

    @Override
    public CourseDTO createOne(ModifiableCourseDTO modifiableCourseDTO) {
        Course courseByNumber = getByNumber(modifiableCourseDTO.getNumber());
        if (courseByNumber != null) {
            throw new KnownException(ExceptionEnum.NUMBER_EXISTS);
        }
        Course course = CourseConverter.INSTANCE.modifiableDtoToEntity(modifiableCourseDTO);
        save(course);
        Course newCourse = getByNumber(course.getNumber());
        return CourseConverter.INSTANCE.entityToDto(newCourse);
    }

    @Override
    public CourseDTO updateByNumber(String number, ModifiableCourseDTO modifiableCourseDTO) {
        Course course = CourseConverter.INSTANCE.modifiableDtoToEntity(modifiableCourseDTO);
        update(course, new UpdateWrapper<Course>().eq("number", number));
        Course updatedCourse = getByNumber(course.getNumber());
        return CourseConverter.INSTANCE.entityToDto(updatedCourse);
    }

    @Override
    public boolean deleteByNumber(String number) {
        Course byNumber = getByNumber(number);
        if (byNumber == null) {
            throw new KnownException(ExceptionEnum.NUMBER_NOT_EXISTS);
        }
        remove(new QueryWrapper<Course>().eq("number", number));
        return true;
    }

    @Override
    // TODO: 等待完成
    public String modifyIconByNumber(String number) {
        return null;
    }

    private Course getByNumber(String number) {

        QueryWrapper<Course> queryWrapper = new QueryWrapper<Course>()
                .eq("number", number);
        return getOne(queryWrapper);
    }
}
