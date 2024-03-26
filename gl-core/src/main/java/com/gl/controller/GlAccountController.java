package com.gl.controller;

import com.gl.api.CommonResult;
import com.gl.dto.GlAccountRegisterReq;
import com.gl.dto.GlAccountSignInReq;
import com.gl.service.GlAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlAccountController {

    @Autowired
    private GlAccountService glAccountService;

    @PostMapping("login")
    public CommonResult<Object> signIn(@RequestBody GlAccountSignInReq signInReq){
        glAccountService.login(signInReq);
        return CommonResult.success(null,"sign in successfully");
    }

    @PostMapping("register")
    public CommonResult<Object> register(@RequestBody GlAccountRegisterReq registerReq){
        glAccountService.register(registerReq);
        return CommonResult.success(null,"register successfully");
    }
}
