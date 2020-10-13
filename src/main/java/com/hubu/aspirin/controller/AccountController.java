package com.hubu.aspirin.controller;

import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.model.dto.UserDTO;
import com.hubu.aspirin.service.UserService;
import com.hubu.aspirin.util.VerificationCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "账户")
@RequestMapping("api/account")
@RestController
public class AccountController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "用户登录", notes = "不能用于管理员登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string"),
            @ApiImplicitParam(name = "rememberMe", value = "记住我", dataType = "boolean"),
            @ApiImplicitParam(name = "role", paramType = "header", value = "角色，取值为student或teacher", dataType = "string")
    })
    @GetMapping("user-login")
    public JsonWrapper<UserDTO> userLogin(String username, String password, Boolean rememberMe) {
        return new JsonWrapper<>(userService.login(username, password, rememberMe));
    }

    @ApiOperation(value = "管理员登录", notes = "role和verificationCode参数需要在header里")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string"),
            @ApiImplicitParam(name = "rememberMe", value = "记住我", dataType = "boolean"),
            @ApiImplicitParam(name = "role", paramType = "header", value = "角色，取值只能填administrator", dataType = "string"),
            @ApiImplicitParam(name = "verificationCode", paramType = "header", value = "验证码", dataType = "string")
    })
    @GetMapping("admin-login")
    public JsonWrapper<UserDTO> adminLogin(String username, String password, Boolean rememberMe) {
        return new JsonWrapper<>(userService.login(username, password, rememberMe));
    }

    @RequiresUser
    @ApiOperation(value = "登出")
    @GetMapping("logout")
    public JsonWrapper<Boolean> logout() {
        return new JsonWrapper<>(userService.logout());
    }


    @ApiOperation(value = "获取验证码图片")
    // @GetMapping里的produces属性会提示 Swagger显示图片而不是乱码
    @GetMapping(value = "get-verification-code", produces = MediaType.IMAGE_PNG_VALUE)
    public void getVerificationCode() {
        VerificationCodeUtils.getRandomCodeImage();
    }
}
