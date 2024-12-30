package com.gl.filter;


import com.gl.dto.GlAccountSignInReq;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
@Getter
public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private final String captcha;
    private final String clientId;

    public CustomAuthenticationToken(GlAccountSignInReq signInReq) {
        super(signInReq.getAccount(), signInReq.getPassword());
        this.captcha = signInReq.getCaptcha();
        this.clientId = signInReq.getClientId();
    }
    public CustomAuthenticationToken(UserDetails userDetails, GlAccountSignInReq signInReq) {
        super(userDetails, signInReq.getPassword());
        this.captcha = signInReq.getCaptcha();
        this.clientId =signInReq.getClientId();
    }


}
