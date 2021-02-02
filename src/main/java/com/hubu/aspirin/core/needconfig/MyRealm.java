package com.hubu.aspirin.core.needconfig;

import com.hubu.aspirin.core.KnownException;
import com.hubu.aspirin.model.entity.LoginToken;
import com.hubu.aspirin.model.entity.User;
import com.hubu.aspirin.model.enums.RoleEnum;
import com.hubu.aspirin.util.EnumUtils;
import com.hubu.aspirin.util.UserUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;


/**
 * @author alex
 */
public class MyRealm extends AuthorizingRealm {
    /**
     * 此方法必须重写，只有重写了才可以使用自己的token
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    /**
     * 执行认证逻辑
     *
     * @param token 用户输入的令牌，即用户名，密码
     * @return 返回信息
     * @throws AuthenticationException 异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户信息
        LoginToken userToken = (LoginToken) token;
        String username = userToken.getUsername();
        RoleEnum role = userToken.getRole();

        User principle = UserUtils.getByUsernameAndRole(username, role);
        if (principle == null) {
            // NOTE: 其实这里无论抛什么异常都会被 shiro 改成UnAuthenticationException并改写msg,
            // 所以只用全局捕获UnAuthenticationException 就行
            throw new KnownException(ExceptionEnum.WRONG_CREDENTIALS);
        }

        // 加密后的凭证凭证(一般都是密码)
        Object hashedCredentials = principle.getPassword();
        // 盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        // 当前 realm 对象的 name
        String realmName = getName();
        // 注册登录用户的信息
        return new SimpleAuthenticationInfo(principle, hashedCredentials, credentialsSalt, realmName);
    }

    /**
     * 权限核心配置 根据数据库中的该用户 角色 和 权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User principal = (User) principals.getPrimaryPrincipal();
        // 查看在doGetAuthenticationInfo中授权的权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String role = EnumUtils.getNameLowerCase(RoleEnum.class, principal.getRole().ordinal());
        HashSet<String> rolesCollection = new HashSet<>();
        rolesCollection.add(role);
        authorizationInfo.addRoles(rolesCollection);
        return authorizationInfo;
    }
}
