package com.hubu.aspirin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.converter.BulletinConverter;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.hubu.aspirin.enums.RoleEnum;
import com.hubu.aspirin.model.dto.BulletinDTO;
import com.hubu.aspirin.model.entity.*;
import com.hubu.aspirin.service.*;
import com.hubu.aspirin.util.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private BulletinService bulletinService;

    @Override
    public boolean login(String username, String password, Boolean rememberMe) {
        // TODO: 上线后开放验证码检查
//        RoleEnum role = UserUtils.getCurrentRole();
//        // 对管理员检查验证码
//        if (RoleEnum.ADMINISTRATOR.equals(role)) {
//            VerificationCodeUtils.checkCode();
//        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        subject.login(token);
        return true;
    }

    @Override
    public boolean logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return true;
    }

    @Override
    public boolean modifyPassword(String oldPassword, String newPassword) {
        RoleEnum role = UserUtils.getCurrentRole();
        User user = UserUtils.getByRawPasswordAndRole(oldPassword, role);
        if (user == null) {
            throw new KnownException(ExceptionEnum.WRONG_PASSWORD);
        }

        String username = UserUtils.getCurrentUsername();
        String generatedPassword = UserUtils.generatePassword(username, newPassword);
        Long userId = user.getId();
        switch (role) {
            case ADMINISTRATOR:
                Administrator administrator = administratorService.getById(userId);
                administrator.setPassword(generatedPassword);
                administratorService.updateById(administrator);
                break;
            case STUDENT:
                Student student = studentService.getById(userId);
                student.setPassword(generatedPassword);
                studentService.updateById(student);
                break;
            case TEACHER:
                Teacher teacher = teacherService.getById(userId);
                teacher.setPassword(generatedPassword);
                teacherService.updateById(teacher);
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public IPage<BulletinDTO> getBulletinPage(Integer current, Integer size) {
        Page<Bulletin> page = new Page<>(current, size);
        IPage<Bulletin> bulletinPage = bulletinService.page(page);
        return BulletinConverter.INSTANCE.entityToDtoPage(bulletinPage);
    }

    @Override
    public IPage<BulletinDTO> getBulletinPageByTitleOrContent(Integer current, Integer size, String queryString) {
        Page<Bulletin> page = new Page<>(current, size);
        QueryWrapper<Bulletin> queryWrapper = new QueryWrapper<Bulletin>()
                .like("title", queryString)
                .or()
                .like("content", queryString);
        IPage<Bulletin> bulletinIPage = bulletinService.page(page, queryWrapper);
        return BulletinConverter.INSTANCE.entityToDtoPage(bulletinIPage);
    }

    @Override
    public String uploadAvatar(MultipartFile file) {
        return null;
    }


}
