package com.hubu.aspirin.model.dto;

import com.hubu.aspirin.model.enums.CourseTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("已分配的课程")
@Data
public class CourseDetailDTO {
    @ApiModelProperty("已分配课程的ID")
    private Long id;

    @ApiModelProperty(value = "教师编号")
    private String teacherNumber;

    @ApiModelProperty(value = "教师名")
    private String teacherName;

    @ApiModelProperty(value = "课程编号")
    private String courseNumber;

    @ApiModelProperty(value = "课程名")
    private String courseName;

    @ApiModelProperty(value = "教室编号")
    private String classroomNumber;

    @ApiModelProperty(value = "教室名")
    private String classroomName;

    @ApiModelProperty(value = "第几节课")
    private Integer schedulingTime;

    @ApiModelProperty(value = "星期几")
    private Integer dayOfTheWeek;

    @ApiModelProperty("课程类型")
    private CourseTypeEnum type;

    @ApiModelProperty(value = "课程图标URL")
    private String iconUrl;

    @ApiModelProperty(value = "课程简介")
    private String description;

    @ApiModelProperty(value = "学时")
    private Integer period;

    @ApiModelProperty(value = "学分")
    private Float credit;

    @ApiModelProperty(value = "开课的学期")
    private Integer semester;
}
