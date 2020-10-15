package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.common.AspirinConstant;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.converter.CourseConverter;
import com.hubu.aspirin.enums.CourseTypeEnum;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.hubu.aspirin.mapper.CourseMapper;
import com.hubu.aspirin.model.dto.CourseDTO;
import com.hubu.aspirin.model.dto.CourseModifiableDTO;
import com.hubu.aspirin.model.entity.Course;
import com.hubu.aspirin.service.CourseDetailService;
import com.hubu.aspirin.service.CourseService;
import com.hubu.aspirin.util.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    CourseDetailService courseDetailService;


    @Override
    public IPage<CourseDTO> queryPage(Integer current, Integer size, String specialtyNumber, CourseTypeEnum courseType, String courseNameOrNumber) {
        Page<Course> page = new Page<>(current, size);
        Integer type = courseType == null ? null : courseType.getValue();
        IPage<Course> coursePage = courseMapper.queryPage(page, specialtyNumber, type, courseNameOrNumber);
        return CourseConverter.INSTANCE.entityToDtoPage(coursePage);
    }

    @Override
    public CourseDTO createOne(CourseModifiableDTO courseModifiableDTO) {
        Course course = getByNumber(courseModifiableDTO.getNumber());
        if (course != null) {
            throw new KnownException(ExceptionEnum.NUMBER_EXISTS);
        }
        course = CourseConverter.INSTANCE.modifiableDtoToEntity(courseModifiableDTO);
        save(course);
        Course newCourse = getByNumber(course.getNumber());
        return CourseConverter.INSTANCE.entityToDto(newCourse);
    }

    @Override
    public CourseDTO updateByNumber(String originalNumber, CourseModifiableDTO courseModifiableDTO) {
        String newNumber = courseModifiableDTO.getNumber();
        Course course = getByNumber(originalNumber);
        if (!originalNumber.equals(newNumber) && course == null) {
            throw new KnownException(ExceptionEnum.NUMBER_NOT_EXIST);
        }
        course = CourseConverter.INSTANCE.updateEntityFromModifiableDto(courseModifiableDTO, course);
        updateById(course);
        return CourseConverter.INSTANCE.entityToDto(course);
    }

    @Override
    public boolean deleteByNumber(String number) {
        Course byNumber = getByNumber(number);
        if (byNumber == null) {
            throw new KnownException(ExceptionEnum.NUMBER_NOT_EXIST);
        }
        remove(new QueryWrapper<Course>().eq("number", number));
        return true;
    }

    @Override
    public String updateIconByNumber(String number, MultipartFile file) {
        Course course = getByNumber(number);
        if (course == null) {
            throw new KnownException(ExceptionEnum.NUMBER_NOT_EXIST);
        }
        String fileName = file.getOriginalFilename();
        String uploadKey = "course/" + number + "/icon/" + fileName;

        String defaultIconUrl = AspirinConstant.DEFAULT_COURSE_ICON_URL.getValue();
        String oldIconUrl = course.getIconUrl();
        // 原来的图标不是默认图标时才删除之前的图标
        if (!defaultIconUrl.equals(oldIconUrl)) {
            String oldUploadKey = QiniuUtils.getKeyFromUrl(oldIconUrl);
            QiniuUtils.deleteFile(oldUploadKey);
        }

        String newIconUrl;
        newIconUrl = QiniuUtils.uploadFile(file, uploadKey);
        course.setIconUrl(newIconUrl);
        updateById(course);
        return newIconUrl;
    }



    private Course getByNumber(String number) {
        QueryWrapper<Course> queryWrapper = new QueryWrapper<Course>()
                .eq("number", number);
        return getOne(queryWrapper);
    }
}
