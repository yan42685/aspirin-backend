package com.hubu.aspirin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.model.dto.BulletinDTO;
import com.hubu.aspirin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户通用")
@RequestMapping("api/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "分页查看所有公告")
    @GetMapping("bulletin-page")
    public JsonWrapper<IPage<BulletinDTO>> getBulletinPage(Integer current, Integer size) {
        return new JsonWrapper<>(userService.getBulletinPage(current, size));
    }

    @ApiOperation(value = "根据标题或内容查询公告")
    @GetMapping("bulletin-page-query")
    public JsonWrapper<IPage<BulletinDTO>> getBulletinPageByTitleOrContent(Integer current, Integer size, String queryString) {
        return new JsonWrapper<>(userService.getBulletinPageByTitleOrContent(current, size, queryString));
    }
}
