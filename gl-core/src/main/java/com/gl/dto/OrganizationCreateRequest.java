package com.gl.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrganizationCreateRequest {
    @NotBlank
    private String name;
    // 可以根据需要添加其他字段，如描述、创建者ID等
    private String description;
}
