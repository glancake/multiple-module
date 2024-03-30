package com.gl.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GlAccountRegisterReq {

    /**
     *
     */
    @TableId
    private Integer id;

    /**
     *
     */
    private String account;

    /**
     *
     */
    private String password;
}
