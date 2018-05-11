package com.sun.xiaotian.demo.springboot.aop;

import com.alibaba.fastjson.JSON;
import com.sun.xiaotian.demo.springboot.common.HttpResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class RequestLogAspect {

    private static Logger logger = LoggerFactory.getLogger(RequestLogAspect.class);

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    private void logPointcut() {}

    @Before("logPointcut()")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        logger.info(String.format("[[Thread-ID: %s]], (Request Type:Request URL) -> [%s : %s] , (Invoke Method) -> [%s.%s], Params -> [%s]",
                Thread.currentThread().getId(),
                request.getMethod().toUpperCase(), request.getRequestURL().toString(),
                joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName(),
                JSON.toJSONString(joinPoint.getArgs())
        ));
    }

    @AfterReturning(pointcut = "logPointcut()", returning = "result")
    public void afterReturning(Object result) {
        logger.info(String.format("[Thread Id : %s, result: %s]", Thread.currentThread().getId(), JSON.toJSONString(result)));
    }

    @Around("logPointcut()")
    public Object around(ProceedingJoinPoint pjp) {
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            return new HttpResult(false, e.getMessage());
        }
    }
}
