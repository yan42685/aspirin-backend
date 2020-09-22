package com.hubu.aspirin.service.impl;

import com.hubu.aspirin.model.entity.Student;
import com.hubu.aspirin.mapper.StudentMapper;
import com.hubu.aspirin.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
