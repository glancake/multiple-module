package com.gl.filter;


import com.gl.dto.GlAccountSignInReq;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String captcha;

    public CustomAuthenticationToken(GlAccountSignInReq signInReq) {
        super(signInReq.getAccount(), signInReq.getPassword());
        this.captcha = signInReq.getCaptcha();
    }
    public CustomAuthenticationToken(UserDetails userDetails, GlAccountSignInReq signInReq) {
        super(userDetails, signInReq.getPassword());
        this.captcha = signInReq.getCaptcha();
    }


    public String getCaptcha() {
        return captcha;
    }
}
