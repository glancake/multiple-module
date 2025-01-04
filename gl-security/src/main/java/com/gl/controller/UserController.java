package com.gl.controller;

import com.gl.api.CommonResult;
import com.gl.domain.GlAccount;
import com.gl.service.GlAccountService;
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
    public CommonResult updateAvatar(@RequestParam("user_id") Long userId,
                                     @RequestBody MultipartFile avatar) throws  IOException {
        boolean result = glAccountService.updateAvatar(userId, avatar);
        return CommonResult.success(null, "update avatar successfully");
    }

    @GetMapping("/user_info")
    public CommonResult getUserInfo() throws IOException {
        GlAccount user = glAccountService.getUserInfo();
        return CommonResult.success(user);
    }


}
