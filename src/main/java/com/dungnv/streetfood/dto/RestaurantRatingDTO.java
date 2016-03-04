
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dto;

/**
* @author dungnv
* @version 1.0
* @since 2/21/2016 12:50 PM
*/
public class RestaurantRatingDTO {    
	//Fields
    private String id;
    private String point;
    private String restaurantId;
    private String restaurantIdName;
    private String userId;
    private String userIdName;
    private String rateTime;
    private String rateTimeGmt;
    //Constructor
	public RestaurantRatingDTO() {	
	}
	public RestaurantRatingDTO(String id, String point, String restaurantId, String userId, String rateTime, String rateTimeGmt) {
		this.id = id;
		this.point = point;
		this.restaurantId = restaurantId;
		this.userId = userId;
		this.rateTime = rateTime;
		this.rateTimeGmt = rateTimeGmt;
	}	
	//Getters and setters
    
	public void setId(String id) {
        this.id = id;
    }
	public String getId() {		
        return id;		
    }
    
	public void setPoint(String point) {
        this.point = point;
    }
	public String getPoint() {		
        return point;		
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
	public void setUserId(String userId) {
        this.userId = userId;
    }
	public String getUserId() {		
        return userId;		
    }
    
	public void setUserIdName(String userIdName) {
        this.userIdName = userIdName;
    }
	public String getUserIdName() {		
        return userIdName;		
    }
	public void setRateTime(String rateTime) {
        this.rateTime = rateTime;
    }
	public String getRateTime() {		
        return rateTime;		
    }
    
	public void setRateTimeGmt(String rateTimeGmt) {
        this.rateTimeGmt = rateTimeGmt;
    }
	public String getRateTimeGmt() {		
        return rateTimeGmt;		
    }
    
	
}

