package com.hubu.aspirin.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author alex
 */
@Data
public class ClassroomDTO {
    @ApiModelProperty(value = "地点")
    private String location;

    @ApiModelProperty(value = "编号")
    private String number;
}
