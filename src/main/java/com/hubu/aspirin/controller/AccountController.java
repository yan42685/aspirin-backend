package com.hubu.aspirin.controller;

import com.hubu.aspirin.core.Result;
import com.hubu.aspirin.core.util.VerificationCodeUtils;
import com.hubu.aspirin.model.dto.LoginTokenDTO;
import com.hubu.aspirin.model.dto.UserDTO;
import com.hubu.aspirin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
    @GetMapping("user-login")
    public Result<UserDTO> userLogin(LoginTokenDTO token) {
        return new Result<>(userService.login(token));
    }

    @ApiOperation(value = "管理员登录", notes = "verificationCode参数需要在header里")
    @ApiImplicitParam(name = "verificationCode", paramType = "header", value = "验证码", dataType = "string")
    @GetMapping("admin-login")
    public Result<UserDTO> adminLogin(LoginTokenDTO token) {
        return new Result<>(userService.login(token));
    }

    @RequiresUser
    @ApiOperation(value = "登出")
    @GetMapping("logout")
    public Result<Boolean> logout() {
        return new Result<>(userService.logout());
    }


    @ApiOperation(value = "获取验证码图片")
    // @GetMapping里的produces属性会提示 Swagger显示图片而不是乱码
    @GetMapping(value = "get-verification-code", produces = MediaType.IMAGE_PNG_VALUE)
    public void getVerificationCode() {
        VerificationCodeUtils.getRandomCodeImage();
    }
}
