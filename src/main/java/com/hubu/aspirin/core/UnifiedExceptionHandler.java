package com.hubu.aspirin.core;

import com.hubu.aspirin.core.needconfig.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 捕获全局异常
 *
 * @author alex
 */
@Slf4j
@RestControllerAdvice
public class UnifiedExceptionHandler {
    /**
     * 处理未知异常
     */
    // 建议全部返回200状态码，具体错误类型根据response里的数据来处理，这样方便前端写代码
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500状态码
    @ExceptionHandler(Exception.class)
    public Result<String> handleUnknownException(Exception e, HttpServletRequest request) {
        int errorCode = ExceptionEnum.UNKNOWN_EXCEPTION.getErrorCode();
        String errorMessage = ExceptionEnum.UNKNOWN_EXCEPTION.getErrorMsg();
        String stackTrack = Arrays.toString(e.getStackTrace());
        log.error("url: {} | exceptionClass: {} | msg: {}", request.getRequestURL(), e.getClass(), stackTrack);
        return new Result<>(errorCode, errorMessage);
    }

    /**
     * 处理已知异常
     */
    @ExceptionHandler(KnownException.class)
    public Result<String> handleKnownException(KnownException e, HttpServletRequest request) {
        String stackTrack = Arrays.toString(e.getStackTrace());
        log.error("url: {}    msg: {}", request.getRequestURL(), e.getMessage() + stackTrack);
        return new Result<>(e.getErrorCode(), e.getMessage());
    }

    /**
     * 对于@Validated 普通参数(非 java bean)校验出错时抛出的异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleConstraintViolationException(ConstraintViolationException e) {
        int errorCode = ExceptionEnum.INVALID_PARAM.getErrorCode();
        String errorMessage = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return new Result<>(errorCode, errorMessage);
    }

    /**
     * 对于@Validated 请求体绑定到java bean上失败时抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        int errorCode = ExceptionEnum.INVALID_PARAM.getErrorCode();
        String errorMessage = e.getBindingResult().toString();
        return new Result<>(errorCode, errorMessage);
    }

    /**
     * 对于@Valid 请求参数绑定到java bean上失败时抛出的异常
     */
    @ExceptionHandler(BindException.class)
    public Result<String> handleBindException(BindException e) {
        int errorCode = ExceptionEnum.INVALID_PARAM.getErrorCode();
        String errorMessage = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return new Result<>(errorCode, errorMessage);
    }

    /**
     * 处理Shiro的未认证异常
     */
    @ExceptionHandler(AuthenticationException.class)
    public Result<String> handleUnAuthenticationException(Exception e, HttpServletRequest request) {
        int errorCode = ExceptionEnum.WRONG_CREDENTIALS.getErrorCode();
        String errorMessage = ExceptionEnum.WRONG_CREDENTIALS.getErrorMsg();
        String stackTrack = Arrays.toString(e.getStackTrace());
        log.error("url: {}    msg: {}", request.getRequestURL(), stackTrack);
        return new Result<>(errorCode, errorMessage);
    }

    /**
     * 处理Shiro的未授权异常
     */
    @ExceptionHandler(AuthorizationException.class)
    public Result<String> handleUnAuthorizationException(Exception e, HttpServletRequest request) {
        int errorCode = ExceptionEnum.NO_PERMISSION.getErrorCode();
        String errorMessage = ExceptionEnum.NO_PERMISSION.getErrorMsg();
        String stackTrack = Arrays.toString(e.getStackTrace());
        log.error("url: {}    msg: {}", request.getRequestURL(), stackTrack);
        return new Result<>(errorCode, errorMessage);
    }
}
