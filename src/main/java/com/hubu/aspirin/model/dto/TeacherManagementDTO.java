package com.hubu.aspirin.model.dto;

import com.hubu.aspirin.enums.GenderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author alex
 */
@Data
@ApiModel("管理教师信息")
public class TeacherManagementDTO {
    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "性别")
    private GenderEnum gender;

    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    @ApiModelProperty(value = "其他联系方式, 比如qq或邮箱等")
    private String contactInformation;

    @ApiModelProperty(value = "教师编号")
    private String number;
}
