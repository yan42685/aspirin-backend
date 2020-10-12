package com.hubu.aspirin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 学生选课状态
 */
@AllArgsConstructor
@Getter
public enum ElectiveStatusEnum {
    CHOSEN(0, "已选择"),
    DROPPED(1, "已退课");

    @EnumValue // 标记数据库存的值
    private Integer value;
    @JsonValue // 标记响应json值
    private String description;
}
