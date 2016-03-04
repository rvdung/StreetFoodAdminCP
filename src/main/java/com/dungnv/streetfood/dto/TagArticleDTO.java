
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.dto;

/**
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 12:59 PM
 */
public class TagArticleDTO {
    //Fields

    private String id;
    private String tagId;
    private String tagIdName;
    private String articleId;
    private String articleIdName;

    //Constructor
    public TagArticleDTO() {
    }

    public TagArticleDTO(String id, String tagId, String articleId) {
        this.id = id;
        this.tagId = tagId;
        this.articleId = articleId;
    }
    //Getters and setters

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagIdName(String tagIdName) {
        this.tagIdName = tagIdName;
    }

    public String getTagIdName() {
        return tagIdName;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleIdName(String articleIdName) {
        this.articleIdName = articleIdName;
    }

    public String getArticleIdName() {
        return articleIdName;
    }

}
