package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.model.dto.AdministratorDTO;
import com.hubu.aspirin.model.dto.ModifiableAdministratorDTO;
import com.hubu.aspirin.model.entity.Administrator;

public interface AdministratorService extends IService<Administrator> {
    /**
     * 获取个人信息
     */
    AdministratorDTO getInformation();

    /**
     * 修改个人信息
     */
    boolean updateInformation(ModifiableAdministratorDTO newInformation);

}
