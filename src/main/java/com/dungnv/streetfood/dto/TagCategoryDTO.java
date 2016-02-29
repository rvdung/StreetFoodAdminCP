
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dto;

/**
* @author dungnv
* @version 1.0
* @since 1/24/2016 7:34 PM
*/
public class TagCategoryDTO{    
	//Fields
    private String id;
    private String tagId;
    private String tagIdName;
    private String categoryId;
    private String categoryIdName;
    //Constructor
	public TagCategoryDTO() {	
	}
	public TagCategoryDTO(String id, String tagId, String categoryId) {
		this.id = id;
		this.tagId = tagId;
		this.categoryId = categoryId;
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
	
}

