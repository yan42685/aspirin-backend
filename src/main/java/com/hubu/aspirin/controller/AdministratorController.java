package com.hubu.aspirin.controller;

import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.service.AdministratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "管理员")
@RequestMapping("api/admin")
@RestController
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @ApiOperation("获取个人信息")
    @ApiImplicitParam(name = "str", value = "", paramType = "query", dataType = "string")
    @GetMapping("")
    public JsonWrapper<String> getAccountInfo( String str) {
        return new JsonWrapper<>();
    }
}
