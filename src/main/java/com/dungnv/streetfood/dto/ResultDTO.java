/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.dto;

import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vtsoft
 */
@XmlRootElement
public class ResultDTO {

    private String id;
    private String key;
    private String message;
    private int quantitySucc;
    private int quantityFail;

    private String requestTime;
    private String finishTime;
    private List lstResult;

    public List getLstResult() {
        return lstResult;
    }

    public void setLstResult(List lstResult) {
        this.lstResult = lstResult;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public ResultDTO(String id, String key, String message) {
        this.id = id;
        this.key = key;
        this.message = message;
    }

    public ResultDTO(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public ResultDTO(String id, String key, String message, int quantitySucc, int quantityFail, String requestTime, String finishTime) {
        this.id = id;
        this.key = key;
        this.message = message;
        this.quantitySucc = quantitySucc;
        this.quantityFail = quantityFail;
        this.requestTime = requestTime;
        this.finishTime = finishTime;
    }

    public ResultDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getQuantitySucc() {
        return quantitySucc;
    }

    public void setQuantitySucc(int quantitySucc) {
        this.quantitySucc = quantitySucc;
    }

    public int getQuantityFail() {
        return quantityFail;
    }

    public void setQuantityFail(int quantityFail) {
        this.quantityFail = quantityFail;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.key);
        hash = 29 * hash + Objects.hashCode(this.message);
        hash = 29 * hash + this.quantitySucc;
        hash = 29 * hash + this.quantityFail;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResultDTO other = (ResultDTO) obj;
        if (this.quantitySucc != other.quantitySucc) {
            return false;
        }
        if (this.quantityFail != other.quantityFail) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        if (!Objects.equals(this.message, other.message)) {
            return false;
        }
        return true;
    }
    
    

}
