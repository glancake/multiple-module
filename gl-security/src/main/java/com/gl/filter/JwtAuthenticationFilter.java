package com.gl.filter;

import com.gl.service.GlAccountService;
import com.gl.service.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private GlAccountService glAccountService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        try {
            userEmail = jwtService.extractUserName(jwt);
        }catch (ExpiredJwtException expiredJwtException){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        if (StringUtils.isNotEmpty(userEmail)
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = glAccountService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(jwt, userDetails)) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                context.setAuthentication(authToken);
                SecurityContextHolder.setContext(context);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void handleExpiredJwt(HttpServletRequest request, HttpServletResponse response, String jwt) throws IOException {
        final String refreshHeader = request.getHeader("X-Refresh-Token");

        if (refreshHeader != null && StringUtils.startsWith(refreshHeader, "Bearer ")) {
            String refreshToken = refreshHeader.substring(7);
            try {
                String userEmail = jwtService.extractUserNameFromRefreshToken(refreshToken);
                UserDetails userDetails = glAccountService.loadUserByUsername(userEmail);
                if (jwtService.isRefreshTokenValid(refreshToken, userDetails)) {
                    String newJwt = jwtService.generateToken(userDetails);
                    Map<String, Object> tokens = new HashMap<>();
                    tokens.put("access_token", newJwt);
                    tokens.put("refresh_token", refreshToken); // Optionally, you can rotate the refresh token here

                    response.setContentType("application/json");
                    PrintWriter out = response.getWriter();
                    out.print(tokens);
                    out.flush();
                } else {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid refresh token");
                }
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid refresh token");
            }
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT expired");
        }
    }
}
