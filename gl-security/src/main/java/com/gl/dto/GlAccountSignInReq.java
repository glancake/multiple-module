package com.gl.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class GlAccountSignInReq {

    /**
     *
     */
    @NotBlank
    private String account;

    /**
     *
     */
    @NotBlank
    private String password;

    @NotBlank
    private String captcha;

    @NotBlank(message = "clientId不能为空")
    private String clientId;
}
