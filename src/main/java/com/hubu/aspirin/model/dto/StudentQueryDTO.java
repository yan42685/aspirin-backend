package com.hubu.aspirin.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("查询学生所需信息")
@Data
public class StudentQueryDTO {
    @ApiModelProperty(value = "学院编号")
    private String facultyNumber;

    @ApiModelProperty(value = "专业编号")
    private String specialtyNumber;

    @ApiModelProperty(value = "入学年份")
    private Integer admissionYear;

    @ApiModelProperty("真名或学号")
    private String queryString;
}
