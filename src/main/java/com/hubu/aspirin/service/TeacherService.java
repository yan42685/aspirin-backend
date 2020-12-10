package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.model.dto.*;
import com.hubu.aspirin.model.entity.Teacher;

import java.util.List;

public interface TeacherService extends IService<Teacher> {
    /**
     * 根据编号获取个人信息
     */
    TeacherDTO getDtoByNumber(String number);

    /**
     * 获取自身个人信息
     */
    TeacherDTO getInformation();

    /**
     * 修改个人信息
     */
    TeacherDTO updateInformation(TeacherModifiableDTO dto);

    /**
     * 获取所教课程
     */
    List<TeacherCourseDTO> getTeachesCourseDtoList();

    /**
     * 打分页面
     */
    IPage<MarkOutputDTO> getMarkStudentPage(Integer current, Integer size, Long courseDetailId);

    /**
     * 打分
     */
    boolean markCourse(MarkInputDTO input);

    /**
     * 修改打分
     */
    boolean updateMarkCourse(MarkInputDTO input);

    /**
     * 提交打分
     */
    boolean submitMark(Long gradeId);


}
