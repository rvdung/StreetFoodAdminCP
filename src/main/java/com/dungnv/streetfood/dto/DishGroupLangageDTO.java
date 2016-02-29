
/*
* Copyright (C) 2011 dungnv. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.dto;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/21/2016 9:12 PM
 */
public class DishGroupLangageDTO {
    //Fields

    private String id;
    private String name;
    private String description;
    private String languageCode;
    private String dishGroupId;
    private String dishGroupIdName;

    protected String defaultSortField;

    //Constructor
    public DishGroupLangageDTO() {
    }

    public DishGroupLangageDTO(String id, String name, String description, String languageCode, String dishGroupId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.languageCode = languageCode;
        this.dishGroupId = dishGroupId;
    }
    //Getters and setters

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

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setDishGroupId(String dishGroupId) {
        this.dishGroupId = dishGroupId;
    }

    public String getDishGroupId() {
        return dishGroupId;
    }

    public void setDishGroupIdName(String dishGroupIdName) {
        this.dishGroupIdName = dishGroupIdName;
    }

    public String getDishGroupIdName() {
        return dishGroupIdName;
    }

    public String getDefaultSortField() {
        return defaultSortField;
    }

    public void setDefaultSortField(String defaultSortField) {
        this.defaultSortField = defaultSortField;
    }
}
