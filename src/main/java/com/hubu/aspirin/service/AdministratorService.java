package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.model.dto.AdministratorDTO;
import com.hubu.aspirin.model.dto.ModifiableAdministratorDTO;
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
     * 添加教师
     */
    boolean addTeacher(TeacherManagementDTO teacherManagementDTO);

    boolean updateTeacher(TeacherManagementDTO teacherManagementDTO, String originalNumber);

}
