package com.gl.filter;

import com.gl.domain.GlAccount;
import com.gl.dto.GlAccountSignInReq;
import com.gl.service.GlAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {


    private final GlAccountService glAccountService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthenticationToken customAuthenticationToken = (CustomAuthenticationToken) authentication;
        GlAccountSignInReq glAccountSignInReq = new GlAccountSignInReq();
        glAccountSignInReq.setAccount(authentication.getName());
        glAccountSignInReq.setPassword(authentication.getCredentials().toString());
        glAccountSignInReq.setCaptcha(customAuthenticationToken.getCaptcha());
        glAccountSignInReq.setClientId(customAuthenticationToken.getClientId());
        glAccountService.login(glAccountSignInReq);
        GlAccount glAccount = new GlAccount();
        glAccount.setAccount(authentication.getName());
        return new CustomAuthenticationToken(glAccount,glAccountSignInReq);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
