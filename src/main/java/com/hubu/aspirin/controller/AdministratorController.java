package com.hubu.aspirin.controller;

import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.model.dto.*;
import com.hubu.aspirin.service.AdministratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiresRoles("administrator")
@Api(tags = "管理员")
@RequestMapping("api/administrator")
@RestController
public class AdministratorController {
    @Autowired
    AdministratorService administratorService;

    @ApiOperation("获取个人信息")
    @GetMapping("information")
    public JsonWrapper<AdministratorDTO> getAccountInfo() {
        return new JsonWrapper<>(administratorService.getAdministratorInformation());
    }

    @ApiOperation("修改个人信息")
    @PutMapping("information")
    public JsonWrapper<Boolean> updateAdministratorInformation(ModifiableAdministratorDTO newInformation) {
        return new JsonWrapper<>(administratorService.updateAdministratorInformation(newInformation));
    }

    @ApiOperation(value = "添加教师", notes = "默认用户名为教师编号， 默认密码为123456")
    @PostMapping("information/teacher")
    public JsonWrapper<TeacherDTO> addTeacher(TeacherManagementDTO teacherManagementDTO) {
        return new JsonWrapper<>(administratorService.addTeacher(teacherManagementDTO));
    }

    @ApiOperation(value = "查看教师信息")
    @GetMapping("information/teacher")
    public JsonWrapper<TeacherDTO> getTeacher(String number) {
        return new JsonWrapper<>(administratorService.getTeacher(number));
    }


    @ApiOperation(value = "修改教师信息")
    @ApiImplicitParam(name = "originalNumber", value = "原来的教师编号", dataType = "string")
    @PutMapping("information/teacher")
    public JsonWrapper<TeacherDTO> updateTeacher(TeacherManagementDTO teacherManagementDTO, String originalNumber) {
        return new JsonWrapper<>(administratorService.updateTeacher(teacherManagementDTO, originalNumber));
    }

    @ApiOperation(value = "删除教师")
    @ApiImplicitParam(name = "number", value = "教师编号", dataType = "string")
    @DeleteMapping("information/teacher")
    public JsonWrapper<Boolean> updateTeacher(String number) {
        return new JsonWrapper<>(administratorService.deleteTeacher(number));
    }

    @ApiOperation(value = "添加学生", notes = "默认用户名为学号， 默认密码为123456")
    @PostMapping("information/student")
    public JsonWrapper<StudentDTO> addStudent(StudentManagementDTO studentManagementDTO) {
        return new JsonWrapper<>(administratorService.addStudent(studentManagementDTO));
    }

    @ApiOperation(value = "查看学生信息")
    @GetMapping("information/student")
    public JsonWrapper<StudentDTO> getStudent(String number) {
        return new JsonWrapper<>(administratorService.getStudent(number));
    }

    @ApiOperation(value = "修改学生信息")
    @ApiImplicitParam(name = "originalNumber", value = "原来的学号", dataType = "string")
    @PutMapping("information/student")
    public JsonWrapper<StudentDTO> updateStudent(StudentManagementDTO studentManagementDTO, String originalNumber) {
        return new JsonWrapper<>(administratorService.updateStudent(studentManagementDTO, originalNumber));
    }

    @ApiOperation(value = "删除学生")
    @ApiImplicitParam(name = "number", value = "学号", dataType = "string")
    @DeleteMapping("information/student")
    public JsonWrapper<Boolean> updateStudent(String number) {
        return new JsonWrapper<>(administratorService.deleteStudent(number));
    }

}
