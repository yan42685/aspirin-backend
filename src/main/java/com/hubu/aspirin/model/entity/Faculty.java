package com.hubu.aspirin.model.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Faculty对象", description="")
public class Faculty implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "学院名")
    private String name;

    @ApiModelProperty(value = "工作联系电话")
    private String phoneNumber;

    @ApiModelProperty(value = "学院图标URL")
    private String avatarUrl;

    @ApiModelProperty(value = "学院编号")
    private String number;


}
