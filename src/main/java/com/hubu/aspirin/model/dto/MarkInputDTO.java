package com.hubu.aspirin.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("打分更新信息")
public class MarkInputDTO {
    @ApiModelProperty("成绩Id")
    private Long gradeId;

    @ApiModelProperty(value = "平时分")
    private Float regularScores;

    @ApiModelProperty(value = "考试分")
    private Float examScores;
}
