package com.hubu.aspirin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.omg.CORBA.UNKNOWN;

@Getter
@AllArgsConstructor
public enum GenderEnum {
    // 未知
    UNKNOWN("未知"),
    // 男
    MALE("男"),
    // 女
    FEMALE("女");

    private String name;
}
