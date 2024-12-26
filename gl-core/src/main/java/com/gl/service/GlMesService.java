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
/**
 * GlMesService接口扩展了IService接口，专门用于处理GlMes对象的业务逻辑
 * 它提供了对GlMes对象进行基本操作的方法，如添加、修改、删除和查询
 */
public interface GlMesService extends IService<GlMes> {

    /**
     * 添加新的GlMes对象
     *
     * @param mesReq 包含要添加的GlMes对象信息的请求对象
     * @throws Exception 如果添加过程中发生错误，则抛出异常
     */
    void addMes(GlMesReq mesReq) throws Exception;

    /**
     * 修改现有的GlMes对象
     *
     * @param mesReq 包含要修改的GlMes对象新信息的请求对象
     * @param id 要修改的GlMes对象的标识符
     * @throws BizException 如果修改过程中发生业务逻辑相关的错误，则抛出异常
     */
    void modifyMes(GlMesReq mesReq,Long id) throws BizException;
}

