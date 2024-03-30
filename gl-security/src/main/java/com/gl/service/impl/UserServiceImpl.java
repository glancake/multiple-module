package com.gl.service.impl;

import com.gl.service.GlAccountService;
import com.gl.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final GlAccountService glAccountService;

    @Override
    public UserDetailsService userDetailsService() {
        return glAccountService::findAccountByUname;
    }
}
