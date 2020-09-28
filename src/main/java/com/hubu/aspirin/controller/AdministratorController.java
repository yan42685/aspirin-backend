package com.hubu.aspirin.controller;

import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.model.dto.AdministratorDTO;
import com.hubu.aspirin.model.dto.ModifiableAdministratorDTO;
import com.hubu.aspirin.model.dto.TeacherManagementDTO;
import com.hubu.aspirin.service.AdministratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("information/teacher")
    public JsonWrapper<Boolean> addTeacher(TeacherManagementDTO teacherManagementDTO) {
        return new JsonWrapper<>(administratorService.addTeacher(teacherManagementDTO));
    }
}
