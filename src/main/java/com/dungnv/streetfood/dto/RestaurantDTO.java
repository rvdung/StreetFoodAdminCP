
/*
* Copyright (C) 2011 dungnv. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.dto;

import java.util.List;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/27/2016 11:45 PM
 */
public class RestaurantDTO {
    //Fields

    private String id;
    private String name;
    private String lon;
    private String lat;
    private String videoUrl;
    private String address;
    private String phoneNumber;
    private String siteUrl;
    private String introduce;
    private String priceFromVn;
    private String priceFromEn;
    private String priceToVn;
    private String priceToEn;
    private String carParking;
    private String motobikeParking;
    private String capacity;
    private String waitingTime;
    private String operatingTimeStart;
    private String operatingTimeEnd;
    private String restaurantStatus;
    private String restaurantUpdateTime;
    private String restaurantCreateTime;
    private String restaurantUpdateTimeGmt;
    private String restaurantCreateTimeGmt;
    private String viewCount;
    private String commentCount;
    private String shareCount;
    private String rating;

    protected String defaultSortField;

    private String viewCountFrom;
    private String commentCountFrom;
    private String shareCountFrom;
    private String ratingFrom;
    private String waitingTimeFrom;

    private String viewCountTo;
    private String commentCountTo;
    private String shareCountTo;
    private String ratingTo;
    private String waitingTimeTo;

    private String dishId;
    private String notDishId;
    private String articleId;
    private String notArticleId;
    private String isGetOnlyIdentified;
    private List<RestaurantLanguageDTO> listLanguage;
    private List<String> listTag;
    private List<String> listImgUrl;
    private List<String> listLocale;
    private List<String> listNotLocale;
    private String imageId;
    private String imageUrl;

    //Constructor
    public RestaurantDTO() {
    }

    public RestaurantDTO(String id, String name, String lon, String lat, String videoUrl, String address, String phoneNumber, String siteUrl, String introduce, String priceFromVn, String priceFromEn, String priceToVn, String priceToEn, String carParking, String motobikeParking, String capacity, String waitingTime, String operatingTimeStart, String operatingTimeEnd, String restaurantStatus, String restaurantUpdateTime, String restaurantCreateTime, String restaurantUpdateTimeGmt, String restaurantCreateTimeGmt, String viewCount, String commentCount, String shareCount, String rating) {
        this.id = id;
        this.name = name;
        this.lon = lon;
        this.lat = lat;
        this.videoUrl = videoUrl;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.siteUrl = siteUrl;
        this.introduce = introduce;
        this.priceFromVn = priceFromVn;
        this.priceFromEn = priceFromEn;
        this.priceToVn = priceToVn;
        this.priceToEn = priceToEn;
        this.carParking = carParking;
        this.motobikeParking = motobikeParking;
        this.capacity = capacity;
        this.waitingTime = waitingTime;
        this.operatingTimeStart = operatingTimeStart;
        this.operatingTimeEnd = operatingTimeEnd;
        this.restaurantStatus = restaurantStatus;
        this.restaurantUpdateTime = restaurantUpdateTime;
        this.restaurantCreateTime = restaurantCreateTime;
        this.restaurantUpdateTimeGmt = restaurantUpdateTimeGmt;
        this.restaurantCreateTimeGmt = restaurantCreateTimeGmt;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.shareCount = shareCount;
        this.rating = rating;
    }
    //Getters and setters

    public String getViewCountFrom() {
        return viewCountFrom;
    }

    public void setViewCountFrom(String viewCountFrom) {
        this.viewCountFrom = viewCountFrom;
    }

    public String getCommentCountFrom() {
        return commentCountFrom;
    }

    public void setCommentCountFrom(String commentCountFrom) {
        this.commentCountFrom = commentCountFrom;
    }

    public String getShareCountFrom() {
        return shareCountFrom;
    }

    public void setShareCountFrom(String shareCountFrom) {
        this.shareCountFrom = shareCountFrom;
    }

    public String getRatingFrom() {
        return ratingFrom;
    }

    public void setRatingFrom(String ratingFrom) {
        this.ratingFrom = ratingFrom;
    }

    public String getWaitingTimeFrom() {
        return waitingTimeFrom;
    }

    public void setWaitingTimeFrom(String waitingTimeFrom) {
        this.waitingTimeFrom = waitingTimeFrom;
    }

    public String getViewCountTo() {
        return viewCountTo;
    }

    public void setViewCountTo(String viewCountTo) {
        this.viewCountTo = viewCountTo;
    }

    public String getCommentCountTo() {
        return commentCountTo;
    }

    public void setCommentCountTo(String commentCountTo) {
        this.commentCountTo = commentCountTo;
    }

    public String getShareCountTo() {
        return shareCountTo;
    }

    public void setShareCountTo(String shareCountTo) {
        this.shareCountTo = shareCountTo;
    }

    public String getRatingTo() {
        return ratingTo;
    }

