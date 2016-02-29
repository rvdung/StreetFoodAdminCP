/**
 * @(#)UserForm.java ,
 * Copyright 2011 dungnv. All rights reserved
 * VIETTEL PROPRIETARY/CONFIDENTIAL
 */
package com.dungnv.streetfood.dto;

public class UserDTO {
    //Fields

    private String id;
    private String fullname;
    private String email;
    private String birthday;
    private String username;
    private String password;
    private String fistname;
    private String lastname;
    private String userStatus;
    private String userUpdateTime;
    private String userCreateTime;
    private String userUpdateTimeGmt;
    private String userCreateTimeGmt;
    private String sysRoleId;
    private String sysRoleIdName;
    private String iduserToken;
    private String isActive;
    private String refreshToken;

    //Constructor
    public UserDTO() {
    }

    public UserDTO(String id, String fullname, String email, String birthday, String username, String password, String fistname, String lastname, String userStatus, String userUpdateTime, String userCreateTime, String userUpdateTimeGmt, String userCreateTimeGmt, String sysRoleId, String iduserToken, String isActive, String refreshToken) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
        this.fistname = fistname;
        this.lastname = lastname;
        this.userStatus = userStatus;
        this.userUpdateTime = userUpdateTime;
        this.userCreateTime = userCreateTime;
        this.userUpdateTimeGmt = userUpdateTimeGmt;
        this.userCreateTimeGmt = userCreateTimeGmt;
        this.sysRoleId = sysRoleId;
        this.iduserToken = iduserToken;
        this.isActive = isActive;
        this.refreshToken = refreshToken;
    }
    //Getters and setters

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setFistname(String fistname) {
        this.fistname = fistname;
    }

    public String getFistname() {
        return fistname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserUpdateTime(String userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    public String getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserCreateTime(String userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public String getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserUpdateTimeGmt(String userUpdateTimeGmt) {
        this.userUpdateTimeGmt = userUpdateTimeGmt;
    }

    public String getUserUpdateTimeGmt() {
        return userUpdateTimeGmt;
    }

    public void setUserCreateTimeGmt(String userCreateTimeGmt) {
        this.userCreateTimeGmt = userCreateTimeGmt;
    }

    public String getUserCreateTimeGmt() {
        return userCreateTimeGmt;
    }

    public void setSysRoleId(String sysRoleId) {
        this.sysRoleId = sysRoleId;
    }

    public String getSysRoleId() {
        return sysRoleId;
    }

    public void setSysRoleIdName(String sysRoleIdName) {
        this.sysRoleIdName = sysRoleIdName;
    }

    public String getSysRoleIdName() {
        return sysRoleIdName;
    }

    public void setIduserToken(String iduserToken) {
        this.iduserToken = iduserToken;
    }

    public String getIduserToken() {
        return iduserToken;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

}
