package com.hubu.aspirin.model.dto;

import com.hubu.aspirin.enums.GenderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "可修改的管理员个人信息")
public class ModifiableAdministratorDTO {
    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "性别")
    private GenderEnum gender;

    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    @ApiModelProperty(value = "其他联系方式, 比如qq或邮箱等")
    private String contactInformation;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像URL")
    private String avatarUrl;

    @ApiModelProperty(value = "工号")
    private String number;

}