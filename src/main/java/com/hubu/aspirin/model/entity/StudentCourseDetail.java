package com.hubu.aspirin.model.entity;

import com.hubu.aspirin.model.enums.ElectiveStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "StudentCourseDetail对象", description = "")
public class StudentCourseDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "学生编号")
    private String studentNumber;

    @ApiModelProperty(value = "具体课程id")
    private Long courseDetailId;

    @ApiModelProperty("选课状态")
    private ElectiveStatusEnum status;

}
