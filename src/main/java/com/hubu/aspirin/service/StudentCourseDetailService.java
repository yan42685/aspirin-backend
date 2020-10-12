package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hubu.aspirin.model.dto.CourseDetailDTO;
import com.hubu.aspirin.model.dto.CourseDropDTO;
import com.hubu.aspirin.model.entity.CourseDetail;
import com.hubu.aspirin.model.entity.StudentCourseDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface StudentCourseDetailService extends IService<StudentCourseDetail> {
    /**
     * 查看退课记录
     */
    IPage<CourseDropDTO> pageCourseDropRecord(Integer current, Integer size, String studentNumber, Integer semester);

    /**
     * 查看学生已有课表
     */
    List<CourseDetailDTO> studentCourseSchedule(String studentNumber, Integer semester);

}
