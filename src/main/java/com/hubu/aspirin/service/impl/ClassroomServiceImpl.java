package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.converter.ClassroomConverter;
import com.hubu.aspirin.mapper.ClassroomMapper;
import com.hubu.aspirin.model.dto.ClassroomDTO;
import com.hubu.aspirin.model.entity.Classroom;
import com.hubu.aspirin.service.ClassroomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl extends ServiceImpl<ClassroomMapper, Classroom> implements ClassroomService {

    @Override
    public List<ClassroomDTO> getList() {
        return ClassroomConverter.INSTANCE.entityToDtoList(list());
    }
}
