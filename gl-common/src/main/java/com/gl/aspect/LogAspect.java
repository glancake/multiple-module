package com.gl.aspect;

import com.gl.api.Log;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("@annotation(com.gl.api.Log)")
    public void logPointcut() {
    }

    @Before("logPointcut() && @annotation(log)")
    public void logBeforeControllerMethod(JoinPoint joinPoint, Log log) {
        String methodName = joinPoint.getSignature().getName();
        String logMessage = log.value().isEmpty() ? methodName : log.value();
        logger.info(logMessage);
        logger.info("Entering {}()", methodName);
    }

    @AfterReturning(pointcut = "logPointcut() && @annotation(log)", returning = "result")
    public void logAfterControllerMethod(JoinPoint joinPoint, Log log, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String logMessage = log.value().isEmpty() ? methodName : log.value();
        logger.info("Exiting {}() with result: {}", methodName, result);
    }

    @AfterThrowing(pointcut = "logPointcut() && @annotation(log)", throwing = "exception")
    public void logAfterThrowingControllerMethod(JoinPoint joinPoint, Log log, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        String logMessage = log.value().isEmpty() ? methodName : log.value();
        logger.info(logMessage);
        logger.error("Exception in {}(): {}", methodName, exception.getMessage());
    }
}