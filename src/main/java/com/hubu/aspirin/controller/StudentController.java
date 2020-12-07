package com.hubu.aspirin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.enums.CourseTypeEnum;
import com.hubu.aspirin.model.dto.*;
import com.hubu.aspirin.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("获取个人信息")
    @GetMapping("information")
    JsonWrapper<StudentDTO> getInformation() {
        return new JsonWrapper<>(studentService.getInformation());
    }

    @ApiOperation("修改个人信息")
    @PutMapping("information")
    JsonWrapper<StudentDTO> updateInformation(StudentModifiableDTO dto) {
        return new JsonWrapper<>(studentService.updateInformation(dto));
    }

    @ApiOperation("分类查看选课表")
    @GetMapping("available-course-list")
    JsonWrapper<List<ElectiveDTO>> availableElectiveList(Integer semester, CourseTypeEnum courseType) {
        return new JsonWrapper<>(studentService.availableElectiveList(semester, courseType));
    }

    @ApiOperation(value = "获取课程表")
    @GetMapping("course-schedule")
    JsonWrapper<List<CourseDetailDTO>> getCourseSchedule(Integer semester) {
        return new JsonWrapper<>(studentService.getCourseSchedule(semester));
    }

    @ApiOperation(value = "选课", notes = "返回课程表")
    @PostMapping("elective")
    JsonWrapper<List<ElectiveDTO>> electCourse(Long courseDetailId) {
        return new JsonWrapper<>(studentService.electCourse(courseDetailId));
    }

    @ApiOperation("退课")
    @DeleteMapping("elective")
    JsonWrapper<List<ElectiveDTO>> dropCourse(Long courseDetailId) {
        return new JsonWrapper<>(studentService.dropCourse(courseDetailId));
    }

    @ApiOperation("查看退课记录")
    @GetMapping("course-drop-record")
    JsonWrapper<IPage<CourseDropDTO>> pageCourseDropRecord(Integer current, Integer size) {
        return new JsonWrapper<>(studentService.pageCourseDropRecord(current, size));
    }

    @ApiOperation("查看成绩")
    @GetMapping("grade")
    JsonWrapper<IPage<GradeDTO>> pageGrade(Integer current, Integer size, Integer semester) {
        return new JsonWrapper<>(studentService.pageGrade(current, size, semester));
    }
}
