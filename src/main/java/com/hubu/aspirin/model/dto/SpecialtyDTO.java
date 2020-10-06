package com.hubu.aspirin.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author alex
 */
@Data
public class SpecialtyDTO {
    @ApiModelProperty(value = "专业名")
    private String name;

    @ApiModelProperty(value = "所属学院编号")
    private String facultyNumber;

    @ApiModelProperty(value = "专业编号")
    private String number;
}
