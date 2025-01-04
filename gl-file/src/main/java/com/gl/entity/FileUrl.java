package com.gl.entity;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class FileUrl {

    @Value("${project.url}")
    private String url;
}
