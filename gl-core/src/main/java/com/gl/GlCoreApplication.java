package com.gl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.gl.*"})
@MapperScan("com.gl.mapper")
public class GlCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(GlCoreApplication.class, args);
    }

}
