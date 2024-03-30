package com.gl.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

    /**
     * 从token中解析出用户名
     * @param token
     * @return
     */
    String extractUserName(String token);

    /**
     * 生成token
     * @param userDetails
     * @return
     */
    String generateToken(UserDetails userDetails);

    /**
     * token合法性校验
     * @param token
     * @param userDetails
     * @return
     */
    boolean isTokenValid(String token, UserDetails userDetails);
}
