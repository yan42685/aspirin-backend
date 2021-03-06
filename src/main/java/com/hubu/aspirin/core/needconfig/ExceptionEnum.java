package com.hubu.aspirin.core.needconfig;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 已知异常枚举
 *
 * @author alex
 */
@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    /**
     * 异常码规则：
     * 正数表示内部异常
     * 负数表示外部异常，应由调用者处理
     */
    FILE_IO_EXCEPTION(51, "文件读写失败"),
    TO_BE_IMPLEMENTED(50, "该功能还未实现"),
    UNKNOWN_EXCEPTION(99, "服务器未知异常"),
    NOT_REGISTER(-1, "用户未注册"),
    NOT_LOGIN(-2, "用户未登录"),
    NO_PERMISSION(-3, "没有足够的权限"),
    INVALID_PARAM(-4, "参数校验失败"),
    IMAGE_UPLOAD_FAIL(-5, "图片为空或者不是jpg，png，gif格式"),
    DOWNLOADING_FILE_NOT_EXITS(-6, "要下载的文件不存在"),
    USERNAME_EXISTS(-7, "用户名已存在"),
    // 登录时异常
    WRONG_CREDENTIALS(-8, "用户名或密码错误"),
    // 修改密码时异常
    WRONG_PASSWORD(-9, "密码错误"),
    USER_NOT_EXISTS(-10, "用户不存在"),
    VERIFICATION_CODE_MISMATCH(-11, "验证码错误"),


    NUMBER_EXISTS(-12, "编号已存在"),
    NUMBER_NOT_EXIST(-13, "编号不存在"),
    ROLE_HEADER_MISSING(-14, "header中缺少role参数"),
    CLASSROOM_NOT_AVAILABLE(-15, "教室该时间段被占用"),
    TEACHER_NOT_AVAILABLE(-16, "教师该时间段已分配课程"),
    DROP_COURSE_NOT_FOUND(-17, "没有选该课程，无法退课"),
    STUDENT_NOT_AVAILABLE(-18, "学生该时间段已有课"),
    COURSE_HAS_BEEN_CHOSEN(-19, "课程已选择，不可重复选择"),
    COURSE_STUDENT_SEMESTER_MISMATCH(-20, "课程不在所选学期"),
    FUNCTION_DISABLED(-21, "该功能暂未开启");


    @JsonValue // typescript-generator根据此注解生成TS Enum的值，如果没有此注解就用枚举的name
    private int errorCode;
    private String errorMsg;


}
