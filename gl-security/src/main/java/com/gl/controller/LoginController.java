package com.gl.controller;

import com.gl.api.CommonResult;
import com.gl.dto.GlAccountSignInReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("login")
    public void handleLogin(@RequestBody GlAccountSignInReq signInReq) {
        String username = signInReq.getAccount();
        String password = signInReq.getPassword();

        try {
            // 传递用户密码给到SpringSecurity执行校验，如果校验失败，会进入BadCredentialsException
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            // 验证通过，设置授权信息至SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 如果验证通过了，从返回的authentication里获得完整的UserDetails信息
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            System.out.println(userDetails);
        } catch (Exception e) {
            CommonResult.failed("sign in fail");
        }
    }

}
