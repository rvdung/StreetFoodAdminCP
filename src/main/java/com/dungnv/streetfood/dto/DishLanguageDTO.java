
/*
* Copyright (C) 2011 dungnv. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dto;

/**
* @author dungnv
* @version 1.0
* @since 1/25/2016 10:02 PM
*/
public class DishLanguageDTO{    
	//Fields
    private String id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private String dishId;
    private String dishIdName;
    private String languageCode;
    //Constructor
	public DishLanguageDTO() {	
	}
	public DishLanguageDTO(String id, String name, String shortDescription, String longDescription, String dishId, String languageCode) {
		this.id = id;
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.dishId = dishId;
		this.languageCode = languageCode;
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
	public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
	public String getLanguageCode() {		
        return languageCode;		
    }
    
	
}

