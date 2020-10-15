package com.hubu.aspirin.common.aspect;

import com.hubu.aspirin.common.ApplicationSwtich;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApplicationSwitchAspect {

    @Pointcut("@annotation(com.hubu.aspirin.common.annotation.CheckElectSwitch)")
    public void checkElectSwitch() {
    }

    @Pointcut("@annotation(com.hubu.aspirin.common.annotation.CheckMarkSwitch)")
    public void checkMarkSwitch() {
    }

    @Before("checkElectSwitch()")
    public void checkElectEnable(JoinPoint joinPoint) {
        ApplicationSwtich.checkElectEnabled();
    }

    @Before("checkMarkSwitch()")
    public void checkMarkSwitch(JoinPoint joinPoint) {
        ApplicationSwtich.checkMarkEnabled();
    }
}

