package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.converter.CourseDetailConverter;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.hubu.aspirin.model.dto.CourseAssignDTO;
import com.hubu.aspirin.model.dto.CourseScheduleDTO;
import com.hubu.aspirin.model.entity.CourseDetail;
import com.hubu.aspirin.mapper.CourseDetailMapper;
import com.hubu.aspirin.service.CourseDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseDetailServiceImpl extends ServiceImpl<CourseDetailMapper, CourseDetail> implements CourseDetailService {
    @Autowired
    CourseDetailMapper courseDetailMapper;

    @Override
    public List<CourseScheduleDTO> assignCourseForTeacher(CourseAssignDTO dto) {
        String classroomNumber = dto.getClassroomNumber();
        String teacherNumber = dto.getTeacherNumber();
        Integer dayOfTheWeek = dto.getDayOfTheWeek();
        Integer schedulingTime = dto.getSchedulingTime();
        CourseDetail courseDetail;

        // 检查教室是否可用
        courseDetail = getAboutClassroom(classroomNumber, dayOfTheWeek, schedulingTime);
        if (courseDetail != null) {
            removeById(courseDetail);
        }
        // 检查教师是否有空
        courseDetail = getAboutTeacher(teacherNumber, dayOfTheWeek, schedulingTime);
        if (courseDetail != null) {
            removeById(courseDetail);
        }

        courseDetail = CourseDetailConverter.INSTANCE.assignDto2Entity(dto);
        save(courseDetail);
        return getCourseScheduleByTeacherNumber(teacherNumber);
    }

    @Override
    public boolean checkBeforeAssign(CourseAssignDTO dto) {
        String classroomNumber = dto.getClassroomNumber();
        String teacherNumber = dto.getTeacherNumber();
        Integer dayOfTheWeek = dto.getDayOfTheWeek();
        Integer schedulingTime = dto.getSchedulingTime();
        CourseDetail courseDetail;

        // 检查教室是否可用
        courseDetail = getAboutClassroom(classroomNumber, dayOfTheWeek, schedulingTime);
        if (courseDetail != null) {
            throw new KnownException(ExceptionEnum.CLASSROOM_NOT_AVAILABLE);
        }

        // 检查教师是否有空
        courseDetail = getAboutTeacher(teacherNumber, dayOfTheWeek, schedulingTime);
        if (courseDetail != null) {
            throw new KnownException(ExceptionEnum.TEACHER_NOT_AVAILABLE);
        }
        return true;
    }

    @Override
    public List<CourseScheduleDTO> getCourseScheduleByTeacherNumber(String teacherNumber) {
        return courseDetailMapper.getCourseScheduleByTeacherNumber(teacherNumber);
    }

    @Override
    public List<CourseScheduleDTO> getCourseScheduleByClassroomNumber(String classroomNumber) {
        return courseDetailMapper.getCourseScheduleByClassroomNumber(classroomNumber);
    }

    private CourseDetail getAboutTeacher(String teacherNumber, Integer dayOfTheWeek, Integer schedulingTime) {
        QueryWrapper<CourseDetail> queryWrapper = new QueryWrapper<CourseDetail>()
                .eq("teacher_number", teacherNumber)
                .eq("day_of_the_week", dayOfTheWeek)
                .eq("scheduling_time", schedulingTime);
        return getOne(queryWrapper);
    }

    private CourseDetail getAboutClassroom(String classroomNumber, Integer dayOfTheWeek, Integer schedulingTime) {
        QueryWrapper<CourseDetail> queryWrapper = new QueryWrapper<CourseDetail>()
                .eq("classroom_number", classroomNumber)
                .eq("day_of_the_week", dayOfTheWeek)
                .eq("scheduling_time", schedulingTime);
        return getOne(queryWrapper);
    }

}
