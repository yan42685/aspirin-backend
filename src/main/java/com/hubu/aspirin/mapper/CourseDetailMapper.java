package com.hubu.aspirin.mapper;

import com.hubu.aspirin.model.dto.CourseScheduleDTO;
import com.hubu.aspirin.model.entity.CourseDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface CourseDetailMapper extends BaseMapper<CourseDetail> {
    List<CourseScheduleDTO> getCourseScheduleByTeacherNumber(String teacherNumber);
    List<CourseScheduleDTO> getCourseScheduleByClassroomNumber(String classroomNumber);

}
