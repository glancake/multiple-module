package com.gl.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.gl.enums.RequestType;
import lombok.Data;

/**
 * @TableName organization_join_requests
 */
@TableName(value = "organization_join_requests")
@Data
public class OrganizationJoinRequests implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.ASSIGN_ID, value = "request_id")

    private Long requestId;

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
    @TableField(value = "request_type")
    private RequestType requestType;

    /**
     *
     */
    @TableField(value = "request_date")
    private Date requestDate;

    /**
     *
     */
    private Object status;

    /**
     *
     */
    @TableField(value = "processed_by_user_id")
    private Long processedByUserId;

    /**
     *
     */
    @TableField(value = "processed_date")
    private Date processedDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        OrganizationJoinRequests other = (OrganizationJoinRequests) that;
        return (this.getRequestId() == null ? other.getRequestId() == null : this.getRequestId().equals(other.getRequestId()))
                && (this.getOrganizationId() == null ? other.getOrganizationId() == null : this.getOrganizationId().equals(other.getOrganizationId()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getRequestType() == null ? other.getRequestType() == null : this.getRequestType().equals(other.getRequestType()))
                && (this.getRequestDate() == null ? other.getRequestDate() == null : this.getRequestDate().equals(other.getRequestDate()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getProcessedByUserId() == null ? other.getProcessedByUserId() == null : this.getProcessedByUserId().equals(other.getProcessedByUserId()))
                && (this.getProcessedDate() == null ? other.getProcessedDate() == null : this.getProcessedDate().equals(other.getProcessedDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRequestId() == null) ? 0 : getRequestId().hashCode());
        result = prime * result + ((getOrganizationId() == null) ? 0 : getOrganizationId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getRequestType() == null) ? 0 : getRequestType().hashCode());
        result = prime * result + ((getRequestDate() == null) ? 0 : getRequestDate().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getProcessedByUserId() == null) ? 0 : getProcessedByUserId().hashCode());
        result = prime * result + ((getProcessedDate() == null) ? 0 : getProcessedDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", requestId=").append(requestId);
        sb.append(", organizationId=").append(organizationId);
        sb.append(", userId=").append(userId);
        sb.append(", requestType=").append(requestType);
        sb.append(", requestDate=").append(requestDate);
        sb.append(", status=").append(status);
        sb.append(", processedByUserId=").append(processedByUserId);
        sb.append(", processedDate=").append(processedDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}