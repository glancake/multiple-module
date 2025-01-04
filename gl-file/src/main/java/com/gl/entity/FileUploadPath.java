package com.gl.entity;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class FileUploadPath {

    @Value("${file.upload.avatar-path}")
    private String avatarPath;

}
