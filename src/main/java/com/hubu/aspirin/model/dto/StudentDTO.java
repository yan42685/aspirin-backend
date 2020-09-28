package com.hubu.aspirin.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author alex
 */
@Data
@ApiModel(value = "学生个人信息")
public class StudentDTO {
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    @ApiModelProperty(value = "其他联系方式, 比如qq或邮箱等")
    private String contactInformation;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像URL")
    private String avatarUrl;

    @ApiModelProperty(value = "学院")
    private String faculty;

    @ApiModelProperty(value = "专业")
    private String specialty;

    @ApiModelProperty(value = "学号")
    private String number;

    @ApiModelProperty(value = "年级")
    private Integer grade;

    @ApiModelProperty(value = "入学年份")
    private Integer admissionYear;

    @ApiModelProperty(value = "所处学期")
    private Integer semester;

}
