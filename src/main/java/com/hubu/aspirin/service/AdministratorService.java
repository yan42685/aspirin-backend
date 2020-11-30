package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.enums.ApplicationSwitchEnum;
import com.hubu.aspirin.enums.RoleEnum;
import com.hubu.aspirin.model.dto.*;
import com.hubu.aspirin.model.entity.Administrator;

public interface AdministratorService extends IService<Administrator> {
    /**
     * 根据工号获取管理员信息
     */
    AdministratorDTO getAdministratorDtoByNumber(String number);

    /**
     * 获取管理员个人信息
     */
    AdministratorDTO getAdministratorInformation();

    /**
     * 修改管理员个人信息
     */
    AdministratorDTO updateAdministratorInformation(AdministratorModifiableDTO newInformation);

    /**
     * 根据编号或真名搜索教师
     */
    IPage<TeacherDTO> pageTeacher(Integer current, Integer size, String queryString);

    /**
     * 添加教师
     */
    TeacherDTO addTeacher(TeacherManagementDTO teacherManagementDTO);

    /**
     * 更新教师信息
     */
    TeacherDTO updateTeacher(String originalNumber, TeacherManagementDTO teacherManagementDTO);

    /**
     * 删除教师
     */
    boolean deleteTeacher(String number);

    /**
     * 根据学号或真名搜索学生
     */
    IPage<StudentDTO> pageStudent(Integer current, Integer size, StudentQueryDTO studentQueryDTO);

    /**
     * 添加学生
     */
    StudentDTO addStudent(StudentManagementDTO dto);

    /**
     * 更新学生信息
     */
    StudentDTO updateStudent(String originalNumber, StudentManagementDTO dto);

    /**
     * 删除学生
     */
    boolean deleteStudent(String number);

    /**
     * 发布公告
     */
    boolean sendBulletin(String title, String content);

    /**
     * 更新公告
     */
    BulletinDTO updateBulletin(Long id, String title, String content);

    /**
     * 删除公告
     */
    boolean deleteBulletin(Long id);

    /**
     * 翻转应用开关
     */
    ApplicationSwitchDTO flipApplicationVariable(ApplicationSwitchEnum switchEnum);

    /**
     * 查看开关状态
     */
    Boolean getApplicationSwitchStatus(ApplicationSwitchEnum switchEnum);

    boolean setUserPassword(RoleEnum role, String number, String rawPassword);
}

