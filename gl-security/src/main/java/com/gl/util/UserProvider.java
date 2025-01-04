package com.gl.util;
import com.gl.domain.GlAccount;
import com.gl.filter.CustomAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserProvider {

    /**
     * 获取当前登录的用户信息
     * @return 当前用户的 UserDetails 实例，或 null 如果没有用户登录
     */
    public static GlAccount getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                return (GlAccount) principal;
            }
        }

        return null; // 如果没有用户登录，返回 null
    }

    public static void setUser(GlAccount user) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}