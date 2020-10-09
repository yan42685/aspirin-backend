package com.hubu.aspirin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 角色
 */
@Getter
@AllArgsConstructor
public enum RoleEnum implements IEnum<Integer> {
     // 管理员
    ADMINISTRATOR(0, "管理员"),
    // 老师
    TEACHER(1, "老师"),
    // 学生
    STUDENT(2, "学生");


    @EnumValue
    private Integer value;
    @JsonValue
    private String description;
}
