package com.hubu.aspirin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 角色
 */
@Getter
@AllArgsConstructor
public enum RoleEnum {
     // 管理员
    ADMINISTRATOR,
    // 老师
    TEACHER,
    // 学生
    STUDENT
}
