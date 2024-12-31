package com.gl.controller;

import com.gl.api.CommonResult;
import com.gl.exception.BizException;
import com.gl.service.GlAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final GlAccountService glAccountService;

    /**
     * 更新头像
     */
    @PostMapping("/update_avatar")
    public CommonResult updateAvatar(@RequestParam("user_id") Long userId,
                                     @RequestBody MultipartFile avatar) throws BizException {
        boolean result = glAccountService.updateAvatar(userId, avatar);
        return CommonResult.success(null, "update avatar successfully");
    }


}
