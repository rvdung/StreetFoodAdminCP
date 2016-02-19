
/*
* Copyright (C) 2011 dungnv. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dto;

/**
* @author dungnv
* @version 1.0
* @since 1/25/2016 10:09 PM
*/
public class CategoryDishDTO{    
	//Fields
    private String id;
    private String categoryId;
    private String categoryIdName;
    private String dishId;
    private String dishIdName;
    //Constructor
	public CategoryDishDTO() {	
	}
	public CategoryDishDTO(String id, String categoryId, String dishId) {
		this.id = id;
		this.categoryId = categoryId;
		this.dishId = dishId;
	}	
	//Getters and setters
    
	public void setId(String id) {
        this.id = id;
    }
	public String getId() {		
        return id;		
    }
    
	public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
	public String getCategoryId() {		
        return categoryId;		
    }
    
	public void setCategoryIdName(String categoryIdName) {
        this.categoryIdName = categoryIdName;
    }
	public String getCategoryIdName() {		
        return categoryIdName;		
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

