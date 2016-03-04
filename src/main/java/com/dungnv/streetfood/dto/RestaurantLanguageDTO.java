
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.dto;

/**
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 11:30 AM
 */
public class RestaurantLanguageDTO {
    //Fields

    private String id;
    private String name;
    private String address;
    private String introduce;
    private String languageCode;
    private String restaurantId;
    private String restaurantIdName;
    protected String defaultSortField;

    //Constructor
    public RestaurantLanguageDTO() {
    }

    public RestaurantLanguageDTO(String id, String name, String address, String introduce, String languageCode, String restaurantId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.introduce = introduce;
        this.languageCode = languageCode;
        this.restaurantId = restaurantId;
    }
    //Getters and setters

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

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantIdName(String restaurantIdName) {
        this.restaurantIdName = restaurantIdName;
    }

    public String getRestaurantIdName() {
        return restaurantIdName;
    }

}
