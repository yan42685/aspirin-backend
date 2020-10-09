package com.hubu.aspirin.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("课程表DTO")
@Data
public class CourseScheduleDTO {
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
}
