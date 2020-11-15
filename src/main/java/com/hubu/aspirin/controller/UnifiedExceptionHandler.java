package com.hubu.aspirin.controller;

import com.hubu.aspirin.common.JsonWrapper;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.enums.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500状态码
    @ExceptionHandler(Exception.class)
    public JsonWrapper<String> handleUnknownException(Exception e, HttpServletRequest request) {
        int errorCode = ExceptionEnum.UNKNOWN_EXCEPTION.getErrorCode();
        String errorMessage = ExceptionEnum.UNKNOWN_EXCEPTION.getErrorMsg();
        String stackTrack = Arrays.toString(e.getStackTrace());
        log.error("url: {} | exceptionClass: {} | msg: {}", request.getRequestURL(), e.getClass(), stackTrack);
        return new JsonWrapper<>(errorCode, errorMessage + stackTrack);
    }

    /**
     * 处理已知异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 400状态码
    @ExceptionHandler(KnownException.class)
    public JsonWrapper<String> handleKnownException(KnownException e, HttpServletRequest request) {
        String stackTrack = Arrays.toString(e.getStackTrace());
        log.error("url: {}    msg: {}", request.getRequestURL(), e.getMessage() + stackTrack);
        return new JsonWrapper<>(e.getErrorCode(), e.getMessage());
    }

    /**
     * 对于@Validated 普通参数(非 java bean)校验出错时抛出的异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public JsonWrapper<String> handleConstraintViolationException(ConstraintViolationException e) {
        int errorCode = ExceptionEnum.INVALID_PARAM.getErrorCode();
        String errorMessage = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return new JsonWrapper<>(errorCode, errorMessage);
    }

    /**
     * 对于@Validated 请求体绑定到java bean上失败时抛出的异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JsonWrapper<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        int errorCode = ExceptionEnum.INVALID_PARAM.getErrorCode();
        String errorMessage = e.getBindingResult().toString();
        return new JsonWrapper<>(errorCode, errorMessage);
    }

    /**
     * 对于@Valid 请求参数绑定到java bean上失败时抛出的异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public JsonWrapper<String> handleBindException(BindException e) {
        int errorCode = ExceptionEnum.INVALID_PARAM.getErrorCode();
        String errorMessage = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return new JsonWrapper<>(errorCode, errorMessage);
    }

    /**
     * 处理Shiro的未认证异常
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)  // 401状态码
    @ExceptionHandler(AuthenticationException.class)
    public JsonWrapper<String> handleUnAuthenticationException(Exception e, HttpServletRequest request) {
        int errorCode = ExceptionEnum.WRONG_CREDENTIALS.getErrorCode();
        String errorMessage = ExceptionEnum.WRONG_CREDENTIALS.getErrorMsg();
        String stackTrack = Arrays.toString(e.getStackTrace());
        log.error("url: {}    msg: {}", request.getRequestURL(), stackTrack);
        return new JsonWrapper<>(errorCode, errorMessage);
    }

    /**
     * 处理Shiro的未授权异常
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)  // 401状态码
    @ExceptionHandler(AuthorizationException.class)
    public JsonWrapper<String> handleUnAuthorizationException(Exception e, HttpServletRequest request) {
        int errorCode = ExceptionEnum.NO_PERMISSION.getErrorCode();
        String errorMessage = ExceptionEnum.NO_PERMISSION.getErrorMsg();
        String stackTrack = Arrays.toString(e.getStackTrace());
        log.error("url: {}    msg: {}", request.getRequestURL(), stackTrack);
        return new JsonWrapper<>(errorCode, errorMessage);
    }
}
