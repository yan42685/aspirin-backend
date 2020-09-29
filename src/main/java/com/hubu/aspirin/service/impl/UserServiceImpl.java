package com.hubu.aspirin.service.impl;

import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.hubu.aspirin.enums.RoleEnum;
import com.hubu.aspirin.model.entity.Administrator;
import com.hubu.aspirin.model.entity.Student;
import com.hubu.aspirin.model.entity.Teacher;
import com.hubu.aspirin.model.entity.User;
import com.hubu.aspirin.service.AdministratorService;
import com.hubu.aspirin.service.StudentService;
import com.hubu.aspirin.service.TeacherService;
import com.hubu.aspirin.service.UserService;
import com.hubu.aspirin.util.UserUtils;
import com.hubu.aspirin.util.VerificationCodeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @Override
    public boolean login(String username, String password, Boolean rememberMe) {
        RoleEnum role = UserUtils.getCurrentRole();
        // 对管理员检查验证码
        if (RoleEnum.ADMINISTRATOR.equals(role)) {
            VerificationCodeUtils.checkCode();
        }
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
    public boolean simpleRegister(String username, String password) {
        RoleEnum role = UserUtils.getCurrentRole();

        // 如果用户名已存在
        if (UserUtils.getByUsernameAndRole(username, role) != null) {
            throw new KnownException(ExceptionEnum.USERNAME_EXISTS);
        }
        // 进行Sha256加密,用户名作为盐，并转为16进制
        password = UserUtils.generatePassword(username, password);

        switch (role) {
            case ADMINISTRATOR:
                Administrator administrator = new Administrator();
                administrator.setUsername(username).setPassword(password);
                administratorService.save(administrator);
                break;
            case STUDENT:
                Student student = new Student();
                student.setUsername(username).setPassword(password);
                studentService.save(student);
                break;
            case TEACHER:
                Teacher teacher = new Teacher();
                teacher.setUsername(username).setPassword(password);
                teacherService.save(teacher);
                break;
            default:
                break;
        }
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

    /**
     * 校验验证码
     */
//    private
}
