package com.hubu.aspirin.config;

import cn.hutool.core.util.EnumUtil;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.enums.ExceptionEnum;
import com.hubu.aspirin.enums.RoleEnum;
import com.hubu.aspirin.model.entity.User;
import com.hubu.aspirin.util.UserUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;


/**
 * @author alex
 */
public class MyRealm extends AuthorizingRealm {
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
            throw new KnownException(ExceptionEnum.WRONG_CREDENTIALS);
        }
        return new SimpleAuthenticationInfo(
                user.getUsername(),
                user.getPassword(),
                // 用用户名当盐
                ByteSource.Util.bytes(user.getUsername()),
                this.getName());
    }

    /**
     * 权限核心配置 根据数据库中的该用户 角色 和 权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principals.getPrimaryPrincipal();
        Integer ordinal = user.getRole();
        String role = EnumUtil.getNames(RoleEnum.class).get(ordinal).toLowerCase();
        authorizationInfo.addRole(role);
        return authorizationInfo;
    }
}
