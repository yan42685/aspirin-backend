package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.mapper.StudentMapper;
import com.hubu.aspirin.model.dto.StudentDTO;
import com.hubu.aspirin.model.entity.Student;
import com.hubu.aspirin.model.entity.User;
import com.hubu.aspirin.service.StudentService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    StudentMapper studentMapper;


    @Override
    public StudentDTO getInformation() {
        User user  = (User) SecurityUtils.getSubject().getPrincipal();
        Long id = user.getId();
        String number = getById(id).getNumber();
        return getDtoByNumber(number);
    }

    @Override
    public StudentDTO getDtoByNumber(String number) {
        return studentMapper.getDtoByNumber(number);
    }

    @Override
    public StudentDTO getDtoById(Long id) {
        return studentMapper.getBoById(id);
    }

    @Override
    public IPage<StudentDTO> pageBoByNumberOrRealName(Integer current, Integer size, String queryString) {
        Page<StudentDTO> page = new Page<>(current, size);
        return studentMapper.pageBoByNumberOrRealName(page, queryString);
    }


}
