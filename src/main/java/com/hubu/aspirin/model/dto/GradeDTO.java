package com.hubu.aspirin.model.dto;

import com.hubu.aspirin.model.enums.CourseTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GradeDTO {
    @ApiModelProperty(value = "教师名")
    private String teacherName;

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

    @ApiModelProperty(value = "平时分")
    private Float regularScores;

    @ApiModelProperty(value = "考试分")
    private Float examScores;

    @ApiModelProperty("最终分数")
    private Float finalScores;
}
