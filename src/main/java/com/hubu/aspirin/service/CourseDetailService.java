package com.hubu.aspirin.service;

import com.hubu.aspirin.model.dto.CourseAssignDTO;
import com.hubu.aspirin.model.dto.CourseScheduleDTO;
import com.hubu.aspirin.model.entity.CourseDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CourseDetailService extends IService<CourseDetail> {
    /**
     * 给教师分配课程
     */
    List<CourseScheduleDTO> assignCourseForTeacher(CourseAssignDTO courseAssignDTO);

    /**
     * 分配课程前的检查
     */
    boolean checkBeforeAssign(String classroomNumber, String teacherNumber, Integer dayOfTheWeek, Integer schedulingTime);

    /**
     * 获取授课表
     */
    List<CourseScheduleDTO> getCourseScheduleByTeacherNumber(String teacherNumber);


    /**
     * 获取教室课表
     */
    List<CourseScheduleDTO> getCourseScheduleByClassroomNumber(String classroomNumber);
}
