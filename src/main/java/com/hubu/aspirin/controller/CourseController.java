package com.hubu.aspirin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.enums.CourseTypeEnum;
import com.hubu.aspirin.model.dto.CourseAssignDTO;
import com.hubu.aspirin.model.dto.CourseDTO;
import com.hubu.aspirin.model.dto.CourseScheduleDTO;
import com.hubu.aspirin.model.dto.ModifiableCourseDTO;
import com.hubu.aspirin.model.entity.CourseDetail;
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

    @ApiOperation(value = "查看全部课程")
    @GetMapping("page")
    JsonWrapper<IPage<CourseDTO>> getAllPage(Integer current, Integer size) {
        return new JsonWrapper<>(courseService.getAllPage(current, size));
    }

    @ApiOperation(value = "根据专业编号查看课程")
    @GetMapping("page-by-specialty-number")
    JsonWrapper<IPage<CourseDTO>> getAllPageBySpecialtyNumber(Integer current, Integer size, String specialtyNumber) {
        return new JsonWrapper<>(courseService.getAllPageBySpecialtyNumber(current, size, specialtyNumber));
    }

    @ApiOperation(value = "根据专业编号和课程分类查看课程")
    @GetMapping("page-by-specialty-number-and-course-type")
    JsonWrapper<IPage<CourseDTO>> getAllPageBySpecialtyNumberAndCourseType(Integer current, Integer size, String specialtyNumber, CourseTypeEnum courseType) {
        return new JsonWrapper<>(courseService.getAllPageBySpecialtyNumberAndCourseType(current, size, specialtyNumber, courseType));
    }

    @ApiOperation("创建课程")
    @PostMapping
    JsonWrapper<CourseDTO> createOne(ModifiableCourseDTO modifiableCourseDTO) {
        return new JsonWrapper<>(courseService.createOne(modifiableCourseDTO));
    }

    @ApiOperation("修改课程")
    @PutMapping
    JsonWrapper<CourseDTO> updateByNumber(String originalNumber, ModifiableCourseDTO modifiableCourseDTO) {
        return new JsonWrapper<>(courseService.updateByNumber(originalNumber, modifiableCourseDTO));
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
    JsonWrapper<List<CourseScheduleDTO>> assignCourseForTeacher(CourseAssignDTO courseAssignDTO) {
        return new JsonWrapper<>(courseDetailService.assignCourseForTeacher(courseAssignDTO));
    }

    @ApiOperation("分配课程前的检查")
    @GetMapping("before-assign")
    JsonWrapper<Boolean> checkBeforeAssign(CourseAssignDTO courseAssignDTO){
        return new JsonWrapper<>(courseDetailService.checkBeforeAssign(courseAssignDTO));
    }

    @ApiOperation("获取授课表")
    @GetMapping("teacher/schedule")
    JsonWrapper<List<CourseScheduleDTO>> getCourseScheduleByTeacherNumber(String teacherNumber) {
        return new JsonWrapper<>(courseDetailService.getCourseScheduleByTeacherNumber(teacherNumber));
    }

    @ApiOperation("获取教室课表")
    @GetMapping("classroom/schedule")
    JsonWrapper<List<CourseScheduleDTO>> getCourseScheduleByClassroomNumber(String classroomNumber) {
        return new JsonWrapper<>(courseDetailService.getCourseScheduleByClassroomNumber(classroomNumber));
    }
}
