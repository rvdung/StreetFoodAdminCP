
/*
* Copyright (C) 2011 dungnv. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.dto;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/21/2016 12:48 AM
 */
public class ArticleDTO {
    //Fields

    private String id;
    private String title;
    private String content;
    private String shortContent;
    private String updateTime;
    private String updateTimeGmt;
    private String viewCount;

    //Constructor
    public ArticleDTO() {
    }

    public ArticleDTO(String id, String title, String content, String shortContent, String updateTime, String updateTimeGmt, String viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.shortContent = shortContent;
        this.updateTime = updateTime;
        this.updateTimeGmt = updateTimeGmt;
        this.viewCount = viewCount;
    }
    //Getters and setters

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTimeGmt(String updateTimeGmt) {
        this.updateTimeGmt = updateTimeGmt;
    }

    public String getUpdateTimeGmt() {
        return updateTimeGmt;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getViewCount() {
        return viewCount;
    }

}
