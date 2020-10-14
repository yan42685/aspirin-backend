package com.hubu.aspirin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.model.dto.*;
import com.hubu.aspirin.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "教师")
//@RequiresRoles("teacher")
@RequestMapping("api/teacher")
@RestController
public class TeacherController {
    @Autowired
    TeacherService teacherService;


    @ApiOperation("获取自身个人信息")
    @GetMapping("information")
    JsonWrapper<TeacherDTO> getInformation() {
        return new JsonWrapper<>(teacherService.getInformation());
    }

    @ApiOperation("修改个人信息")
    @PutMapping("Information")
    JsonWrapper<TeacherDTO> updateInformation(TeacherModifiableDTO dto) {
        return new JsonWrapper<>(teacherService.updateInformation(dto));
    }

    @ApiOperation("打分页面")
    @GetMapping("mark-page")
    JsonWrapper<IPage<MarkOutputDTO>> getMarkStudentPage(Integer current, Integer size, Long courseDetailId) {
        return new JsonWrapper<>(teacherService.getMarkStudentPage(current, size, courseDetailId));
    }

    @ApiOperation("打分")
    @PostMapping("mark")
    JsonWrapper<Boolean> markCourseList(MarkInputDTO input) {
        return new JsonWrapper<>(teacherService.markCourse(input));
    }

    @ApiOperation("修改打分")
    @PutMapping("mark")
    JsonWrapper<Boolean> updateMarkCourseList(MarkUpdateDTO input) {
        return new JsonWrapper<>(teacherService.updateMarkCourse(input));
    }

    @ApiOperation("提交打分")
    @PutMapping("submit-mark")
    JsonWrapper<Boolean> submitMarkList(Long gradeId) {
        return new JsonWrapper<>(teacherService.submitMark(gradeId));
    }

}
