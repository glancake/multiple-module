package com.gl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gl.domain.GlAccount;
import com.gl.dto.GlAccountRegisterReq;
import com.gl.dto.GlAccountSignInReq;
import com.gl.service.GlAccountService;
import com.gl.mapper.GlAccountMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【gl_account】的数据库操作Service实现
 * @createDate 2024-03-20 17:24:13
 */
@Service
public class GlAccountServiceImpl extends ServiceImpl<GlAccountMapper, GlAccount>
        implements GlAccountService {

    @Override
    public void register(GlAccountRegisterReq registerReq) {
        GlAccount glAccount = convertGlAccountRegisterReq(registerReq);
        baseMapper.insert(glAccount);
    }

    @Override
    public void login(GlAccountSignInReq signInReq) {
        QueryWrapper<GlAccount> queryWrapper = new QueryWrapper<GlAccount>()
                .eq("account",signInReq.getAccount())
                .eq("password",signInReq.getPassword());
        baseMapper.selectOne(queryWrapper);
    }

    private GlAccount convertGlAccountRegisterReq(GlAccountRegisterReq registerReq) {
        GlAccount glAccount = new GlAccount();
        glAccount.setAccount(registerReq.getAccount());
        glAccount.setPassword(registerReq.getPassword());
        return glAccount;
    }

    private GlAccount convertGlAccountSignInReq( GlAccountSignInReq signInReq) {
        GlAccount glAccount = new GlAccount();
        glAccount.setAccount(signInReq.getAccount());
        glAccount.setPassword(signInReq.getPassword());
        return glAccount;
    }
}




