package com.hubu.aspirin.model.dto;

import com.hubu.aspirin.model.enums.CourseTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel("选课记录")
@Data
public class CourseDropDTO {
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "已分配的课程的id")
    private Long courseDetailId;

    @ApiModelProperty(value = "教师名")
    private String teacherName;

    @ApiModelProperty(value = "课程名")
    private String courseName;

    @ApiModelProperty(value = "教室名")
    private String classroomName;

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
