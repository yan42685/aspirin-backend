package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.converter.AdministratorConverter;
import com.hubu.aspirin.mapper.AdministratorMapper;
import com.hubu.aspirin.model.dto.AdministratorDTO;
import com.hubu.aspirin.model.entity.Administrator;
import com.hubu.aspirin.service.AdministratorService;
import com.hubu.aspirin.util.UserUtils;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements AdministratorService {

    @Override
    public AdministratorDTO getInformation() {
        String username = UserUtils.getCurrentUsername();
        Administrator administrator = getOne(new QueryWrapper<Administrator>().eq("username", username));
        return AdministratorConverter.INSTANCE.entityToDto(administrator);
    }
}
