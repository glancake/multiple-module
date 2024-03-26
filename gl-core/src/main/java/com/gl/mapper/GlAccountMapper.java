package com.gl.mapper;

import com.gl.domain.GlAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【gl_account】的数据库操作Mapper
* @createDate 2024-03-20 17:24:13
* @Entity com.gl.domain.GlAccount
*/
@Mapper
public interface GlAccountMapper extends BaseMapper<GlAccount> {

}




