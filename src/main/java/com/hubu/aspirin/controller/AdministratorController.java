package com.hubu.aspirin.controller;

import com.hubu.aspirin.common.JsonWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "管理员")
@RequestMapping("api/administrator")
@RestController
public class AdministratorController {
    @ApiOperation("获取个人信息")
    @GetMapping("information")
    public JsonWrapper<String> getAccountInfo() {
        return null;
    }
}
