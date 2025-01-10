package com.gl.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName organization_members
 */
@TableName(value = "organization_members")
@Data
public class OrganizationMembers implements Serializable {
    /**
     *
     */
    @TableId(value = "organization_member_id", type = IdType.ASSIGN_ID)
    private Long organizationMemberId;

    /**
     *
     */
    @TableField(value = "organization_id")
    private Long organizationId;

    /**
     *
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     *
     */
    private String role;

    /**
     *
     */
    @TableField(value = "join_date")
    private Date joinDate;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;




}