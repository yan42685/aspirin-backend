package com.hubu.aspirin.config;

import com.hubu.aspirin.enums.RoleEnum;
import com.hubu.aspirin.model.entity.User;
import com.hubu.aspirin.util.UserUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;


/**
 * @author alex
 */
public class MyRealm extends AuthenticatingRealm {
    /**
     * 执行认证逻辑
     *
     * @param token 用户输入的令牌，即用户名，密码
     * @return 返回信息
     * @throws AuthenticationException 异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        String username = userToken.getUsername();
        RoleEnum role = UserUtils.getCurrentRole();
        User user = UserUtils.getByUsernameAndRole(username, role);

        if (user == null) {
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
