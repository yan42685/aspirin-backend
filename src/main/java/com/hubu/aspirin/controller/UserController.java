package com.hubu.aspirin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hubu.aspirin.core.Result;
import com.hubu.aspirin.model.dto.BulletinDTO;
import com.hubu.aspirin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "用户通用")
@RequestMapping("api/user")
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation(value = "查询公告")
    @GetMapping("bulletin-page-query")
    public Result<IPage<BulletinDTO>> queryBulletinDto(Integer current, Integer size, String titleOrContent) {
        return new Result<>(userService.pageBulletin(current, size, titleOrContent));
    }

    @ApiOperation("修改头像")
    @PutMapping("avatar")
    public Result<String> updateAvatar(MultipartFile multipartFile) {
        return new Result<>(userService.updateAvatar(multipartFile));
    }

    @ApiOperation(value = "修改密码")
    @PutMapping("password")
    public Result<Boolean> modifyPassword(String oldPassword, String newPassword) {
        return new Result<>(userService.modifyPassword(oldPassword, newPassword));
    }
}
