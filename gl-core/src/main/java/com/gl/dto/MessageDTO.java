package com.gl.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDTO {
    private Integer id;
    private String content;
    private Date createAt;
    private String accountName;
}