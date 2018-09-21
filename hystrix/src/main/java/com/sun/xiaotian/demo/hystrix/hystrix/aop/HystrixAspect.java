package com.sun.xiaotian.demo.hystrix.hystrix.aop;


import com.netflix.hystrix.HystrixCommand;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HystrixAspect {

    @Pointcut(value = "@annotation(com.sun.xiaotian.demo.hystrix.hystrix.DubboMethodAnnotation)")
    private void dubboInvokePoint() {

    }

    @Around(value = "dubboInvokePoint()")
    public Object dubboInvoke(ProceedingJoinPoint pjp) {
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
