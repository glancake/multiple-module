package com.gl.controller;

import com.gl.api.CommonResult;
import com.gl.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * @ResponseBody 注解是必须的
 */
@ControllerAdvice
@Slf4j
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
        return CommonResult.failed(ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public CommonResult<String> handleAccessDeniedException(AccessDeniedException ex) {

        return CommonResult.failed("权限不足!");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public CommonResult<String> handleAccessDeniedException(MethodArgumentNotValidException ex) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        FieldError fieldError =(FieldError) allErrors.get(0);
        String firstErrorMessage = fieldError.getDefaultMessage();
        return CommonResult.failed(firstErrorMessage);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult<String> handleException(Exception ex) {
        // 处理其他未捕获的异常
        ex.printStackTrace();
        log.error("Exception: {}", ex.getMessage());
        return CommonResult.failed("系统异常");
    }
}
