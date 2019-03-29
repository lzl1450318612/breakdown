package com.design.background.entity;

import java.util.Date;

/**
 * 故障实体类
 * @author 14503
 */
public class Breakdown {
    private Long id;

    private Integer statuscode;

    private String description;

    private Integer status;

    private Long handlerId;

    private Date createTime;

    private Date handleTime;

    private Long hrefId;

    private String handleInfo;

    private Boolean isurged;

    public Breakdown(Long id, Integer statuscode, String description, Integer status, Long handlerId, Date createTime, Date handleTime, Long hrefId, String handleInfo, Boolean isurged) {
        this.id = id;
        this.statuscode = statuscode;
        this.description = description;
        this.status = status;
        this.handlerId = handlerId;
        this.createTime = createTime;
        this.handleTime = handleTime;
        this.hrefId = hrefId;
        this.handleInfo = handleInfo;
        this.isurged = isurged;
    }

    public Breakdown() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(Integer statuscode) {
        this.statuscode = statuscode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Long getHrefId() {
        return hrefId;
    }

    public void setHrefId(Long hrefId) {
        this.hrefId = hrefId;
    }

    public String getHandleInfo() {
        return handleInfo;
    }

    public void setHandleInfo(String handleInfo) {
        this.handleInfo = handleInfo == null ? null : handleInfo.trim();
    }

    public Boolean getIsurged() {
        return isurged;
    }

    public void setIsurged(Boolean isurged) {
        this.isurged = isurged;
    }
}