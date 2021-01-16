package com.hubu.aspirin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hubu.aspirin.core.Result;
import com.hubu.aspirin.model.dto.*;
import com.hubu.aspirin.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "教师")
//@RequiresRoles("teacher")
@RequestMapping("api/teacher")
@RestController
public class TeacherController {
    @Autowired
    TeacherService teacherService;


    @ApiOperation("获取自身个人信息")
    @GetMapping("information")
    Result<TeacherDTO> getInformation() {
        return new Result<>(teacherService.getInformation());
    }

    @ApiOperation("修改个人信息")
    @PutMapping("Information")
    Result<TeacherDTO> updateInformation(TeacherModifiableDTO dto) {
        return new Result<>(teacherService.updateInformation(dto));
    }

    @ApiOperation("授课表")
    @GetMapping("teaching-courses")
    Result<List<TeacherCourseDTO>> getTeachesCourseDtoList() {
        return new Result<>(teacherService.getTeachesCourseDtoList());
    }


    @ApiOperation("打分页面")
    @GetMapping("mark-page")
    Result<IPage<MarkOutputDTO>> getMarkStudentPage(Integer current, Integer size, Long courseDetailId) {
        return new Result<>(teacherService.getMarkStudentPage(current, size, courseDetailId));
    }

    @ApiOperation("打分")
    @PostMapping("mark")
    Result<Boolean> markCourseList(MarkInputDTO input) {
        return new Result<>(teacherService.markCourse(input));
    }

    @ApiOperation("修改打分")
    @PutMapping("mark")
    Result<Boolean> updateMarkCourseList(MarkInputDTO input) {
        return new Result<>(teacherService.updateMarkCourse(input));
    }

    @ApiOperation("提交打分")
    @PutMapping("submit-mark")
    Result<Boolean> submitMarkList(Long gradeId) {
        return new Result<>(teacherService.submitMark(gradeId));
    }

}
