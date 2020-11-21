package com.hubu.aspirin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CourseTypeEnum implements IEnum<Integer> {
    COMMON_COMPULSORY(0, "公共必修"),
    PROFESSIONAL_COMPULSORY(1, "专业必修"),
    COMMON_ELECTIVE(2, "公共选修"),
    PROFESSIONAL_ELECTIVE(3, "专业选修");

    @EnumValue  // 标记数据库存的值
    private Integer value;
    private String description;
}
