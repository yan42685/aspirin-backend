package com.hubu.aspirin.common;

import com.hubu.aspirin.enums.ExceptionEnum;

/**
 * 应用开关
 */
public class ApplicationSwtich {

    /**
     * 是否开启选课系统
     */
    private static boolean electEnabled;
    /**
     * 是否开启打分系统
     */
    private static boolean markEnabled;

    static {
        electEnabled = true;
        markEnabled = true;
    }

    public static void checkElectEnabled() {
        if (!electEnabled) {
            throw new KnownException(ExceptionEnum.FUNCTION_DISABLED);
        }
    }

    public static void checkMarkEnabled() {
        if (!markEnabled) {
            throw new KnownException(ExceptionEnum.FUNCTION_DISABLED);
        }
    }

}
