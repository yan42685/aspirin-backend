package com.hubu.aspirin.model.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    protected Long id;

    @ApiModelProperty(value = "创建时间")
    protected LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    protected LocalDateTime updateTime;

    @ApiModelProperty(value = "用户名")
    protected String username;

    @ApiModelProperty(value = "密码")
    protected String password;

    @ApiModelProperty(value = "姓名")
    protected String realName;

    @ApiModelProperty(value = "性别")
    protected Integer gender;

    @ApiModelProperty(value = "手机号")
    protected String phoneNumber;

    @ApiModelProperty(value = "其他联系方式, 比如qq或邮箱等")
    protected String contactInformation;

    @ApiModelProperty(value = "昵称")
    protected String nickname;

    @ApiModelProperty(value = "头像URL")
    protected String avatarUrl;

    @ApiModelProperty(value = "用户账号状态")
    protected Integer status;

    @ApiModelProperty(value = "角色")
    protected Integer role;
}
