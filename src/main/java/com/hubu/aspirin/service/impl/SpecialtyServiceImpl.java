package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.converter.SpecialtyConverter;
import com.hubu.aspirin.mapper.SpecialtyMapper;
import com.hubu.aspirin.model.dto.SpecialtyDTO;
import com.hubu.aspirin.model.entity.Specialty;
import com.hubu.aspirin.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyServiceImpl extends ServiceImpl<SpecialtyMapper, Specialty> implements SpecialtyService {
    @Autowired
    SpecialtyMapper specialtyMapper;

    @Override
    public String getNumberByName(String name) {
        QueryWrapper<Specialty> queryWrapper = new QueryWrapper<Specialty>().eq("name", name);
        Specialty specialty = getOne(queryWrapper);
        return specialty.getNumber();
    }

    @Override
    public List<SpecialtyDTO> getList() {
        List<Specialty> specialtyList = list();
        return SpecialtyConverter.INSTANCE.entityToDtoList(specialtyList);
    }

    @Override
    public List<SpecialtyDTO> getListByFacultyNumber(String facultyNumber) {
        List<Specialty> specialtyList = specialtyMapper.getListByFacultyNumber(facultyNumber);
        return SpecialtyConverter.INSTANCE.entityToDtoList(specialtyList);
    }
}
