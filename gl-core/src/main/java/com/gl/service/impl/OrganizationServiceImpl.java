package com.gl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gl.domain.GlAccount;
import com.gl.domain.Organization;
import com.gl.domain.OrganizationMembers;
import com.gl.dto.OrganizationCreateRequest;
import com.gl.enums.OrganizationStatus;
import com.gl.mapper.OrganizationMapper;
import com.gl.service.OrganizationMembersService;
import com.gl.service.OrganizationService;
import com.gl.util.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【organizations】的数据库操作Service实现
* @createDate 2025-01-08 15:30:03
*/
@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization>
    implements OrganizationService {

    private final OrganizationMembersService organizationMembersService;

    @Override
    public void createOrganization(OrganizationCreateRequest request) {
        Organization organization = new Organization();
        organization.setName(request.getName());
        if (request.getDescription() != null) {
            organization.setDescription(request.getDescription());
        }
        GlAccount user = UserProvider.getUser();
        organization.setOwnerUserId(user.getId());

//        organization.setStatus(OrganizationStatus.ACTIVE);
        save(organization);
        //        成员表添加记录
        OrganizationMembers organizationMembers = new OrganizationMembers();
        organizationMembers.setOrganizationId(organization.getOrganizationId());
        organizationMembers.setUserId(user.getId());
        organizationMembersService.save(organizationMembers);

    }
}




