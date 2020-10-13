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
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if (user == null) {
            throw new KnownException(ExceptionEnum.NOT_LOGIN);
        }
        return user;
    }

    public static String getCurrentUsername() {
        return getCurrentUser().getUsername();
    }

    public static RoleEnum getCurrentRole() {
        return getCurrentUser().getRole();
    }

    /**
     * 根据用户名和密码生成新密码
     */
    public static String generatePassword(String username, String rawPassword) {
        return new Sha256Hash(rawPassword, username, ShiroConfig.HASH_ITERATIONS).toHex();
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
