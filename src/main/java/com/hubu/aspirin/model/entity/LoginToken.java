package com.hubu.aspirin.model.entity;

import com.hubu.aspirin.enums.RoleEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 使用自定义的token必须重写Realm的supports方法，
 * 如果要实现记住我等功能，自定义的token一定要继承自UsernamePasswordToken。
 */
@ApiModel("shiro自定义token")
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginToken extends UsernamePasswordToken {
    private RoleEnum role;

    // 实现构造方法，基础属性调用父类
    public LoginToken(RoleEnum role, String username, String password, Boolean rememberMe) {
        super(username, password != null ? password.toCharArray() : null, rememberMe, null);
        this.role = role;
    }
}
