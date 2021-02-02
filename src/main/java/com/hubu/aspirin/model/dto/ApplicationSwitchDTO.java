package com.hubu.aspirin.model.dto;

import com.hubu.aspirin.model.enums.ApplicationSwitchEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("应用开关")
@Data
public class ApplicationSwitchDTO {
    @ApiModelProperty("开关名字")
    private ApplicationSwitchEnum switchEnum;
    @ApiModelProperty("开关状态")
    private Boolean status;
}
