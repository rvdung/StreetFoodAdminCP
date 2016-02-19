
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.dto;

import java.util.List;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/22/2016 9:48 PM
 */
public class CategoryDTO {
    //Fields

    private String id;
    private String name;
    private String description;
    private String categoryStatus;
    private String categoryUpdateTime;
    private String categoryCreateTime;
    private String categoryUpdateTimeGmt;
    private String categoryCreateTimeGmt;

    //extend
    protected String defaultSortField;
    private List<DishGroupLangageDTO> listLanguage;
    private List<String> listTag;
    private List<String> listImgUrl;
    private String imageId;
    private String imageUrl;

    //Constructor
    public CategoryDTO() {
    }

    public CategoryDTO(String id, String name, String description, String categoryStatus, String categoryUpdateTime, String categoryCreateTime, String categoryUpdateTimeGmt, String categoryCreateTimeGmt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryStatus = categoryStatus;
        this.categoryUpdateTime = categoryUpdateTime;
        this.categoryCreateTime = categoryCreateTime;
        this.categoryUpdateTimeGmt = categoryUpdateTimeGmt;
        this.categoryCreateTimeGmt = categoryCreateTimeGmt;
    }
    //Getters and setters

    public List<String> getListImgUrl() {
        return listImgUrl;
    }

    public void setListImgUrl(List<String> listImgUrl) {
        this.listImgUrl = listImgUrl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCategoryStatus(String categoryStatus) {
        this.categoryStatus = categoryStatus;
    }

    public String getCategoryStatus() {
        return categoryStatus;
    }

    public void setCategoryUpdateTime(String categoryUpdateTime) {
        this.categoryUpdateTime = categoryUpdateTime;
    }

    public String getCategoryUpdateTime() {
        return categoryUpdateTime;
    }

    public void setCategoryCreateTime(String categoryCreateTime) {
        this.categoryCreateTime = categoryCreateTime;
    }

    public String getCategoryCreateTime() {
        return categoryCreateTime;
    }

    public void setCategoryUpdateTimeGmt(String categoryUpdateTimeGmt) {
        this.categoryUpdateTimeGmt = categoryUpdateTimeGmt;
    }

    public String getCategoryUpdateTimeGmt() {
        return categoryUpdateTimeGmt;
    }

    public void setCategoryCreateTimeGmt(String categoryCreateTimeGmt) {
        this.categoryCreateTimeGmt = categoryCreateTimeGmt;
    }

    public String getCategoryCreateTimeGmt() {
        return categoryCreateTimeGmt;
    }

    public List<String> getListTag() {
        return listTag;
    }

    public void setListTag(List<String> listTag) {
        this.listTag = listTag;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<DishGroupLangageDTO> getListLanguage() {
        return listLanguage;
    }

    public void setListLanguage(List<DishGroupLangageDTO> listLanguage) {
        this.listLanguage = listLanguage;
    }

    public String getDefaultSortField() {
        return defaultSortField;
    }

    public void setDefaultSortField(String defaultSortField) {
        this.defaultSortField = defaultSortField;
    }

}
