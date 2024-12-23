package com.gl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication(scanBasePackages = {"com.gl.*"})
@EnableMethodSecurity(prePostEnabled = true)
@MapperScan("com.gl.mapper")
public class GlCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlCoreApplication.class, args);
    }

}
