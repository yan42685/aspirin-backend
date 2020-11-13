package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.converter.SpecialtyConverter;
import com.hubu.aspirin.mapper.SpecialtyMapper;
import com.hubu.aspirin.mapper.StudentMapper;
import com.hubu.aspirin.model.dto.SpecialtyDTO;
import com.hubu.aspirin.model.entity.Specialty;
import com.hubu.aspirin.model.entity.Student;
import com.hubu.aspirin.service.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialtyServiceImpl extends ServiceImpl<SpecialtyMapper, Specialty> implements SpecialtyService {
    @Autowired
    SpecialtyMapper specialtyMapper;
    @Autowired
    StudentMapper studentMapper;

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

    @Override
    public List<Integer> getAllAdmissionYear(String number) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<Student>().select("distinct admission_year").eq("specialty_number", number);
        List<Student> students = studentMapper.selectList(queryWrapper);
        return students.stream().map(Student::getAdmissionYear).collect(Collectors.toList());
    }
}
