package com.sun.xiaotian.demo.springboot.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAop {

    private static Logger logger = LoggerFactory.getLogger(LogAop.class);

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    private void logPointcut() {}

    @Before("logPointcut()")
    public void doBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info(String.format("[[Thread-ID: %s]], (Request Type:Request URL) -> [%s : %s] , (Invoke Method) -> [%s.%s], Params -> [%s]",
                Thread.currentThread().getId(),
                request.getMethod().toUpperCase(), request.getRequestURL().toString(),
                joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(),
                JSON.toJSONString(joinPoint.getArgs())
        ));
    }

    @AfterReturning(pointcut = "logPointcut()", returning = "result")
    public void doAfterReturning(Object result) {
        logger.info(String.format("[Thread Id : %s, result: %s]", Thread.currentThread().getId(), JSON.toJSONString(result)));
    }
}
