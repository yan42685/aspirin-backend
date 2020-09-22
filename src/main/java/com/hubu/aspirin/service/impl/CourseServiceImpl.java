package com.hubu.aspirin.service.impl;

import com.hubu.aspirin.model.entity.Course;
import com.hubu.aspirin.mapper.CourseMapper;
import com.hubu.aspirin.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {

}
