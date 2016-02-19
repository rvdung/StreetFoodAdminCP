
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.dto;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/22/2016 9:45 PM
 */
public class LocaleDTO {
    //Fields

    private String id;
    private String locale;
    private String gmt;
    private String status;

    protected String defaultSortField;

    //Constructor
    public LocaleDTO() {
    }

    public LocaleDTO(String id, String locale, String gmt, String status) {
        this.id = id;
        this.locale = locale;
        this.gmt = gmt;
        this.status = status;
    }
    //Getters and setters

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getLocale() {
        return locale;
    }

    public void setGmt(String gmt) {
        this.gmt = gmt;
    }

    public String getGmt() {
        return gmt;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getDefaultSortField() {
        return defaultSortField;
    }

    public void setDefaultSortField(String defaultSortField) {
        this.defaultSortField = defaultSortField;
    }
    
    

}
