package com.hubu.aspirin.model.dto;

import com.hubu.aspirin.common.AspirinConstant;
import com.hubu.aspirin.enums.GenderEnum;
import com.hubu.aspirin.enums.RoleEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserDTO {
    @ApiModelProperty(value = "用户名")
    protected String username;

    @ApiModelProperty(value = "姓名")
    protected String realName;

    @ApiModelProperty(value = "性别")
    protected GenderEnum gender;

    @ApiModelProperty(value = "手机号")
    protected String phoneNumber;

    @ApiModelProperty(value = "其他联系方式, 比如qq或邮箱等")
    protected String contactInformation;

    @ApiModelProperty(value = "昵称")
    protected String nickname;

    @ApiModelProperty(value = "头像URL")
    protected String avatarUrl = AspirinConstant.DEFAULT_USER_AVATAR_URL.getValue();

}