    public void setRatingTo(String ratingTo) {
        this.ratingTo = ratingTo;
    }

    public String getWaitingTimeTo() {
        return waitingTimeTo;
    }

    public void setWaitingTimeTo(String waitingTimeTo) {
        this.waitingTimeTo = waitingTimeTo;
    }

    public List<String> getListLocale() {
        return listLocale;
    }

    public void setListLocale(List<String> listLocale) {
        this.listLocale = listLocale;
    }

    public List<String> getListNotLocale() {
        return listNotLocale;
    }

    public void setListNotLocale(List<String> listNotLocale) {
        this.listNotLocale = listNotLocale;
    }

    public String getDishId() {
        return dishId;
    }

    public void setDishId(String dishId) {
        this.dishId = dishId;
    }

    public String getNotDishId() {
        return notDishId;
    }

    public void setNotDishId(String notDishId) {
        this.notDishId = notDishId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getNotArticleId() {
        return notArticleId;
    }

    public void setNotArticleId(String notArticleId) {
        this.notArticleId = notArticleId;
    }

    public String getIsGetOnlyIdentified() {
        return isGetOnlyIdentified;
    }

    public void setIsGetOnlyIdentified(String isGetOnlyIdentified) {
        this.isGetOnlyIdentified = isGetOnlyIdentified;
    }

    public String getDefaultSortField() {
        return defaultSortField;
    }

    public void setDefaultSortField(String defaultSortField) {
        this.defaultSortField = defaultSortField;
    }

    public List<RestaurantLanguageDTO> getListLanguage() {
        return listLanguage;
    }

    public void setListLanguage(List<RestaurantLanguageDTO> listLanguage) {
        this.listLanguage = listLanguage;
    }

    public List<String> getListTag() {
        return listTag;
    }

    public void setListTag(List<String> listTag) {
        this.listTag = listTag;
    }

    public List<String> getListImgUrl() {
        return listImgUrl;
    }

    public void setListImgUrl(List<String> listImgUrl) {
        this.listImgUrl = listImgUrl;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

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

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLon() {
        return lon;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLat() {
        return lat;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setPriceFromVn(String priceFromVn) {
        this.priceFromVn = priceFromVn;
    }

    public String getPriceFromVn() {
        return priceFromVn;
    }

    public void setPriceFromEn(String priceFromEn) {
        this.priceFromEn = priceFromEn;
    }

    public String getPriceFromEn() {
        return priceFromEn;
    }

    public void setPriceToVn(String priceToVn) {
        this.priceToVn = priceToVn;
    }

    public String getPriceToVn() {
        return priceToVn;
    }

    public void setPriceToEn(String priceToEn) {
        this.priceToEn = priceToEn;
    }

    public String getPriceToEn() {
        return priceToEn;
    }

    public void setCarParking(String carParking) {
        this.carParking = carParking;
    }

    public String getCarParking() {
        return carParking;
    }

    public void setMotobikeParking(String motobikeParking) {
        this.motobikeParking = motobikeParking;
    }

    public String getMotobikeParking() {
        return motobikeParking;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setWaitingTime(String waitingTime) {
        this.waitingTime = waitingTime;
    }

    public String getWaitingTime() {
        return waitingTime;
    }

    public void setOperatingTimeStart(String operatingTimeStart) {
        this.operatingTimeStart = operatingTimeStart;
    }

    public String getOperatingTimeStart() {
        return operatingTimeStart;
    }

    public void setOperatingTimeEnd(String operatingTimeEnd) {
        this.operatingTimeEnd = operatingTimeEnd;
    }

    public String getOperatingTimeEnd() {
        return operatingTimeEnd;
    }

    public void setRestaurantStatus(String restaurantStatus) {
        this.restaurantStatus = restaurantStatus;
    }

    public String getRestaurantStatus() {
        return restaurantStatus;
    }

    public void setRestaurantUpdateTime(String restaurantUpdateTime) {
        this.restaurantUpdateTime = restaurantUpdateTime;
    }

    public String getRestaurantUpdateTime() {
        return restaurantUpdateTime;
    }

    public void setRestaurantCreateTime(String restaurantCreateTime) {
        this.restaurantCreateTime = restaurantCreateTime;
    }

    public String getRestaurantCreateTime() {
        return restaurantCreateTime;
    }

    public void setRestaurantUpdateTimeGmt(String restaurantUpdateTimeGmt) {
        this.restaurantUpdateTimeGmt = restaurantUpdateTimeGmt;
    }

    public String getRestaurantUpdateTimeGmt() {
        return restaurantUpdateTimeGmt;
    }

    public void setRestaurantCreateTimeGmt(String restaurantCreateTimeGmt) {
        this.restaurantCreateTimeGmt = restaurantCreateTimeGmt;
    }

    public String getRestaurantCreateTimeGmt() {
        return restaurantCreateTimeGmt;
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
