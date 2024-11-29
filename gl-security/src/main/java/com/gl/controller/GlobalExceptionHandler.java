package com.gl.controller;

import com.gl.api.CommonResult;
import com.gl.exception.BizException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ResponseBody 注解是必须的
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public CommonResult<String> handleBizException(BizException ex) {
        // 处理自定义业务异常
        return CommonResult.failed(ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public CommonResult<String> handleBadCredentialsException(BadCredentialsException ex) {
        // 处理用户名或密码错误异常
        return CommonResult.failed("用户名或密码错误!");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult<String> handleException(Exception ex) {
        // 处理其他未捕获的异常
        return CommonResult.failed(ex.getMessage());
    }
}
