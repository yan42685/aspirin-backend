package com.hubu.aspirin.model.entity;

import com.hubu.aspirin.model.enums.RoleEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Teacher对象", description = "")
public class Teacher extends User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色")
    private RoleEnum role = RoleEnum.TEACHER;

    @ApiModelProperty(value = "教师编号")
    private String number;

    @ApiModelProperty(value = "学院编号")
    private String facultyNumber;
}
