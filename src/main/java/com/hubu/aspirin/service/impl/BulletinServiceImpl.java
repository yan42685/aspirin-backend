package com.hubu.aspirin.service.impl;

import com.hubu.aspirin.model.entity.Bulletin;
import com.hubu.aspirin.mapper.BulletinMapper;
import com.hubu.aspirin.service.BulletinService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BulletinServiceImpl extends ServiceImpl<BulletinMapper, Bulletin> implements BulletinService {

}
