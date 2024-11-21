package com.gl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gl.api.CommonResult;
import com.gl.api.Log;
import com.gl.domain.GlMes;
import com.gl.dto.GlMesReq;
import com.gl.entity.Pagination;
import com.gl.service.GlMesService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/auth/")
public class GlMesController {

    @Autowired
    private GlMesService glMesService;

    @Log("获取所有消息---/api/v1/auth/mes")
    @GetMapping("mes")
    public CommonResult<IPage<GlMes>> getMesList(Pagination pagination) {
        try {
            // Directly use the provided or default values for pagination
            Page<GlMes> page = new Page<>(pagination.getCurrentPage(), pagination.getPageSize());

            // Retrieve the paginated list of GlMes objects
            IPage<GlMes> iPage = glMesService.page(page);

            // Return the records wrapped in a success response
            return CommonResult.success(iPage);
        }catch (Exception e){
            return CommonResult.failed(e.getMessage());
        }

    }

    @Log("添加消息---/api/v1/auth/mes")
    @PostMapping("mes")
    public CommonResult<Object> addMes(@RequestBody @Validated GlMesReq glMesReq) throws Exception {
        glMesService.addMes(glMesReq);
        return CommonResult.success(null, "save message successfully!");
    }

    @Log("删除消息---/api/v1/auth/mes/{id}")
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
    @PatchMapping("mes/{id}")
    public CommonResult modifyMes(@RequestBody @Validated GlMesReq glMesReq,
                                  @PathVariable Integer id) {
        try {
            glMesService.modifyMes(glMesReq, id);
            return CommonResult.success(Collections.singletonMap("message", "Message modified successfully!"));
        } catch (ConstraintViolationException e) {
            return CommonResult.failed(e.getMessage());
        } catch (Exception e) {
            return CommonResult.failed("Internal server error");
        }
    }

}
