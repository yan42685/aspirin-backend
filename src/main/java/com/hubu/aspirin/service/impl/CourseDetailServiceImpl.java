package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.converter.CourseDetailConverter;
import com.hubu.aspirin.core.KnownException;
import com.hubu.aspirin.core.needconfig.ExceptionEnum;
import com.hubu.aspirin.mapper.CourseDetailMapper;
import com.hubu.aspirin.model.dto.CourseAssignDTO;
import com.hubu.aspirin.model.dto.CourseDetailDTO;
import com.hubu.aspirin.model.entity.CourseDetail;
import com.hubu.aspirin.model.enums.CourseTypeEnum;
import com.hubu.aspirin.service.CourseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseDetailServiceImpl extends ServiceImpl<CourseDetailMapper, CourseDetail> implements CourseDetailService {
    @Autowired
    CourseDetailMapper courseDetailMapper;

    @Override
    public List<CourseDetailDTO> assignCourseForTeacher(CourseAssignDTO dto) {
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
        return teacherCourseSchedule(teacherNumber);
    }

    @Override
    public boolean checkBeforeAssign(String classroomNumber, String teacherNumber, Integer dayOfTheWeek, Integer schedulingTime) {
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
    public List<CourseDetailDTO> teacherCourseSchedule(String teacherNumber) {
        return courseDetailMapper.listByTeacherNumber(teacherNumber);
    }

    @Override
    public List<CourseDetailDTO> classroomCourseSchedule(String classroomNumber) {
        return courseDetailMapper.listByClassroomNumber(classroomNumber);
    }

    @Override
    public List<CourseDetailDTO> studentAvailableCourseList(String specialtyNumber, Integer semester, CourseTypeEnum courseType) {
        Integer type = courseType == null ? null : courseType.getValue();
        return courseDetailMapper.listBySpecialtyNumberAndSemesterAndCourseType(specialtyNumber, semester, type);
    }

    @Override
    public CourseDetailDTO getDtoById(Long id) {
        return courseDetailMapper.getDtoById(id);
    }

    @Override
    public String getCourseNumberById(Long id) {
        CourseDetail courseDetail = getById(id);
        return courseDetail.getCourseNumber();
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
