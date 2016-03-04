
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dto;

/**
* @author dungnv
* @version 1.0
* @since 2/21/2016 12:49 PM
*/
public class RestaurantArticleDTO {    
	//Fields
    private String id;
    private String articleId;
    private String articleIdName;
    private String restaurantId;
    private String restaurantIdName;
    //Constructor
	public RestaurantArticleDTO() {	
	}
	public RestaurantArticleDTO(String id, String articleId, String restaurantId) {
		this.id = id;
		this.articleId = articleId;
		this.restaurantId = restaurantId;
	}	
	//Getters and setters
    
	public void setId(String id) {
        this.id = id;
    }
	public String getId() {		
        return id;		
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

