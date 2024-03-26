package com.gl.config;


import com.gl.aspect.LogAspect;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
public class ApplicationConfig {
    // 配置其他 Bean 和组件

    @Bean
    public LogAspect logAspect() {
        return new LogAspect();
    }
}