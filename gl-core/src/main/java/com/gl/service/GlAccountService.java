package com.gl.service;

import com.gl.domain.GlAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gl.dto.GlAccountRegisterReq;
import com.gl.dto.GlAccountSignInReq;

/**
* @author Administrator
* @description 针对表【gl_account】的数据库操作Service
* @createDate 2024-03-20 17:24:13
*/
public interface GlAccountService extends IService<GlAccount> {

/**
 * 1.注册
 * 2.登录
 */
    void register(GlAccountRegisterReq registerReq);
    void login(GlAccountSignInReq signInReq);
}
