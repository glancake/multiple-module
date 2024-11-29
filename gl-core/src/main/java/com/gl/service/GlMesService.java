package com.gl.service;

import com.gl.domain.GlMes;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gl.dto.GlMesReq;
import com.gl.exception.BizException;

/**
* @author Administrator
* @description 针对表【gl_mes】的数据库操作Service
* @createDate 2024-03-20 17:24:13
*/
public interface GlMesService extends IService<GlMes> {

    /**
     * 1.添加
     * 2.删除
     * 3.修改
     * 4.查询
     */

    void addMes(GlMesReq mesReq) throws Exception;
    void modifyMes(GlMesReq mesReq,Integer id) throws BizException;
}
