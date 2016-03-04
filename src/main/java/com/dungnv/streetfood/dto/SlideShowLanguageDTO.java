
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.dto;

/**
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 1:04 PM
 */
public class SlideShowLanguageDTO {
    //Fields

    private String id;
    private String name;
    private String description;
    private String languageCode;
    private String slideShowId;

    //Constructor
    public SlideShowLanguageDTO() {
    }

    public SlideShowLanguageDTO(String id, String name, String description, String languageCode, String slideShowId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.languageCode = languageCode;
        this.slideShowId = slideShowId;
    }
    //Getters and setters

    protected String defaultSortField = "name";

    public String getDefaultSortField() {
        return defaultSortField;
    }

    public void setDefaultSortField(String defaultSortField) {
        this.defaultSortField = defaultSortField;
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

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setSlideShowId(String slideShowId) {
        this.slideShowId = slideShowId;
    }

    public String getSlideShowId() {
        return slideShowId;
    }

}
