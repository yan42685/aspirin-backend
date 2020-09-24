package com.hubu.aspirin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户可以修改的信息
 * @author alex
 */
@Getter
@AllArgsConstructor
public enum UserInfoEnum {
    // 昵称
    NICKNAME("昵称"),
    // 邮箱
    EMAIL("邮箱");

    private String name;
}
