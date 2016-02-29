
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dto;

/**
* @author dungnv
* @version 1.0
* @since 1/26/2016 9:17 PM
*/
public class TagDishDTO{    
	//Fields
    private String id;
    private String tagId;
    private String tagIdName;
    private String dishId;
    private String dishIdName;
    //Constructor
	public TagDishDTO() {	
	}
	public TagDishDTO(String id, String tagId, String dishId) {
		this.id = id;
		this.tagId = tagId;
		this.dishId = dishId;
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
	public void setDishId(String dishId) {
        this.dishId = dishId;
    }
	public String getDishId() {		
        return dishId;		
    }
    
	public void setDishIdName(String dishIdName) {
        this.dishIdName = dishIdName;
    }
	public String getDishIdName() {		
        return dishIdName;		
    }
	
}

