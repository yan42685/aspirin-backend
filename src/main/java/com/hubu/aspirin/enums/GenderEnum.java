package com.hubu.aspirin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GenderEnum {
    // 保密
    UNKNOWN("保密"),
    // 男
    MALE("男"),
    // 女
    FEMALE("女");

    private String value;
}
