package com.gl.controller;

import com.gl.api.CommonResult;
import com.gl.dto.GlAccountRegisterReq;
import com.gl.dto.GlAccountSignInReq;
import com.gl.service.GlAccountService;
import com.gl.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final GlAccountService glAccountService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("login")
    public CommonResult<Map> handleSignIn(@RequestBody GlAccountSignInReq signInReq) {
        String username = signInReq.getAccount();
        String password = signInReq.getPassword();

        // 传递用户密码给到SpringSecurity执行校验，如果校验失败，会进入BadCredentialsException
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        // 验证通过，设置授权信息至SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 如果验证通过了，从返回的authentication里获得完整的UserDetails信息
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        Map<String, Object> tokens = new HashMap<>();
        tokens.put("access_token", token);
        tokens.put("refresh_token", refreshToken);
        return CommonResult.success(tokens);
    }

    @PostMapping("register")
    public CommonResult<String> handleRegister(@RequestBody GlAccountRegisterReq registerReq) {
        registerReq.setPassword(passwordEncoder.encode(registerReq.getPassword()));
        glAccountService.register(registerReq);
        return CommonResult.success("register successfully");
    }


    @PostMapping("/refresh-token")
    public CommonResult<?> refreshToken(@RequestHeader("X-Refresh-Token") String refreshToken) {
        try {
            String userEmail = jwtService.extractUserNameFromRefreshToken(refreshToken);
            UserDetails userDetails = glAccountService.findAccountByUname(userEmail);
            if (jwtService.isRefreshTokenValid(refreshToken, userDetails)) {
                String newAccessToken = jwtService.generateToken(userDetails);
                String newRefreshToken = jwtService.generateRefreshToken(userDetails);
                Map<String, Object> tokens = new HashMap<>();
                tokens.put("access_token", newAccessToken);
                tokens.put("refresh_token", newRefreshToken); // Optionally, you can rotate the refresh token here
                return CommonResult.success(tokens);
            } else {
                return CommonResult.failed("Invalid refresh token");
            }
        } catch (Exception e) {
            return CommonResult.failed("Invalid refresh token");
        }
    }

}
