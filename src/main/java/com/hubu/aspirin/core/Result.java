package com.hubu.aspirin.core;


import lombok.Data;

/**
 * @author alex
 */

@Data
public class Result<T> {

    private static final long serialVersionUID = 1L;
    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_MESSAGE = "success";

    /**
     * 状态码  == 0 成功
     * > 0 服务器内部异常
     * < 0 外部异常，由调用方处理
     */
    private int code;

    /**
     * 返回API调用情况
     */
    private String message;

    /**
     * 返回的数据
     */
    private T data;

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(int code, String message) {
        this(code, message, null);
    }

    public Result(T data) {
        this(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

}
