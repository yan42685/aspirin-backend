package com.hubu.aspirin.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnum implements IEnum<Integer> {
    // 保密
    SECRETE(0, "保密"),
    // 男
    MALE(1, "男"),
    // 女
    FEMALE(2, "女");

    @EnumValue // 标记数据库存的值
    private Integer value;
    @JsonValue
    private String description;
}
