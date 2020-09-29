package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.model.dto.*;
import com.hubu.aspirin.model.entity.Administrator;

public interface AdministratorService extends IService<Administrator> {
    /**
     * 获取管理员个人信息
     */
    AdministratorDTO getAdministratorInformation();

    /**
     * 修改管理员个人信息
     */
    boolean updateAdministratorInformation(ModifiableAdministratorDTO newInformation);

    /**
     * 根据编号或真名搜索教师
     *
     * @param current     当前页数
     * @param size        每页多少行
     * @param queryString 查询字符串
     */
    IPage<TeacherDTO> pageByNumberOrRealName(Integer current, Integer size, String queryString);

    /**
     * 查看教师信息
     */
    TeacherDTO getTeacher(String number);

    /**
     * 添加教师
     */
    TeacherDTO addTeacher(TeacherManagementDTO teacherManagementDTO);

    /**
     * 更新教师信息
     */
    TeacherDTO updateTeacher(TeacherManagementDTO teacherManagementDTO, String originalNumber);

    /**
     * 删除教师
     */
    boolean deleteTeacher(String number);


    /**
     * 查看学生信息
     */
    StudentDTO getStudent(String number);

    /**
     * 添加学生
     */
    StudentDTO addStudent(StudentManagementDTO dto);

    /**
     * 更新学生信息
     */
    StudentDTO updateStudent(StudentManagementDTO dto, String originalNumber);

    /**
     * 删除学生
     */
    boolean deleteStudent(String number);
}
