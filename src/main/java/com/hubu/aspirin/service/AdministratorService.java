package com.hubu.aspirin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hubu.aspirin.model.dto.AdministratorDTO;
import com.hubu.aspirin.model.entity.Administrator;

public interface AdministratorService extends IService<Administrator> {
    public AdministratorDTO getInformation();
}
