package com.hubu.aspirin.common;

import com.hubu.aspirin.core.KnownException;
import com.hubu.aspirin.core.needconfig.ExceptionEnum;
import lombok.Getter;

/**
 * 应用开关
 */
@Getter
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

    public static boolean flipElectEnabled() {
        electEnabled = !electEnabled;
        return electEnabled;
    }

    public static void checkMarkEnabled() {
        if (!markEnabled) {
            throw new KnownException(ExceptionEnum.FUNCTION_DISABLED);
        }
    }

    public static boolean flipMarkEnabled() {
        markEnabled = !markEnabled;
        return markEnabled;
    }

    public static boolean isElectEnabled() {
        return electEnabled;
    }

    public static boolean isMarkEnabled() {
        return markEnabled;
    }
}
