package com.hubu.aspirin.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountConstant {
    // 默认密码
    DEFAULT_RAW_PASSWORD("123456"),
    // 默认头像图片链接
    DEFAULT_AVATAR_URL("");


    private String value;
}
