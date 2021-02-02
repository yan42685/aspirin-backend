package com.hubu.aspirin.model.dto;

import com.hubu.aspirin.model.enums.GenderEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TeacherModifiableDTO {
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

}
