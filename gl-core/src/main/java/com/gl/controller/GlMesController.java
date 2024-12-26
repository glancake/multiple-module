package com.gl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gl.api.CommonResult;
import com.gl.api.Log;
import com.gl.domain.GlAccount;
import com.gl.domain.GlMes;
import com.gl.dto.GlMesReq;
import com.gl.dto.MessageDTO;
import com.gl.entity.Pagination;
import com.gl.exception.BizException;
import com.gl.service.GlAccountService;
import com.gl.service.GlMesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Tag(name = "消息")
@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class GlMesController {

    //    使用构造器注入bean
    private final GlMesService glMesService;
    private final GlAccountService glAccountService;

    @Log("获取所有消息---/api/v1/auth/mes")
    @Operation(summary = "获取所有消息")
    @PreAuthorize("hasRole('ROLE_DEVELOPER')")
    @GetMapping("mes")
    public CommonResult<IPage<MessageDTO>> getMesList(Pagination pagination) {
        // Directly use the provided or default values for pagination
        Page<MessageDTO> page = new Page<>(pagination.getCurrentPage(), pagination.getPageSize());
        List<GlMes> list = glMesService.list();
        List<MessageDTO> messageDTOs = new ArrayList<>();
        for (GlMes message : list) {
            GlAccount account = glAccountService.getById(message.getAccountId());
            MessageDTO messageDTO = new MessageDTO();
            BeanUtils.copyProperties(message, messageDTO);
            messageDTO.setAccountName(account.getAccount());
            messageDTOs.add(messageDTO);
        }
        // Retrieve the paginated list of GlMes objects
        IPage<MessageDTO> iPage = new Page<>(page.getCurrent(), page.getSize(), messageDTOs.size());
        iPage.setRecords(messageDTOs);
        // Return the records wrapped in a success response
        return CommonResult.success(iPage);
    }

    @Log("添加消息---/api/v1/auth/mes")
    @Operation(summary = "添加消息")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("mes")
    public CommonResult<Object> addMes(@RequestBody @Validated GlMesReq glMesReq) throws Exception {
        glMesService.addMes(glMesReq);
        return CommonResult.success(null, "save message successfully!");
    }

    @Log("删除消息---/api/v1/auth/mes/{id}")
    @Operation(summary = "删除消息")
    @DeleteMapping("mes/{id}")
    public CommonResult<Object> removeMes(@PathVariable("id") Integer id) {
        glMesService.removeById(id);
        return CommonResult.success(null, "remove message successfully!");
    }

    /**
     * Modifies a message in the database.
     *
     * @param glMesReq the request object containing the updated message information
     * @param id       the ID of the message to be modified
     * @return a CommonResult object indicating the success of the modification
     */
    @Log("修改消息---/api/v1/auth/mes/{id}")
    @Operation(summary = "修改消息")
    @PatchMapping("mes/{id}")
    public CommonResult modifyMes(@RequestBody @Validated GlMesReq glMesReq,
                                  @PathVariable Long id) throws BizException {
        glMesService.modifyMes(glMesReq, id);
        return CommonResult.success(Collections.singletonMap("message", "Message modified successfully!"));
    }

}
