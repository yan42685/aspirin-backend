package com.hubu.aspirin.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "教师")
//@RequiresRoles("teacher")
@RequestMapping("api/teacher")
@RestController
public class TeacherController {
}
