package com.hubu.aspirin.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 学生选课状态
 */
@AllArgsConstructor
@Getter
public enum ElectiveStatusEnum {
    CHOSEN(0, "已选择"),
    DROPPED(1, "已退课"),
    UNCHOSEN(2, "未选择");

    @EnumValue // 标记数据库存的值
    private Integer value;
    private String description;
}
