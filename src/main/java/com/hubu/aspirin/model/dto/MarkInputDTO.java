package com.hubu.aspirin.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("打分输入信息")
@Data
public class MarkInputDTO {
    @ApiModelProperty("具体课程ID")
    private Long courseDetailId;

    @ApiModelProperty("学生学号")
    private String studentNumber;

    @ApiModelProperty(value = "平时分")
    private Float regularScores;

    @ApiModelProperty(value = "考试分")
    private Float examScores;
}
