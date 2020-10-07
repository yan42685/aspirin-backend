package com.hubu.aspirin.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.config.ShiroConfig;
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
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取当前用户相关信息
 *
 * @author alex
 */
public class UserUtils {
    private static AdministratorService administratorService = SpringContextUtils.getBean("administratorServiceImpl", AdministratorService.class);
    private static StudentService studentService = SpringContextUtils.getBean("studentServiceImpl", StudentService.class);
    private static TeacherService teacherService = SpringContextUtils.getBean("teacherServiceImpl", TeacherService.class);

    public static User getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        // 用户已登录或者记住密码，才可获取用户名
        if (subject.isAuthenticated() || subject.isRemembered()) {
            String username = (String) subject.getPrincipal();
            RoleEnum role = getCurrentRole();
            return getByUsernameAndRole(username, role);
        } else {
            // 未登录或者未记住密码，抛出未登录异常
            throw new KnownException(ExceptionEnum.NOT_LOGIN);
        }
    }

    public static String getCurrentUsername() {
        User principal = (User) SecurityUtils.getSubject().getPrincipal();
        return principal.getUsername();
    }

    public static User getByUsernameAndRole(String username, RoleEnum role) {
        return getByColumnAndRole("username", username, role);
    }

    /**
     * @param rawPassword 未加密的密码
     */
    public static User getByRawPasswordAndRole(String rawPassword, RoleEnum role) {
        String password = generatePassword(getCurrentUsername(), rawPassword);
        return getByColumnAndRole("password", password, role);
    }

    public static User getByNumberAndRole(String number, RoleEnum role) {
        return getByColumnAndRole("number", number, role);
    }

    // TODO: 和getcurrentUser只在shiro中使用，清除其他使用方式
    public static RoleEnum getCurrentRole() {
        // 获取用户角色
        HttpServletRequest request = ServletUtils.getRequest();
        return RoleEnum.valueOf(request.getHeader("role").toUpperCase());
    }

    /**
     * 根据用户名和密码生成新密码
     */
    public static String generatePassword(String username, String rawPassword) {
        return new Sha256Hash(rawPassword, username, ShiroConfig.HASH_ITERATIONS).toHex();
    }

    private static User getByColumnAndRole(String column, Object property, RoleEnum role) {
        User user = null;
        switch (role) {
            case ADMINISTRATOR:
                user = administratorService.getOne(new QueryWrapper<Administrator>().eq(column, property));
                break;
            case STUDENT:
                user = studentService.getOne(new QueryWrapper<Student>().eq(column, property));
                break;
            case TEACHER:
                user = teacherService.getOne(new QueryWrapper<Teacher>().eq(column, property));
                break;
            default:
                break;
        }
        return user;
    }
}
