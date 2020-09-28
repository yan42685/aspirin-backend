package com.hubu.aspirin.model.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.hubu.aspirin.enums.RoleEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Teacher对象", description="")
public class Teacher extends User implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "角色")
    private Integer role = RoleEnum.TEACHER.ordinal();

    @ApiModelProperty(value = "教师编号")
    private String number;

}
