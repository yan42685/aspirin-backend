package com.hubu.aspirin.common.aspect;

import com.hubu.aspirin.common.ApplicationSwtich;
import com.hubu.aspirin.common.KnownException;
import com.hubu.aspirin.enums.ExceptionEnum;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationSwitchAspect {

    @Pointcut("@annotation(com.hubu.aspirin.common.annotation.CheckElectEnabled)")
    public void checkElectEnabled() {
    }

    @Pointcut("@annotation(com.hubu.aspirin.common.annotation.CheckMarkEnabled)")
    public void checkMarkEnabled() {
    }

    @Before("checkElectEnabled()")
    public void checkElectEnable(JoinPoint joinPoint) {
        if (!ApplicationSwtich.electEnabled) {
            throw new KnownException(ExceptionEnum.FUNCTION_DISABLED);
        }
    }

    @Before("checkMarkEnabled()")
    public void checkMarkEnabled(JoinPoint joinPoint) {
        if (!ApplicationSwtich.electEnabled) {
            throw new KnownException(ExceptionEnum.FUNCTION_DISABLED);
        }
    }
}

