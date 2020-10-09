package com.hubu.aspirin.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author alex
 */
@Data
public class CourseAssignDTO {
    @ApiModelProperty(value = "教师编号")
    private String teacherNumber;

    @ApiModelProperty(value = "课程编号")
    private String courseNumber;

    @ApiModelProperty(value = "第几节课")
    private Integer schedulingTime;

    @ApiModelProperty(value = "星期几")
    private Integer dayOfTheWeek;

    @ApiModelProperty(value = "教室编号")
    private String classroomNumber;
}
