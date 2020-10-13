package com.hubu.aspirin.common;

/**
 * 应用开关
 */
public class ApplicationSwtich {

    /**
     * 是否开启选课系统
     */
    public static boolean enableElect;
    /**
     * 是否开启打分系统
     */
    public static boolean enableMark;

    static {
        enableElect = true;
        enableMark = true;
    }
}
