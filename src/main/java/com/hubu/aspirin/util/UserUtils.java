package com.hubu.aspirin.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取当前用户相关信息
 * @author alex
 */
public class UserUtils {
    private static AdministratorService administratorService = SpringContextUtils.getBean("administratorServiceImpl",AdministratorService.class);
    private static StudentService studentService  = SpringContextUtils.getBean("studentServiceImpl",StudentService.class);
    private static TeacherService teacherService = SpringContextUtils.getBean("teacherServiceImpl",TeacherService.class);

    public static User getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        // 用户已登录或者记住密码，才可获取用户名
        if (subject.isAuthenticated() || subject.isRemembered()){
            String username = (String) subject.getPrincipal();
            return getByUsername(username);
        } else {
            // 未登录或者未记住密码，抛出未登录异常
            throw new KnownException(ExceptionEnum.NOT_LOGIN);
        }
    }

    public static User getByUsername(String username) {
        RoleEnum role = getCurrentRole();
        User user = null;
        switch (role) {
            case ADMINISTRATOR:
                user = administratorService.getOne(new QueryWrapper<Administrator>().eq("username", username));
                break;
            case STUDENT:
                user = studentService.getOne(new QueryWrapper<Student>().eq("username", username));
                break;
            case TEACHER:
                user = teacherService.getOne(new QueryWrapper<Teacher>().eq("username", username));
                break;
            default: break;
        }
        return user;
    }
    
    public static RoleEnum getCurrentRole() {
        // 获取用户角色
        HttpServletRequest request = ServletUtils.getRequest();
        return RoleEnum.valueOf(request.getHeader("role").toUpperCase());
    }
}
