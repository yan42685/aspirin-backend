package com.hubu.aspirin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hubu.aspirin.core.Result;
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
    Result<StudentDTO> getInformation() {
        return new Result<>(studentService.getInformation());
    }

    @ApiOperation("修改个人信息")
    @PutMapping("information")
    Result<StudentDTO> updateInformation(StudentModifiableDTO dto) {
        return new Result<>(studentService.updateInformation(dto));
    }

    @ApiOperation("分类查看选课表")
    @GetMapping("available-course-list")
    Result<List<ElectiveDTO>> availableElectiveList(Integer semester, CourseTypeEnum courseType) {
        return new Result<>(studentService.availableElectiveList(semester, courseType));
    }

    @ApiOperation(value = "获取课程表")
    @GetMapping("course-schedule")
    Result<List<CourseDetailDTO>> getCourseSchedule(Integer semester) {
        return new Result<>(studentService.getCourseSchedule(semester));
    }

    @ApiOperation(value = "选课", notes = "返回课程表")
    @PostMapping("elective")
    Result<List<ElectiveDTO>> electCourse(Long courseDetailId) {
        return new Result<>(studentService.electCourse(courseDetailId));
    }

    @ApiOperation("退课")
    @DeleteMapping("elective")
    Result<List<ElectiveDTO>> dropCourse(Long courseDetailId) {
        return new Result<>(studentService.dropCourse(courseDetailId));
    }

    @ApiOperation("查看退课记录")
    @GetMapping("course-drop-record")
    Result<IPage<CourseDropDTO>> pageCourseDropRecord(Integer current, Integer size) {
        return new Result<>(studentService.pageCourseDropRecord(current, size));
    }

    @ApiOperation("查看成绩")
    @GetMapping("grade")
    Result<IPage<GradeDTO>> pageGrade(Integer current, Integer size, Integer semester) {
        return new Result<>(studentService.pageGrade(current, size, semester));
    }
}
