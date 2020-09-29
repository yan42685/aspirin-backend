package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.converter.TeacherConverter;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.hubu.aspirin.enums.RoleEnum;
import com.hubu.aspirin.mapper.TeacherMapper;
import com.hubu.aspirin.model.dto.TeacherDTO;
import com.hubu.aspirin.model.entity.Teacher;
import com.hubu.aspirin.model.entity.User;
import com.hubu.aspirin.service.TeacherService;
import com.hubu.aspirin.util.UserUtils;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Override
    public TeacherDTO getInformationByNumber(String number) {
        User user = UserUtils.getByUsernameAndRole(number, RoleEnum.TEACHER);
        if (user == null) {
            throw new KnownException(ExceptionEnum.USER_NOT_EXISTS);
        }
        Teacher teacher = getById(user.getId());
        return TeacherConverter.INSTANCE.entityToDto(teacher);
    }

    @Override
    public TeacherDTO getInformation() {
        Long id = UserUtils.getCurrentUser().getId();
        String number = getById(id).getNumber();
        return getInformationByNumber(number);
    }
}
