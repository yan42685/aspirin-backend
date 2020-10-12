package com.hubu.aspirin.controller;

import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.enums.CourseTypeEnum;
import com.hubu.aspirin.model.dto.CourseDTO;
import com.hubu.aspirin.model.dto.CourseDetailDTO;
import com.hubu.aspirin.model.dto.ModifiableCourseDTO;
import com.hubu.aspirin.model.entity.Course;
import com.hubu.aspirin.service.CourseDetailService;
import com.hubu.aspirin.service.StudentService;
import com.sun.jmx.snmp.agent.SnmpUserDataFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "学生")
//@RequiresRoles("student")
@RequestMapping("api/student")
@RestController
public class StudentController {
    @Autowired
    StudentService studentService;


    @ApiOperation("分类查看课程")
    @GetMapping("available-course-list")
    JsonWrapper<List<CourseDetailDTO>> availableCourseDetailList(CourseTypeEnum courseType) {
        return new JsonWrapper<>(studentService.availableCourseDetailList(courseType));
    }
}
