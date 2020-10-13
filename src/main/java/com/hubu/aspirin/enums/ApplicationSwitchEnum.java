package com.hubu.aspirin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApplicationSwitchEnum {

    ELECT_SWITCH(0, "选课开关"),
    MARK_SWITCH(1, "打分开关");

    @EnumValue // 标记数据库存的值
    private Integer value;
    @JsonValue // 标记响应json值
    private String description;
}
