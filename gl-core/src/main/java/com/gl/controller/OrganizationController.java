package com.gl.controller;

import com.gl.api.CommonResult;
import com.gl.dto.OrganizationCreateRequest;
import com.gl.service.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    /**
     * 创建组织
     */
    @PostMapping
    public CommonResult createOrganization(@RequestBody @Valid OrganizationCreateRequest request) {
        organizationService.createOrganization(request);
        return CommonResult.success("创建成功");
    }
/**
 * 更新组织
 */
public void String (){

}

/**
 * 删除组织
 */
/**
 * 获取组织
 */
/**
 * 获取组织列表
 */
/**
 * 加入组织
 */
/**
 * 退出组织
 */

}
