package com.hubu.aspirin.service;

import com.hubu.aspirin.enums.CourseTypeEnum;
import com.hubu.aspirin.enums.ElectiveStatusEnum;
import com.hubu.aspirin.model.dto.CourseAssignDTO;
import com.hubu.aspirin.model.dto.CourseDetailDTO;
import com.hubu.aspirin.model.entity.CourseDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CourseDetailService extends IService<CourseDetail> {
    /**
     * 给教师分配课程
     */
    List<CourseDetailDTO> assignCourseForTeacher(CourseAssignDTO courseAssignDTO);

    /**
     * 分配课程前的检查
     */
    boolean checkBeforeAssign(String classroomNumber, String teacherNumber, Integer dayOfTheWeek, Integer schedulingTime);

    /**
     * 获取授课表
     */
    List<CourseDetailDTO> teacherCourseSchedule(String teacherNumber);

    /**
     * 获取教室课表
     */
    List<CourseDetailDTO> classroomCourseSchedule(String classroomNumber);

    /**
     * 获取学生可选课表
     */
    List<CourseDetailDTO> studentAvailableCourseList(String specialtyNumber, Integer semester, CourseTypeEnum courseType);

    /**
     * 获取指定课程信息
     */
    CourseDetailDTO getDtoById(Long id);
}
