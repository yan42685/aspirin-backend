package com.hubu.aspirin.model.dto;

import com.hubu.aspirin.enums.CourseTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CourseDTO")
public class CourseDTO implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "课程图标URL")
    private String iconUrl;

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
