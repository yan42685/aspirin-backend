package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.converter.StudentConverter;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.hubu.aspirin.mapper.StudentMapper;
import com.hubu.aspirin.model.bo.StudentBO;
import com.hubu.aspirin.model.dto.StudentDTO;
import com.hubu.aspirin.model.entity.Student;
import com.hubu.aspirin.service.StudentService;
import com.hubu.aspirin.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public StudentDTO getInformationByNumber(String number) {
        StudentBO studentBO = getBoByNumber(number);
        if (studentBO == null) {
            throw new KnownException(ExceptionEnum.USER_NOT_EXISTS);
        }
        return StudentConverter.INSTANCE.boToDto(studentBO);
    }

    @Override
    public StudentDTO getInformation() {
        Long id = UserUtils.getCurrentUser().getId();
        String number = getById(id).getNumber();
        return getInformationByNumber(number);
    }

    @Override
    public StudentBO getBoByNumber(String number) {
        return studentMapper.getBoByNumber(number);
    }

    @Override
    public StudentBO getBoById(Long id) {
        return studentMapper.getBoById(id);
    }

    @Override
    public IPage<StudentBO> pageBoByNumberOrRealName(Integer current, Integer size, String queryString) {
        Page<StudentBO> page = new Page<>(current, size);
        return studentMapper.pageBoByNumberOrRealName(page, queryString);
    }


}
