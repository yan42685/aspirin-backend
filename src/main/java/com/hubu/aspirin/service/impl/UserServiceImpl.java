package com.hubu.aspirin.service.impl;

import com.hubu.aspirin.service.AdministratorService;
import com.hubu.aspirin.service.StudentService;
import com.hubu.aspirin.service.TeacherService;
import com.hubu.aspirin.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public AdministratorService administratorService;
    @Autowired
    public StudentService studentService;
    @Autowired
    public TeacherService teacherService;

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
}
