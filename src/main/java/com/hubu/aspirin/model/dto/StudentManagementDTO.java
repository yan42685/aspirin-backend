package com.hubu.aspirin.model.dto;

import com.hubu.aspirin.enums.GenderEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author alex
 */
@ApiModel("管理学生信息")
@Data
public class StudentManagementDTO {
    @ApiModelProperty(value = "姓名")
    private String realName;

    @ApiModelProperty(value = "性别")
    private GenderEnum gender;

    @ApiModelProperty(value = "手机号")
    private String phoneNumber;

    @ApiModelProperty(value = "其他联系方式, 比如qq或邮箱等")
    private String contactInformation;

    @ApiModelProperty(value = "学院")
    private String faculty;

    @ApiModelProperty(value = "专业")
    private String specialty;

    @ApiModelProperty(value = "学号")
    private String number;

    @ApiModelProperty(value = "入学年份")
    private Integer admissionYear;

    @ApiModelProperty(value = "所处学期")
    private Integer semester;
}
