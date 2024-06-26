package com.gl.controller;

import com.gl.api.CommonResult;
import com.gl.api.Log;
import com.gl.domain.GlMes;
import com.gl.dto.GlMesReq;
import com.gl.service.GlMesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/")
public class GlMesController {

    @Autowired
    private GlMesService glMesService;

    @Log("获取所有消息---/api/v1/auth/mes")
    @GetMapping("mes")
    public CommonResult<List<GlMes>> getMesList() {
        return CommonResult.success(glMesService.list());
    }

    @Log("添加消息---/api/v1/auth/mes")
    @PostMapping("mes")
    public CommonResult<Object> addMes(@RequestBody @Validated GlMesReq glMesReq) {
        glMesService.addMes(glMesReq);
        return CommonResult.success(null, "save message successfully!");
    }

    @Log("删除消息---/api/v1/auth/mes/{id}")
    @DeleteMapping("mes/{id}")
    public CommonResult<Object> removeMes(@PathVariable("id") Integer id) {
        glMesService.removeById(id);
        return CommonResult.success(null, "remove message successfully!");
    }

    @PatchMapping("mes/{id}")
    public CommonResult<Object> modifyMes(@RequestBody @Validated GlMesReq glMesReq,
                                          @PathVariable Integer id) {
        glMesService.modifyMes(glMesReq, id);
        return CommonResult.success(null, "modify message successfully!");
    }

}
