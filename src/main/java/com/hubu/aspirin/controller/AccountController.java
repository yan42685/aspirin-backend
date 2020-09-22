package com.hubu.aspirin.controller;

import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "账户")
@RequestMapping("api/account")
@RestController
public class AccountController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "用户登录", notes = "role参数需要在header里")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "string"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string"),
            @ApiImplicitParam(name = "role", value = "角色", dataType = "string")
    })
    @GetMapping("login")
    public JsonWrapper<Boolean> login(String username, String password) {
        return new JsonWrapper<>(userService.login(username, password));
    }

    @ApiOperation(value = "用户登出")
    @GetMapping("logout")
    public JsonWrapper<Boolean> logout() {
        return new JsonWrapper<>(userService.logout());
    }

    @ApiOperation("获取个人信息")
    @ApiImplicitParam(name = "str", value = "", paramType = "query", dataType = "string")
    @GetMapping("information")
    public JsonWrapper<String> getAccountInfo( String str) {
        return null;
    }
}
