package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.mapper.FacultyMapper;
import com.hubu.aspirin.model.entity.Faculty;
import com.hubu.aspirin.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class
FacultyServiceImpl extends ServiceImpl<FacultyMapper, Faculty> implements FacultyService {
    @Autowired
    FacultyMapper facultyMapper;

    @Override
    public String getNumberByName(String name) {
        QueryWrapper<Faculty> queryWrapper = new QueryWrapper<Faculty>().eq("name", name);
        Faculty faculty = getOne(queryWrapper);
        return faculty.getNumber();
    }

    @Override
    public List<String> getAllNames() {
        return facultyMapper.getAllNames();
    }
}
