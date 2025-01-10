package com.gl.controller;

import com.gl.api.CommonResult;
import com.gl.domain.GlAccount;
import com.gl.service.GlAccountService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/user")
public class UserController {

    private final GlAccountService glAccountService;

    /**
     * 更新头像
     */
    @PostMapping("/update_avatar")
    @Operation(summary = "更新头像")
    public CommonResult updateAvatar(@RequestParam("user_id") Long userId,
                                     @RequestBody MultipartFile avatar) throws  IOException {
        glAccountService.updateAvatar(userId, avatar);
        return CommonResult.success(null, "update avatar successfully");
    }

    /**
     * 获取用户信息
     */
    @GetMapping("/user_info")
    @Operation(summary = "获取用户信息")
    public CommonResult getUserInfo() throws IOException {
        GlAccount user = glAccountService.getUserInfo();
        return CommonResult.success(user);
    }

    /**
     * 更新个人信息
     */
    @PostMapping("/update_info")
    @Operation(summary = "更新个人信息")
    public CommonResult updateInfo(@RequestBody GlAccount glAccount) {
        glAccountService.updateById(glAccount);
        return CommonResult.success(null, "update info successfully");
    }


}
