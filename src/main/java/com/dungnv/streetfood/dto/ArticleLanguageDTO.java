
/*
* Copyright (C) 2011 dungnv. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
*/
package com.dungnv.streetfood.dto;

/**
* @author dungnv
* @version 1.0
* @since 1/25/2016 10:07 PM
*/
public class ArticleLanguageDTO{    
	//Fields
    private String id;
    private String title;
    private String content;
    private String shortContent;
    private String languageCode;
    //Constructor
	public ArticleLanguageDTO() {	
	}
	public ArticleLanguageDTO(String id, String title, String content, String shortContent, String languageCode) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.shortContent = shortContent;
		this.languageCode = languageCode;
	}	
	//Getters and setters
    
	public void setId(String id) {
        this.id = id;
    }
	public String getId() {		
        return id;		
    }
    
	public void setTitle(String title) {
        this.title = title;
    }
	public String getTitle() {		
        return title;		
    }
    
	public void setContent(String content) {
        this.content = content;
    }
	public String getContent() {		
        return content;		
    }
    
	public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }
	public String getShortContent() {		
        return shortContent;		
    }
    
	public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }
	public String getLanguageCode() {		
        return languageCode;		
    }
    
	
}

