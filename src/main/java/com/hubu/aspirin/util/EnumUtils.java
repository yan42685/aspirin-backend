package com.hubu.aspirin.util;

import cn.hutool.core.util.EnumUtil;

public class EnumUtils {
    /**
     * 根据ordinal获取枚举值的字段
     */
    public static String getFieldByOrdinal(Class<? extends Enum<?>> enumClass, String fieldName, int ordinal) {
        return (String) EnumUtil.getFieldValues(enumClass, fieldName).get(ordinal);
    }

    public static String getNameLowerCase(Class<? extends Enum<?>> enumClass, int ordinal) {
        return EnumUtil.getNames(enumClass).get(ordinal).toLowerCase();
    }
}
