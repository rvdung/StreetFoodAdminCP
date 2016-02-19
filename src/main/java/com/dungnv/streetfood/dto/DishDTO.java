
/*
* Copyright (C) 2011 dungnv. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dto;

/**
* @author dungnv
* @version 1.0
* @since 1/25/2016 10:01 PM
*/
public class DishDTO{    
	//Fields
    private String id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private String dishStatus;
    private String dishUpdateTime;
    private String dishCreateTime;
    private String dishUpdateTimeGmt;
    private String dishCreateTimeGmt;
    private String viewCount;
    private String commentCount;
    private String shareCount;
    private String rating;
    //Constructor
	public DishDTO() {	
	}
	public DishDTO(String id, String name, String shortDescription, String longDescription, String dishStatus, String dishUpdateTime, String dishCreateTime, String dishUpdateTimeGmt, String dishCreateTimeGmt, String viewCount, String commentCount, String shareCount, String rating) {
		this.id = id;
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.dishStatus = dishStatus;
		this.dishUpdateTime = dishUpdateTime;
		this.dishCreateTime = dishCreateTime;
		this.dishUpdateTimeGmt = dishUpdateTimeGmt;
		this.dishCreateTimeGmt = dishCreateTimeGmt;
		this.viewCount = viewCount;
		this.commentCount = commentCount;
		this.shareCount = shareCount;
		this.rating = rating;
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
    
	public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
	public String getShortDescription() {		
        return shortDescription;		
    }
    
	public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
	public String getLongDescription() {		
        return longDescription;		
    }
    
	public void setDishStatus(String dishStatus) {
        this.dishStatus = dishStatus;
    }
	public String getDishStatus() {		
        return dishStatus;		
    }
    
	public void setDishUpdateTime(String dishUpdateTime) {
        this.dishUpdateTime = dishUpdateTime;
    }
	public String getDishUpdateTime() {		
        return dishUpdateTime;		
    }
    
	public void setDishCreateTime(String dishCreateTime) {
        this.dishCreateTime = dishCreateTime;
    }
	public String getDishCreateTime() {		
        return dishCreateTime;		
    }
    
	public void setDishUpdateTimeGmt(String dishUpdateTimeGmt) {
        this.dishUpdateTimeGmt = dishUpdateTimeGmt;
    }
	public String getDishUpdateTimeGmt() {		
        return dishUpdateTimeGmt;		
    }
    
	public void setDishCreateTimeGmt(String dishCreateTimeGmt) {
        this.dishCreateTimeGmt = dishCreateTimeGmt;
    }
	public String getDishCreateTimeGmt() {		
        return dishCreateTimeGmt;		
    }
    
	public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }
	public String getViewCount() {		
        return viewCount;		
    }
    
	public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }
	public String getCommentCount() {		
        return commentCount;		
    }
    
	public void setShareCount(String shareCount) {
        this.shareCount = shareCount;
    }
	public String getShareCount() {		
        return shareCount;		
    }
    
	public void setRating(String rating) {
        this.rating = rating;
    }
	public String getRating() {		
        return rating;		
    }
    
	
}

