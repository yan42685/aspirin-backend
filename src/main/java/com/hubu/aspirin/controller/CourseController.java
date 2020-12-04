package com.hubu.aspirin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.enums.CourseTypeEnum;
import com.hubu.aspirin.model.dto.CourseAssignDTO;
import com.hubu.aspirin.model.dto.CourseDTO;
import com.hubu.aspirin.model.dto.CourseDetailDTO;
import com.hubu.aspirin.model.dto.CourseModifiableDTO;
import com.hubu.aspirin.service.CourseDetailService;
import com.hubu.aspirin.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = "课程")
@RequestMapping("api/course")
@RestController
public class CourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    CourseDetailService courseDetailService;

    @ApiOperation(value = "查询课程")
    @GetMapping("page")
    JsonWrapper<IPage<CourseDTO>> queryCoursePage(Integer current, Integer size, String specialtyNumber, CourseTypeEnum courseType, String nameOrNumber) {
        return new JsonWrapper<>(courseService.queryPage(current, size, specialtyNumber, courseType, nameOrNumber));
    }

    @ApiOperation("创建课程")
    @PostMapping
    JsonWrapper<CourseDTO> createOne(CourseModifiableDTO courseModifiableDTO, MultipartFile image) {
        return new JsonWrapper<>(courseService.createOne(courseModifiableDTO, image));
    }

    @ApiOperation("修改课程")
    @PutMapping
    JsonWrapper<CourseDTO> updateByNumber(String originalNumber, CourseModifiableDTO courseModifiableDTO) {
        return new JsonWrapper<>(courseService.updateByNumber(originalNumber, courseModifiableDTO));
    }

    @ApiOperation("删除课程")
    @DeleteMapping
    JsonWrapper<Boolean> deleteByNumber(String number) {
        return new JsonWrapper<>(courseService.deleteByNumber(number));
    }

    @ApiOperation("修改课程图标")
    @PutMapping("icon")
    JsonWrapper<String> modifyIconByNumber(String number, MultipartFile image) {
        return new JsonWrapper<>(courseService.updateIconByNumber(number, image));
    }

    @ApiOperation("给教师分配课程")
    @PutMapping("assign")
    JsonWrapper<List<CourseDetailDTO>> assignCourseForTeacher(CourseAssignDTO courseAssignDTO) {
        return new JsonWrapper<>(courseDetailService.assignCourseForTeacher(courseAssignDTO));
    }

    @ApiOperation(value = "分配课程前的检查", notes = "检查教室是否被占用以及教师是否有时间")
    @GetMapping("before-assign")
    JsonWrapper<Boolean> checkBeforeAssign(String classroomNumber, String teacherNumber, Integer dayOfTheWeek, Integer schedulingTime){
        return new JsonWrapper<>(courseDetailService.checkBeforeAssign(classroomNumber, teacherNumber, dayOfTheWeek, schedulingTime));
    }

    @ApiOperation("获取授课表")
    @GetMapping("teacher/schedule")
    JsonWrapper<List<CourseDetailDTO>> teacherCourseSchedule(String teacherNumber) {
        return new JsonWrapper<>(courseDetailService.teacherCourseSchedule(teacherNumber));
    }

    @ApiOperation("获取教室课表")
    @GetMapping("classroom/schedule")
    JsonWrapper<List<CourseDetailDTO>> classroomCourseSchedule(String classroomNumber) {
        return new JsonWrapper<>(courseDetailService.classroomCourseSchedule(classroomNumber));
    }
}
