package com.hubu.aspirin.common;

import com.hubu.aspirin.enums.ExceptionEnum;

/**
 * 应用开关
 */
public class ApplicationSwtich {

    /**
     * 是否开启选课系统
     */
    public static boolean electEnabled;
    /**
     * 是否开启打分系统
     */
    public static boolean markEnabled;

    static {
        electEnabled = true;
        markEnabled = true;
    }

    // TODO: 写注解实现检测

    /**
     * @throws KnownException
     */
    public static void checkElectEnabled() {
        if (!electEnabled) {
            throw new KnownException(ExceptionEnum.FUNCTION_DISABLED);
        }
    }

    /**
     * @throws KnownException
     */
    public static void checkMarkEnabled() {
        if (!markEnabled) {
            throw new KnownException(ExceptionEnum.FUNCTION_DISABLED);
        }
    }

}
