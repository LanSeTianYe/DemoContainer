package com.sun.xiaotian.demo.hystrix.component;

import com.sun.xiaotian.demo.hystrix.base.ResultT;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerComponent {

    private static final Logger logger = LogManager.getLogger(ExceptionHandlerComponent.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultT allException(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return ResultT.ofExcption(exception);
    }
}
