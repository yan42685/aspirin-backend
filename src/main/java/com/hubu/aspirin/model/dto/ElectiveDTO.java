package com.hubu.aspirin.model.dto;

import com.hubu.aspirin.enums.ElectiveStatusEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@ApiModel("选课DTO")
@Data
public class ElectiveDTO extends CourseDetailDTO {
    private ElectiveStatusEnum status;
}
