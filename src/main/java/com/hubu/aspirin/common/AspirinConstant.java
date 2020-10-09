package com.hubu.aspirin.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AspirinConstant {
    // 默认密码
    DEFAULT_RAW_PASSWORD("123456"),
    // 默认用户头像图片链接
    DEFAULT_USER_AVATAR_URL("http://qiniu-cdn.alexyan.cn/administrator/admin1/avatar/defaultAvatar.jpg"),
    // 默认课程图片链接
    DEFAULT_COURSE_ICON_URL("http://qiniu-cdn.alexyan.cn/course/005/icon/defaultCourseIcon.jpg");


    private String value;
}
