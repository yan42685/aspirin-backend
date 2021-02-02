package com.hubu.aspirin.model.dto;

import com.hubu.aspirin.model.enums.CourseTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author alex
 */
@Data
public class CourseModifiableDTO {
    @ApiModelProperty(value = "课程名")
    private String name;

    @ApiModelProperty(value = "课程编号")
    private String number;

    @ApiModelProperty(value = "课程类型")
    private CourseTypeEnum type;

    @ApiModelProperty(value = "课程简介")
    private String description;

    @ApiModelProperty(value = "学时")
    private Integer period;

    @ApiModelProperty(value = "学分")
    private Float credit;
}
