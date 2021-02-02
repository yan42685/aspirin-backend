package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.mapper.StudentCourseDetailMapper;
import com.hubu.aspirin.model.dto.CourseDetailDTO;
import com.hubu.aspirin.model.dto.CourseDropDTO;
import com.hubu.aspirin.model.entity.StudentCourseDetail;
import com.hubu.aspirin.model.enums.ElectiveStatusEnum;
import com.hubu.aspirin.service.StudentCourseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class
StudentCourseDetailServiceImpl extends ServiceImpl<StudentCourseDetailMapper, StudentCourseDetail> implements StudentCourseDetailService {
    @Autowired
    StudentCourseDetailMapper studentCourseDetailMapper;

    @Override
    public IPage<CourseDropDTO> pageCourseDropRecord(Integer current, Integer size, String studentNumber, Integer semester) {
        Page<CourseDropDTO> page = new Page<>(current, size);
        return studentCourseDetailMapper.pageCourseDropRecourd(page, studentNumber, semester, ElectiveStatusEnum.DROPPED.getValue());
    }

    @Override
    public List<CourseDetailDTO> studentCourseSchedule(String studentNumber, Integer semester) {
        return studentCourseDetailMapper.listCourseDetailDtoByStudentNumberAndSemester(studentNumber, semester, ElectiveStatusEnum.CHOSEN.getValue());
    }
}
