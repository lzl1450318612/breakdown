package com.design.background.entity;

/**
 * 监控url实体类
 * @author 14503
 */
public class MonitorHref {
    private Long id;

    private String href;

    private Integer status;

    public MonitorHref(Long id, String href, Integer status) {
        this.id = id;
        this.href = href;
        this.status = status;
    }

    public MonitorHref() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}