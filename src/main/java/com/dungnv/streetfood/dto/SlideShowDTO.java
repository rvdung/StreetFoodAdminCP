
/*
* Copyright (C) 2011 dungnv. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dto;

/**
* @author dungnv
* @version 1.0
* @since 1/27/2016 11:48 PM
*/
public class SlideShowDTO{    
	//Fields
    private String id;
    private String name;
    private String url;
    private String description;
    private String createDateGmt;
    private String validFromGmt;
    private String validToGmt;
    private String order;
    private String desription;
    //Constructor
	public SlideShowDTO() {	
	}
	public SlideShowDTO(String id, String name, String url, String description, String createDateGmt, String validFromGmt, String validToGmt, String order, String desription) {
		this.id = id;
		this.name = name;
		this.url = url;
		this.description = description;
		this.createDateGmt = createDateGmt;
		this.validFromGmt = validFromGmt;
		this.validToGmt = validToGmt;
		this.order = order;
		this.desription = desription;
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
    
	public void setUrl(String url) {
        this.url = url;
    }
	public String getUrl() {		
        return url;		
    }
    
	public void setDescription(String description) {
        this.description = description;
    }
	public String getDescription() {		
        return description;		
    }
    
	public void setCreateDateGmt(String createDateGmt) {
        this.createDateGmt = createDateGmt;
    }
	public String getCreateDateGmt() {		
        return createDateGmt;		
    }
    
	public void setValidFromGmt(String validFromGmt) {
        this.validFromGmt = validFromGmt;
    }
	public String getValidFromGmt() {		
        return validFromGmt;		
    }
    
	public void setValidToGmt(String validToGmt) {
        this.validToGmt = validToGmt;
    }
	public String getValidToGmt() {		
        return validToGmt;		
    }
    
	public void setOrder(String order) {
        this.order = order;
    }
	public String getOrder() {		
        return order;		
    }
    
	public void setDesription(String desription) {
        this.desription = desription;
    }
	public String getDesription() {		
        return desription;		
    }
    
	
}

