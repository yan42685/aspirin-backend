package com.hubu.aspirin.common;
import com.hubu.aspirin.enums.ExceptionEnum;
import lombok.Getter;


/**
 * 已知异常
 * @author alex
 */
@Getter
public class KnownException extends RuntimeException {
    private static final long serialVersionUID = 3413470958629677916L;
    /**
     * 异常代码
     */
    private Integer errorCode;

    public KnownException(ExceptionEnum exception) {
        super(exception.getErrorMsg());
        this.errorCode = exception.getErrorCode();
    }
}
