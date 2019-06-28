package com.xiaotian.datasource.dynamic.handler;

import com.xiaotian.datasource.dynamic.commoon.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class ConsumerExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result exception(Exception e) {
        log.error(e.getMessage(), e);
        return Result.ofException(e);
    }
}
