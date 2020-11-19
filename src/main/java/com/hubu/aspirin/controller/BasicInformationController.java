package com.hubu.aspirin.controller;


import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.enums.CourseTypeEnum;
import com.hubu.aspirin.enums.RoleEnum;
import com.hubu.aspirin.model.dto.*;
import com.hubu.aspirin.service.*;
import com.hubu.aspirin.util.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author alex
 */
@Api(tags = "基础信息")
@RequestMapping("api/basic-info")
@RestController
public class BasicInformationController {
    @Autowired
    FacultyService facultyService;
    @Autowired
    SpecialtyService specialtyService;
    @Autowired
    ClassroomService classroomService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    @ApiOperation(value = "获取所有学院信息")
    @GetMapping("faculty-names")
    public JsonWrapper<List<FacultyDTO>> getAllFaculty() {
        return new JsonWrapper<>(facultyService.getList());
    }

    @ApiOperation(value = "获取所有专业信息")
    @GetMapping("specialty-names")
    public JsonWrapper<List<SpecialtyDTO>> getAllSpecialty() {
        return new JsonWrapper<>(specialtyService.getList());
    }

    @ApiOperation(value = "获取学院包含的专业")
    @GetMapping("faculty/specialty-names")
    public JsonWrapper<List<SpecialtyDTO>> getAllSpecialtyByFacultyNumber(String facultyNumber) {
        return new JsonWrapper<>(specialtyService.getListByFacultyNumber(facultyNumber));
    }

    @ApiOperation(value = "获取专业所有入学年份")
    @GetMapping("specialty/admission-years")
    public JsonWrapper<List<Integer>> getAllAdmissionYearBySpecialtyNumber(String specialtyNumer) {
        return new JsonWrapper<>(specialtyService.getAllAdmissionYear(specialtyNumer));
    }

    @ApiOperation("获取所有教室信息")
    @GetMapping("classroom/list")
    public JsonWrapper<List<ClassroomDTO>> getClassroomList() {
        return new JsonWrapper<>(classroomService.getList());
    }

    @ApiOperation("判断用户是否存在")
    @GetMapping("user/is-existed")
    public JsonWrapper<Boolean> isUserExisted(String number, RoleEnum role) {
        boolean isUserExisted = UserUtils.getByNumberAndRole(number, role) != null;
        return new JsonWrapper<>(isUserExisted);
    }

    @ApiOperation("根据学号获取学生信息")
    @GetMapping("student")
    public JsonWrapper<StudentDTO> getStudentByNumber(String number) {
        return new JsonWrapper<>(studentService.getDtoByNumber(number));
    }

    @ApiOperation("根据工号获取教师信息")
    @GetMapping("teacher")
    public JsonWrapper<TeacherDTO> getTeacherByNumber(String number) {
        return new JsonWrapper<>(teacherService.getDtoByNumber(number));
    }

    @ApiOperation("获取所有课程类型")
    @GetMapping("course-type/list")
    public JsonWrapper<List<CourseTypeEnum>> getAllCourseType() {
        return new JsonWrapper<>(Arrays.asList(CourseTypeEnum.values()));
    }
}
