package com.gl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gl.domain.GlAccount;
import com.gl.dto.GlAccountRegisterReq;
import com.gl.dto.GlAccountSignInReq;
import com.gl.service.GlAccountService;
import com.gl.mapper.GlAccountMapper;
import com.gl.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Administrator
 * @description 针对表【gl_account】的数据库操作Service实现
 * @createDate 2024-03-20 17:24:13
 */
@Service
@RequiredArgsConstructor
public class GlAccountServiceImpl extends ServiceImpl<GlAccountMapper, GlAccount>
        implements GlAccountService, UserDetailsService {
    private final UserRoleService userRoleService;

    @Override
    public void register(GlAccountRegisterReq registerReq) {
        GlAccount glAccount = convertGlAccountRegisterReq(registerReq);
        baseMapper.insert(glAccount);
//        添加view权限
        boolean flag = userRoleService.setUserRole(glAccount.getId(), "ROLE_VIEWER");


    }

    @Override
    public void login(GlAccountSignInReq signInReq) {
        QueryWrapper<GlAccount> queryWrapper = new QueryWrapper<GlAccount>()
                .eq("account", signInReq.getAccount());
        GlAccount glAccount = baseMapper.selectOne(queryWrapper);

        // 验证验证码
        if (!isCaptchaValid(signInReq.getCaptcha())) {
            throw new BadCredentialsException("验证码错误!");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 验证密码
        if (!passwordEncoder.matches(signInReq.getPassword(), glAccount.getPassword())) {
            throw new BadCredentialsException("用户名或密码错误!");
        }

    }


    @Override
    public boolean isCaptchaValid(String captcha) {
        return "000000".equals(captcha);
    }

    private GlAccount convertGlAccountRegisterReq(GlAccountRegisterReq registerReq) {
        GlAccount glAccount = new GlAccount();
        glAccount.setAccount(registerReq.getAccount());
        glAccount.setPassword(registerReq.getPassword());
        return glAccount;
    }

    private GlAccount convertGlAccountSignInReq(GlAccountSignInReq signInReq) {
        GlAccount glAccount = new GlAccount();
        glAccount.setAccount(signInReq.getAccount());
        glAccount.setPassword(signInReq.getPassword());
        return glAccount;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<GlAccount> queryWrapper = new QueryWrapper<GlAccount>()
                .eq("account", username);
        GlAccount glAccount = baseMapper.selectOne(queryWrapper);
//      设置权限
        List<GrantedAuthority> grantedAuthorities = userRoleService.getRolesByUserId(glAccount.getId())
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        glAccount.setAuthorities(grantedAuthorities);
        return glAccount;
    }
}




