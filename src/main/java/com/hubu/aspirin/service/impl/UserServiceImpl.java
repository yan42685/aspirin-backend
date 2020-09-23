package com.hubu.aspirin.service.impl;

import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.hubu.aspirin.enums.RoleEnum;
import com.hubu.aspirin.mapper.AdministratorMapper;
import com.hubu.aspirin.model.entity.Administrator;
import com.hubu.aspirin.model.entity.Student;
import com.hubu.aspirin.model.entity.Teacher;
import com.hubu.aspirin.model.entity.User;
import com.hubu.aspirin.service.AdministratorService;
import com.hubu.aspirin.service.StudentService;
import com.hubu.aspirin.service.TeacherService;
import com.hubu.aspirin.service.UserService;
import com.hubu.aspirin.util.UserUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
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
    public boolean login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
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
        Sha256Hash sha256Hash = new Sha256Hash(password, username, 1024);
        password = sha256Hash.toHex();

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
}
