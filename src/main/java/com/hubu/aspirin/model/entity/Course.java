package com.hubu.aspirin.model.entity;

import com.hubu.aspirin.common.AspirinConstant;
import com.hubu.aspirin.model.enums.CourseTypeEnum;
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
@ApiModel(value = "Course对象", description = "")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "课程图标URL")
    private String iconUrl = AspirinConstant.DEFAULT_COURSE_ICON_URL.getValue();

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
