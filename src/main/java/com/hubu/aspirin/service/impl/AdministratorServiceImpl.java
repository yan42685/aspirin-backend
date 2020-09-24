package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.converter.AdministratorConverter;
import com.hubu.aspirin.mapper.AdministratorMapper;
import com.hubu.aspirin.model.dto.AdministratorDTO;
import com.hubu.aspirin.model.dto.ModifiableAdministratorDTO;
import com.hubu.aspirin.model.entity.Administrator;
import com.hubu.aspirin.service.AdministratorService;
import com.hubu.aspirin.util.UserUtils;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements AdministratorService {

    @Override
    public AdministratorDTO getInformation() {
        Administrator administrator = getCurrentAdministrator();
        return AdministratorConverter.INSTANCE.entityToDto(administrator);
    }

    @Override
    public boolean updateInformation(ModifiableAdministratorDTO newInformation) {
        Administrator administrator = getCurrentAdministrator();
        AdministratorConverter.INSTANCE.updateEntityFromDto(newInformation, administrator);
        updateById(administrator);
        return true;
    }

    public Administrator getCurrentAdministrator() {
        String username = UserUtils.getCurrentUsername();
        return getOne(new QueryWrapper<Administrator>().eq("username", username));
    }

}
