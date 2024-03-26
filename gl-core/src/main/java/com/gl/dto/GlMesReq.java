package com.gl.dto;

import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class GlMesReq {

    @NonNull
    private String content;

    @NonNull
    private Integer accountId;
}
