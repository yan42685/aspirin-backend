package com.hubu.aspirin.model.dto;

import com.hubu.aspirin.enums.CourseTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("所教课程")
@Data
public class TeacherCourseDTO {
    @ApiModelProperty("专业名")
    private String specialtyName;

    @ApiModelProperty("具体课程ID")
    private Long courseDetailId;

    @ApiModelProperty(value = "课程编号")
    private String courseNumber;

    @ApiModelProperty(value = "课程名")
    private String courseName;

    @ApiModelProperty("课程类型")
    private CourseTypeEnum type;

    @ApiModelProperty(value = "学时")
    private Integer period;

    @ApiModelProperty(value = "学分")
    private Float credit;

    @ApiModelProperty(value = "开课的学期")
    private Integer semester;
}
