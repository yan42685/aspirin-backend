package com.hubu.aspirin.controller;


import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.service.FacultyService;
import com.hubu.aspirin.service.SpecialtyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation(value = "获取所有学院名")
    @GetMapping("faculty-names")
    public JsonWrapper<List<String>> getAllFacultyNames() {
        return new JsonWrapper<>(facultyService.getAllNames());
    }

    @ApiOperation(value = "获取所有专业名")
    @GetMapping("specialty-names")
    public JsonWrapper<List<String>> getAllSpecialtyNames() {
        return new JsonWrapper<>(specialtyService.getAllNames());
    }

    @ApiOperation(value = "获取学院包含的专业名")
    @GetMapping("faculty/specialty-names")
    public JsonWrapper<List<String>> getAllSpecialtyNamesByFacultyName(String facultyName) {
        return new JsonWrapper<>(specialtyService.getAllNamesByFacultyName(facultyName));
    }
}
