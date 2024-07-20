package com.gl.controller;

import com.gl.api.CommonResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ResponseBody 注解是必须的
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult<String> handleException(Exception ex) {
        // 处理其他未捕获的异常
        return CommonResult.failed(ex.getMessage());
    }
}
