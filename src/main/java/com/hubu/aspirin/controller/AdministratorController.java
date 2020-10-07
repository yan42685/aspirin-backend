package com.hubu.aspirin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.model.dto.*;
import com.hubu.aspirin.service.AdministratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@RequiresRoles("administrator")
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

    @ApiOperation(value = "分页搜索教师", notes = "根据number或realName模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页数", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页多少行", dataType = "int"),
            @ApiImplicitParam(name = "queryString", value = "编号或真名", dataType = "string")
    })
    @GetMapping("information/teacher-page")
    public JsonWrapper<IPage<TeacherDTO>> getTeacherPage(Integer current, Integer size, String queryString) {
        return new JsonWrapper<>(administratorService.pageTeacherByNumberOrRealName(current, size, queryString));
    }


    @ApiOperation(value = "修改教师信息")
    @ApiImplicitParam(name = "originalNumber", value = "原来的教师编号", dataType = "string")
    @PutMapping("information/teacher")
    public JsonWrapper<TeacherDTO> updateTeacher(String originalNumber, TeacherManagementDTO teacherManagementDTO) {
        return new JsonWrapper<>(administratorService.updateTeacher(originalNumber, teacherManagementDTO));
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

    @ApiOperation(value = "分页搜索学生", notes = "根据number或realName模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "当前页数", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页多少行", dataType = "int"),
            @ApiImplicitParam(name = "queryString", value = "学号或真名", dataType = "string")
    })
    @GetMapping("information/student-page")
    public JsonWrapper<IPage<StudentDTO>> getStudentPage(Integer current, Integer size, String queryString) {
        return new JsonWrapper<>(administratorService.pageStudentByNumberOrRealName(current, size, queryString));
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

    @ApiOperation(value = "发布公告")
    @PostMapping("bulletin")
    public JsonWrapper<Boolean> sendBulletin(String title, String content) {
        return new JsonWrapper<>(administratorService.sendBulletin(title, content));
    }

    @ApiOperation(value = "更新公告")
    @PutMapping("bulletin")
    public JsonWrapper<BulletinDTO> updateBulletin(Long id, String title, String content) {
        return new JsonWrapper<>(administratorService.updateBulletin(id, title, content));
    }

    @ApiOperation(value = "删除公告")
    @DeleteMapping("bulletin")
    public JsonWrapper<Boolean> deleteBulletin(Long id) {
        return new JsonWrapper<>(administratorService.deleteBulletin(id));
    }
}
