package com.gl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gl.domain.GlAccount;
import com.gl.domain.GlMes;
import com.gl.dto.GlMesReq;
import com.gl.exception.BizException;
import com.gl.service.GlMesService;
import com.gl.mapper.GlMesMapper;
import com.gl.util.UserProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【gl_mes】的数据库操作Service实现
* @createDate 2024-03-20 17:24:13
*/
@Service
public class GlMesServiceImpl extends ServiceImpl<GlMesMapper, GlMes>
    implements GlMesService {

    @Override
    public void addMes(GlMesReq mesReq) throws BizException {
        GlMes glMes = convertGlMesReq(mesReq);
        GlAccount user = UserProvider.getUser();
        glMes.setAccountId(user.getId());
        baseMapper.insert(glMes);
    }

    @Override
    public void modifyMes(GlMesReq mesReq,Long id) throws BizException {
        GlMes glMes = convertGlMesReq(mesReq);
        glMes.setId(id);
        baseMapper.updateById(glMes);
    }

    private GlMes convertGlMesReq(GlMesReq mesReq) throws BizException {
        GlMes glMes = new GlMes();
        if (StringUtils.isBlank(mesReq.getContent()) ){
            throw new BizException("内容不能为空");
        }
        glMes.setContent(mesReq.getContent());
        return glMes;
    }
}




