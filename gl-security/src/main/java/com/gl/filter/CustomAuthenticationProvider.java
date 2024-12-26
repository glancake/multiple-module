package com.gl.filter;

import com.gl.domain.GlAccount;
import com.gl.dto.GlAccountSignInReq;
import com.gl.service.GlAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {


    private final GlAccountService glAccountService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthenticationToken customAuthenticationToken = (CustomAuthenticationToken) authentication;
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        String captcha = customAuthenticationToken.getCaptcha();
        GlAccountSignInReq glAccountSignInReq = new GlAccountSignInReq();
        glAccountSignInReq.setAccount(username);
        glAccountSignInReq.setPassword(password);
        glAccountSignInReq.setCaptcha(captcha);
        glAccountService.login(glAccountSignInReq);
        GlAccount glAccount = new GlAccount();
        glAccount.setAccount(username);
        return new CustomAuthenticationToken(glAccount,glAccountSignInReq);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
