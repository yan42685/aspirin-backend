package com.hubu.aspirin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.enums.CourseTypeEnum;
import com.hubu.aspirin.model.dto.CourseDTO;
import com.hubu.aspirin.model.dto.CourseDetailDTO;
import com.hubu.aspirin.model.dto.CourseDropDTO;
import com.hubu.aspirin.model.dto.ModifiableCourseDTO;
import com.hubu.aspirin.model.entity.Course;
import com.hubu.aspirin.service.CourseDetailService;
import com.hubu.aspirin.service.StudentService;
import com.sun.jmx.snmp.agent.SnmpUserDataFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "学生")
//@RequiresRoles("student")
@RequestMapping("api/student")
@RestController
public class StudentController {
    @Autowired
    StudentService studentService;


    @ApiOperation("分类查看可上课程")
    @GetMapping("available-course-list")
    JsonWrapper<List<CourseDetailDTO>> availableCourseDetailList(Integer semester, CourseTypeEnum courseType) {
        return new JsonWrapper<>(studentService.availableCourseDetailList(semester, courseType));
    }

    @ApiOperation(value = "获取课程表")
    @GetMapping("course-schedule")
    JsonWrapper<List<CourseDetailDTO>> getCourseSchedule(Integer semester) {
        return new JsonWrapper<>(studentService.getCourseSchedule(semester));
    }

    @ApiOperation("选课")
    @PostMapping("elective")
    JsonWrapper<List<CourseDetailDTO>> electCourse(Long courseDetailId) {
        return new JsonWrapper<>(studentService.electCourse(courseDetailId));
    }

    @ApiOperation("退课")
    @DeleteMapping("elective")
    JsonWrapper<List<CourseDetailDTO>> dropCourse(Long courseDetailId) {
        return new JsonWrapper<>(studentService.dropCourse(courseDetailId));
    }

    @ApiOperation("查看退课记录")
    @GetMapping("course-drop-record")
    JsonWrapper<IPage<CourseDropDTO>> pageCourseDropRecord(Integer current, Integer size) {
        return new JsonWrapper<>(studentService.pageCourseDropRecord(current, size));
    }

}
