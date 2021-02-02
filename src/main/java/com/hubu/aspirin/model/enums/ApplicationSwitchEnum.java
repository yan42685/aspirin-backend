package com.hubu.aspirin.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApplicationSwitchEnum {

    ELECT_SWITCH(0, "选课开关"),
    MARK_SWITCH(1, "打分开关");

    @EnumValue // 标记数据库存的值
    private Integer value;
    private String description;
}
