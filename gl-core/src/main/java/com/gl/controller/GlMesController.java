package com.gl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gl.api.CommonResult;
import com.gl.api.Log;
import com.gl.domain.GlMes;
import com.gl.dto.GlMesReq;
import com.gl.service.GlMesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/auth/")
public class GlMesController {

    @Autowired
    private GlMesService glMesService;

    @Log("获取所有消息---/api/v1/auth/mes")
    @GetMapping("mes")
    public CommonResult<List<GlMes>> getMesList(@RequestParam(defaultValue = "1") int pageNum,
                                                @RequestParam(defaultValue = "10") int pageSize) {

        // Directly use the provided or default values for pagination
        Page<GlMes> page = new Page<>(pageNum, pageSize);

        // Retrieve the paginated list of GlMes objects
        IPage<GlMes> iPage = glMesService.page(page);

        // Return the records wrapped in a success response
        return CommonResult.success(iPage.getRecords());
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
    @PatchMapping("mes/{id}")
    public CommonResult<Object> modifyMes(@RequestBody @Validated GlMesReq glMesReq,
                                          @PathVariable Integer id) {
        glMesService.modifyMes(glMesReq, id);
        return CommonResult.success(null, "modify message successfully!");
    }

}
