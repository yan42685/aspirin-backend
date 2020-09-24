package com.hubu.aspirin.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiresRoles("teacher")
@Api(tags = "教师")
@RequestMapping("api/teacher")
@RestController
public class TeacherController {
}
