package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.enums.CourseTypeEnum;
import com.hubu.aspirin.enums.RoleEnum;
import com.hubu.aspirin.mapper.StudentMapper;
import com.hubu.aspirin.model.dto.CourseDetailDTO;
import com.hubu.aspirin.model.dto.StudentDTO;
import com.hubu.aspirin.model.dto.StudentQueryDTO;
import com.hubu.aspirin.model.entity.Student;
import com.hubu.aspirin.model.entity.User;
import com.hubu.aspirin.service.CourseDetailService;
import com.hubu.aspirin.service.StudentService;
import com.hubu.aspirin.util.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CourseDetailService courseDetailService;

    @Override
    public StudentDTO getInformation() {
        User user  = (User) SecurityUtils.getSubject().getPrincipal();
        Long id = user.getId();
        String number = getById(id).getNumber();
        return getDtoByNumber(number);
    }

    @Override
    public StudentDTO getDtoByNumber(String number) {
        return studentMapper.getDtoByNumber(number);
    }

    @Override
    public StudentDTO getDtoById(Long id) {
        return studentMapper.getDtoById(id);
    }

    @Override
    public IPage<StudentDTO> pageByQueryDto(Integer current, Integer size, StudentQueryDTO info) {
        Page<StudentDTO> page = new Page<>(current, size);
        return studentMapper.page(page, info);
    }

    @Override
    public List<CourseDetailDTO> availableCourseDetailList(CourseTypeEnum courseType) {
        String username = UserUtils.getCurrentUsername();
        Student student = getByUsername(username);
        String specialtyNumber = student.getSpecialtyNumber();
        return courseDetailService.listBySpecialtyNumberAndCourseType(specialtyNumber, courseType);
    }

    private Student getByUsername(String username) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<Student>().eq("username", username);
        return getOne(queryWrapper);
    }

}
