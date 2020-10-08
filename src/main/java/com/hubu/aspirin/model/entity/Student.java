package com.hubu.aspirin.model.entity;

import com.hubu.aspirin.enums.RoleEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Student对象", description = "")
public class Student extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色")
    private RoleEnum role = RoleEnum.STUDENT;

    @ApiModelProperty(value = "学院编号")
    private String facultyNumber;

    @ApiModelProperty(value = "专业编号")
    private String specialtyNumber;

    @ApiModelProperty(value = "学号")
    private String number;

    @ApiModelProperty(value = "入学年份")
    private Integer admissionYear;

    @ApiModelProperty(value = "所处学期")
    private Integer semester;


}
