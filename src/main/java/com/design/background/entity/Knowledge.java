package com.design.background.entity;

import java.util.Date;

/**
 * 知识实体类
 * @author 14503
 */
public class Knowledge {
    private Long id;

    private String title;

    private String content;

    private Long writerId;

    private Date createTime;

    public Knowledge(Long id, String title, String content, Long writerId, Date createTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerId = writerId;
        this.createTime = createTime;
    }

    public Knowledge() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Long getWriterId() {
        return writerId;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}