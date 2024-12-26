package com.gl.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("gl_role")
@Data
public class Role {

    @TableId(type = IdType.ASSIGN_UUID)
    private Long id;

    @TableField("r_name")
    private String name;
}
