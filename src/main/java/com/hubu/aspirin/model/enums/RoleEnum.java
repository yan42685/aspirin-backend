package com.hubu.aspirin.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.core.enums.IEnum;
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
//    @JsonValue 注释掉 让生成TS的代码用TEACHER这样的字符串
    private String description;
}
