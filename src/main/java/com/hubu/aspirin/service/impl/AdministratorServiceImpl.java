package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.constant.AccountConstant;
import com.hubu.aspirin.converter.AdministratorConverter;
import com.hubu.aspirin.converter.TeacherConverter;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.hubu.aspirin.enums.RoleEnum;
import com.hubu.aspirin.mapper.AdministratorMapper;
import com.hubu.aspirin.model.dto.AdministratorDTO;
import com.hubu.aspirin.model.dto.ModifiableAdministratorDTO;
import com.hubu.aspirin.model.dto.TeacherManagementDTO;
import com.hubu.aspirin.model.entity.Administrator;
import com.hubu.aspirin.model.entity.Teacher;
import com.hubu.aspirin.model.entity.User;
import com.hubu.aspirin.service.AdministratorService;
import com.hubu.aspirin.service.StudentService;
import com.hubu.aspirin.service.TeacherService;
import com.hubu.aspirin.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorServiceImpl extends ServiceImpl<AdministratorMapper, Administrator> implements AdministratorService {
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;

    @Override
    public AdministratorDTO getAdministratorInformation() {
        Administrator administrator = getCurrentAdministrator();
        return AdministratorConverter.INSTANCE.entityToDto(administrator);
    }

    @Override
    public boolean updateAdministratorInformation(ModifiableAdministratorDTO newInformation) {
        Administrator administrator = getCurrentAdministrator();
        AdministratorConverter.INSTANCE.updateEntityFromDto(newInformation, administrator);
        updateById(administrator);
        return true;
    }

    @Override
    public boolean addTeacher(TeacherManagementDTO dto) {
        Teacher teacher = TeacherConverter.INSTANCE.managementDtoToEntity(dto);
        // 默认用户名为教师编号
        String defaultUsername = teacher.getNumber();
        if (UserUtils.getByUsernameAndRole(defaultUsername, RoleEnum.TEACHER) != null) {
            throw new KnownException(ExceptionEnum.USERNAME_EXISTS);
        }
        teacher.setUsername(defaultUsername);
        // 设置默认密码
        String defaultRawPassword = AccountConstant.DEFAULT_RAW_PASSWORD.getValue();
        String defaultPassword = UserUtils.generatePassword(teacher.getUsername(), defaultRawPassword);
        teacher.setPassword(defaultPassword);
        teacherService.save(teacher);
        return true;
    }

    @Override
    public boolean updateTeacher(TeacherManagementDTO teacherManagementDTO) {
        String number = teacherManagementDTO.getNumber();
        User user = UserUtils.getByNumberAndRole(number, RoleEnum.TEACHER);
        if (user == null) {
            throw new KnownException(ExceptionEnum.USER_NOT_EXISTS);
        }
        Teacher teacher = teacherService.getById(user.getId());
        TeacherConverter.INSTANCE.updateEntityFromManagementDto(teacherManagementDTO, teacher);
        teacherService.updateById(teacher);
        return true;
    }

    public Administrator getCurrentAdministrator() {
        String username = UserUtils.getCurrentUsername();
        return getOne(new QueryWrapper<Administrator>().eq("username", username));
    }

}
