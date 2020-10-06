package com.hubu.aspirin.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author alex
 */
@Data
public class FacultyDTO {
    @ApiModelProperty(value = "学院名")
    private String name;

    @ApiModelProperty(value = "工作联系电话")
    private String phoneNumber;

    @ApiModelProperty(value = "学院图标URL")
    private String avatarUrl;

    @ApiModelProperty(value = "学院编号")
    private String number;
}
