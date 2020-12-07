package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.enums.CourseTypeEnum;
import com.hubu.aspirin.model.dto.*;
import com.hubu.aspirin.model.entity.Student;

import java.util.List;

public interface StudentService extends IService<Student> {

    /**
     * 获取自身个人信息
     */
    StudentDTO getInformation();

    /**
     * 修改个人信息
     */
    StudentDTO updateInformation(StudentModifiableDTO dto);

    /**
     * 根据编号获取获取DTO
     */
    StudentDTO getDtoByNumber(String number);


    /**
     * 根据真名和学号搜索
     */
    IPage<StudentDTO> pageByQueryDto(Integer current, Integer size, StudentQueryDTO studentQueryDTO);

    /**
     * 分类查看可选课程
     */
    List<ElectiveDTO> availableElectiveList(Integer semester, CourseTypeEnum courseType);

    /**
     * 获取课程表
     */
    List<CourseDetailDTO> getCourseSchedule(Integer semester);

    /**
     * 选课
     */
    List<ElectiveDTO> electCourse(Long courseDetailId);

    /**
     * 退课
     */
    List<ElectiveDTO> dropCourse(Long courseDetailId);


    /**
     * 查看退课记录
     */
    IPage<CourseDropDTO> pageCourseDropRecord(Integer current, Integer size);

    /**
     * 查看成绩
     */
    IPage<GradeDTO> pageGrade(Integer current, Integer size, Integer semester);

//    /** 查看所有成绩 */
//    List<List<GradeDTO>>
}
