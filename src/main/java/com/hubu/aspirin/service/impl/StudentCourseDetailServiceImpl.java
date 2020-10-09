package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hubu.aspirin.model.entity.CourseDetail;
import com.hubu.aspirin.model.entity.StudentCourseDetail;
import com.hubu.aspirin.mapper.StudentCourseDetailMapper;
import com.hubu.aspirin.service.CourseService;
import com.hubu.aspirin.service.StudentCourseDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseDetailServiceImpl extends ServiceImpl<StudentCourseDetailMapper, StudentCourseDetail> implements StudentCourseDetailService {

}
