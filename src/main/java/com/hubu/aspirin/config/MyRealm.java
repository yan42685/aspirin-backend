package com.hubu.aspirin.config;

import cn.hutool.extra.servlet.ServletUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hubu.aspirin.enums.RoleEnum;
import com.hubu.aspirin.model.entity.Administrator;
import com.hubu.aspirin.model.entity.Student;
import com.hubu.aspirin.model.entity.Teacher;
import com.hubu.aspirin.model.entity.User;
import com.hubu.aspirin.service.AdministratorService;
import com.hubu.aspirin.service.StudentService;
import com.hubu.aspirin.service.TeacherService;
import com.hubu.aspirin.service.UserService;
import com.hubu.aspirin.util.AccountUtils;
import com.hubu.aspirin.util.ServletUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.management.relation.Role;
import javax.servlet.http.HttpServletRequest;

/**
 * @author alex
 */
public class MyRealm extends AuthenticatingRealm {
    /**
     * 执行认证逻辑
     * @param token 用户输入的令牌，即用户名，密码
     * @return 返回信息
     * @throws AuthenticationException 异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String username = userToken.getUsername();
        // 获取用户角色
        HttpServletRequest request = ServletUtils.getRequest();
        RoleEnum role = RoleEnum.valueOf(request.getHeader("role").toUpperCase());
        User user = AccountUtils.getUserByUsernameAndRole(username, role);

        if(user == null){
            return null;
        }
        return new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPassword(),
                // 用用户名当盐
                ByteSource.Util.bytes(user.getUsername()),
                this.getName());
    }
}
