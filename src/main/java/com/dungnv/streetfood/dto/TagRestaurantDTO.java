
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.dto;

/**
 * @author dungnv
 * @version 1.0
 * @since 2/21/2016 12:58 PM
 */
public class TagRestaurantDTO {
    //Fields

    private String id;
    private String tagId;
    private String tagIdName;
    private String restaurantId;
    private String restaurantIdName;

    //Constructor
    public TagRestaurantDTO() {
    }

    public TagRestaurantDTO(String id, String tagId, String restaurantId) {
        this.id = id;
        this.tagId = tagId;
        this.restaurantId = restaurantId;
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
