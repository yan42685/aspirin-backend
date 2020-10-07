package com.hubu.aspirin.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "学生")
//@RequiresRoles("student")
@RequestMapping("api/student")
@RestController
public class StudentController {
}
