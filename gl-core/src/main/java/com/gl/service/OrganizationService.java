package com.gl.service;

import com.gl.domain.Organization;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gl.dto.OrganizationCreateRequest;

/**
* @author Administrator
* @description 针对表【organizations】的数据库操作Service
* @createDate 2025-01-08 15:30:03
*/
public interface OrganizationService extends IService<Organization> {

    void createOrganization(OrganizationCreateRequest request);
}
