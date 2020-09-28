package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.model.dto.AdministratorDTO;
import com.hubu.aspirin.model.dto.ModifiableAdministratorDTO;
import com.hubu.aspirin.model.dto.TeacherDTO;
import com.hubu.aspirin.model.dto.TeacherManagementDTO;
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
}
