package com.hubu.aspirin.service.impl;

import com.hubu.aspirin.model.entity.Administrator;
import com.hubu.aspirin.mapper.AdministratorMapper;
import com.hubu.aspirin.service.AdministratorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements AdministratorService {

}
