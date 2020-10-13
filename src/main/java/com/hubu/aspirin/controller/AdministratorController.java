package com.hubu.aspirin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.enums.ApplicationSwitchEnum;
import com.hubu.aspirin.model.dto.*;
import com.hubu.aspirin.service.AdministratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@RequiresRoles("administrator")
@Api(tags = "管理员")
@RequestMapping("api/administrator")
@RestController
public class AdministratorController {
    @Autowired
    AdministratorService administratorService;

    @ApiOperation(value = "根据工号获取管理员信息")
    @GetMapping("info-by-number")
    JsonWrapper<AdministratorDTO> getAdministratorDtoByNumber(String number) {
        return new JsonWrapper<>(administratorService.getAdministratorDtoByNumber(number));
    }

    @ApiOperation("获取个人信息")
    @GetMapping("information")
    JsonWrapper<AdministratorDTO> getAccountInfo() {
        return new JsonWrapper<>(administratorService.getAdministratorInformation());
    }

    @ApiOperation("修改个人信息")
    @PutMapping("information")
    JsonWrapper<AdministratorDTO> updateAdministratorInformation(AdministratorModifiableDTO newInformation) {
        return new JsonWrapper<>(administratorService.updateAdministratorInformation(newInformation));
    }

    @ApiOperation(value = "添加教师", notes = "默认用户名为教师编号， 默认密码为123456")
    @PostMapping("information/teacher")
    JsonWrapper<TeacherDTO> addTeacher(TeacherManagementDTO teacherManagementDTO) {
        return new JsonWrapper<>(administratorService.addTeacher(teacherManagementDTO));
    }

    @ApiOperation(value = "分页搜索教师", notes = "根据number或realName模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页数", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页多少行", dataType = "int"),
            @ApiImplicitParam(name = "numberOrRealName", value = "编号或真名", dataType = "string")
    })
    @GetMapping("information/teacher-page")
    JsonWrapper<IPage<TeacherDTO>> getTeacherPage(Integer current, Integer size, String numberOrRealName) {
        return new JsonWrapper<>(administratorService.pageTeacher(current, size, numberOrRealName));
    }


    @ApiOperation(value = "修改教师信息")
    @ApiImplicitParam(name = "originalNumber", value = "原来的教师编号", dataType = "string")
    @PutMapping("information/teacher")
    JsonWrapper<TeacherDTO> updateTeacher(String originalNumber, TeacherManagementDTO teacherManagementDTO) {
        return new JsonWrapper<>(administratorService.updateTeacher(originalNumber, teacherManagementDTO));
    }

    @ApiOperation(value = "删除教师")
    @ApiImplicitParam(name = "number", value = "教师编号", dataType = "string")
    @DeleteMapping("information/teacher")
    JsonWrapper<Boolean> updateTeacher(String number) {
        return new JsonWrapper<>(administratorService.deleteTeacher(number));
    }

    @ApiOperation(value = "添加学生", notes = "默认用户名为学号， 默认密码为123456")
    @PostMapping("information/student")
    JsonWrapper<StudentDTO> addStudent(StudentManagementDTO studentManagementDTO) {
        return new JsonWrapper<>(administratorService.addStudent(studentManagementDTO));
    }


    @ApiOperation(value = "分页搜索学生")
    @GetMapping("information/student-page")
    JsonWrapper<IPage<StudentDTO>> getStudentPage(Integer current, Integer size, StudentQueryDTO studentQueryDTO) {
        return new JsonWrapper<>(administratorService.pageStudent(current, size, studentQueryDTO));
    }

    @ApiOperation(value = "修改学生信息")
    @ApiImplicitParam(name = "originalNumber", value = "原来的学号", dataType = "string")
    @PutMapping("information/student")
    JsonWrapper<StudentDTO> updateStudent(String originalNumber, StudentManagementDTO studentManagementDTO) {
        return new JsonWrapper<>(administratorService.updateStudent(originalNumber, studentManagementDTO));
    }

    @ApiOperation(value = "删除学生")
    @ApiImplicitParam(name = "number", value = "学号", dataType = "string")
    @DeleteMapping("information/student")
    JsonWrapper<Boolean> updateStudent(String number) {
        return new JsonWrapper<>(administratorService.deleteStudent(number));
    }

    @ApiOperation(value = "发布公告")
    @PostMapping("bulletin")
    JsonWrapper<Boolean> sendBulletin(String title, String content) {
        return new JsonWrapper<>(administratorService.sendBulletin(title, content));
    }

    @ApiOperation(value = "更新公告")
    @PutMapping("bulletin")
    JsonWrapper<BulletinDTO> updateBulletin(Long id, String title, String content) {
        return new JsonWrapper<>(administratorService.updateBulletin(id, title, content));
    }

    @ApiOperation(value = "删除公告")
    @DeleteMapping("bulletin")
    JsonWrapper<Boolean> deleteBulletin(Long id) {
        return new JsonWrapper<>(administratorService.deleteBulletin(id));
    }

    @ApiOperation("翻转应用开关")
    @PutMapping("app-switch")
    JsonWrapper<ApplicationSwitchDTO> flipApplicationVariable(ApplicationSwitchEnum switchEnum) {
        return new JsonWrapper<>(administratorService.flipApplicationVariable(switchEnum));
    }

    @ApiOperation("查看应用开关状态")
    @GetMapping("app-switch")
    JsonWrapper<Boolean> getApplicationSwitchStatus(ApplicationSwitchEnum switchEnum) {
        return new JsonWrapper<>(administratorService.getApplicationSwitchStatus(switchEnum));
    }
}
