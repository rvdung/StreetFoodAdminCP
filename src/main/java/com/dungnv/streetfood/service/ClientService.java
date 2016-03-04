
/*
* Copyright (C) 2011 dungnv. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.dto.LocaleDTO;
import com.dungnv.streetfood.dto.RestaurantDTO;
import com.dungnv.streetfood.dto.ResultDTO;
import com.dungnv.streetfood.dto.SlideShowDTO;
import com.dungnv.streetfood.dto.TagsDTO;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebParam;
import javax.jws.WebMethod;

/**
 * @author dungnv
 * @version 1.0
 * @since 1/21/2016 12:48 AM
 */
@WebService
public interface ClientService {

    //Category
    @WebMethod(operationName = "insertCategory")
    public ResultDTO insertCategory(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "categoryDTO") CategoryDTO categoryDTO);

    @WebMethod(operationName = "updateCategory")
    public ResultDTO updateCategory(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "categoryDTO") CategoryDTO categoryDTO);

    @WebMethod(operationName = "deleteCategory")
    public ResultDTO deleteCategory(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "categoryDTOId") String id);

    @WebMethod(operationName = "getListCategoryDTOLess")
    public List<CategoryDTO> getListCategoryDTOLess(//
            @WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "categoryDTO") CategoryDTO categoryDTO//
            , @WebParam(name = "rowStart") int rowStart//
            , @WebParam(name = "maxRow") int maxRow//
            , @WebParam(name = "isCount") boolean isCount//
            , @WebParam(name = "sortType") String sortType//
            , @WebParam(name = "sortFieldList") String sortFieldList);

    @WebMethod(operationName = "getCategoryDetail")
    public CategoryDTO getCategoryDetail(//
            @WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "id") String id);

    // Dish
    @WebMethod(operationName = "insertDish")
    public ResultDTO insertDish(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "dishDTO") DishDTO dishDTO);

    //
    @WebMethod(operationName = "updateDish")
    public ResultDTO updateDish(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "dishDTO") DishDTO dishDTO);

    //
    @WebMethod(operationName = "deleteDish")
    public ResultDTO deleteDish(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "dishDTOId") Long id);

    @WebMethod(operationName = "getListDishDTOLess")
    public List<DishDTO> getListDishDTOLess(//
            @WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "dishDTO") DishDTO dishDTO//
            , @WebParam(name = "rowStart") int rowStart//
            , @WebParam(name = "maxRow") int maxRow//
            , @WebParam(name = "isCount") boolean isCount//
            , @WebParam(name = "sortType") String sortType//
            , @WebParam(name = "sortFieldList") String sortFieldList);

    @WebMethod(operationName = "getDishDetail")
    public DishDTO getDishDetail(//
            @WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "id") String id);

    // Restaurant
    @WebMethod(operationName = "insertRestaurant")
    public ResultDTO insertRestaurant(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "restaurantDTO") RestaurantDTO restaurantDTO);

    //
    @WebMethod(operationName = "updateRestaurant")
    public ResultDTO updateRestaurant(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "restaurantDTO") RestaurantDTO restaurantDTO);

    //
    @WebMethod(operationName = "deleteRestaurant")
    public ResultDTO deleteRestaurant(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "restaurantDTOId") Long id);

    @WebMethod(operationName = "getListRestaurantDTOLess")
    public List<RestaurantDTO> getListRestaurantDTOLess(//
            @WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "restaurantDTO") RestaurantDTO restaurantDTO//
            , @WebParam(name = "rowStart") int rowStart//
            , @WebParam(name = "maxRow") int maxRow//
            , @WebParam(name = "isCount") boolean isCount//
            , @WebParam(name = "sortType") String sortType//
            , @WebParam(name = "sortFieldList") String sortFieldList);

    @WebMethod(operationName = "getRestaurantDetail")
    public RestaurantDTO getRestaurantDetail(//
            @WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "id") String id);

    // Article
    @WebMethod(operationName = "insertArticle")
    public ResultDTO insertArticle(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "articleDTO") ArticleDTO articleDTO);

    //
    @WebMethod(operationName = "updateArticle")
    public ResultDTO updateArticle(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "articleDTO") ArticleDTO articleDTO);

    //
    @WebMethod(operationName = "deleteArticle")
    public ResultDTO deleteArticle(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "articleDTOId") Long id);

    @WebMethod(operationName = "getListArticleDTOLess")
    public List<ArticleDTO> getListArticleDTOLess(//
            @WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "articleDTO") ArticleDTO articleDTO//
            , @WebParam(name = "rowStart") int rowStart//
            , @WebParam(name = "maxRow") int maxRow//
            , @WebParam(name = "isCount") boolean isCount//
            , @WebParam(name = "sortType") String sortType//
            , @WebParam(name = "sortFieldList") String sortFieldList);

    @WebMethod(operationName = "getArticleDetail")
    public ArticleDTO getArticleDetail(//
            @WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "id") String id);

    // SlideShow
    @WebMethod(operationName = "insertSlideShow")
    public ResultDTO insertSlideShow(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "slideShowDTO") SlideShowDTO slideShowDTO);

    //
    @WebMethod(operationName = "updateSlideShow")
    public ResultDTO updateSlideShow(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "slideShowDTO") SlideShowDTO slideShowDTO);

    //
    @WebMethod(operationName = "deleteSlideShow")
    public ResultDTO deleteSlideShow(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "slideShowDTOId") Long id);

    @WebMethod(operationName = "getListSlideShowDTOLess")
    public List<SlideShowDTO> getListSlideShowDTOLess(//
            @WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "slideShowDTO") SlideShowDTO slideShowDTO//
            , @WebParam(name = "rowStart") int rowStart//
            , @WebParam(name = "maxRow") int maxRow//
            , @WebParam(name = "isCount") boolean isCount//
            , @WebParam(name = "sortType") String sortType//
            , @WebParam(name = "sortFieldList") String sortFieldList);

    @WebMethod(operationName = "getSlideShowDetail")
    public SlideShowDTO getSlideShowDetail(//
            @WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "id") String id);

    // CategoryDish
    @WebMethod(operationName = "insertListDishToCategory")
    public ResultDTO insertListDishToCategory(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "id") String id//
            , @WebParam(name = "list") List<String> list);

    @WebMethod(operationName = "insertListCategoryToDish")
    public ResultDTO insertListCategoryToDish(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "id") String id//
            , @WebParam(name = "list") List<String> list);

    // DishArticle
    @WebMethod(operationName = "insertListDishToArticle")
    public ResultDTO insertListDishToArticle(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "id") String id//
            , @WebParam(name = "list") List<String> list);

    @WebMethod(operationName = "insertListArticleToDish")
    public ResultDTO insertListArticleToDish(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "id") String id//
            , @WebParam(name = "list") List<String> list);

    // RestaurantDishDetail
    @WebMethod(operationName = "insertListDishToRestaurant")
    public ResultDTO insertListDishToRestaurant(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "id") String id//
            , @WebParam(name = "list") List<String> list);

    @WebMethod(operationName = "insertListRestaurantToDish")
    public ResultDTO insertListRestaurantToDish(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "id") String id//
            , @WebParam(name = "list") List<String> list);

    // RestaurantArticle
    @WebMethod(operationName = "insertListRestaurantToArticle")
    public ResultDTO insertListRestaurantToArticle(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "id") String id//
            , @WebParam(name = "list") List<String> list);

    @WebMethod(operationName = "insertListArticleToRestaurant")
    public ResultDTO insertListArticleToRestaurant(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "id") String id//
            , @WebParam(name = "list") List<String> list);

    //User
    @WebMethod(operationName = "login")
    public ResultDTO login(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "password") String password);

    // TAG
    public List<TagsDTO> getListTagsDTO(//
            @WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "tagsDTO") TagsDTO tagsDTO//
            , @WebParam(name = "rowStart") int rowStart//
            , @WebParam(name = "maxRow") int maxRow//
            , @WebParam(name = "sortType") String sortType//
            , @WebParam(name = "sortFieldList") String sortFieldList);

    //Locale 
    @WebMethod(operationName = "getListLocaleDTO")
    public List<LocaleDTO> getListLocaleDTO(//
            @WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "token") String token//
            , @WebParam(name = "localeDTO") LocaleDTO localeDTO//
            , @WebParam(name = "rowStart") int rowStart//
            , @WebParam(name = "maxRow") int maxRow//
            , @WebParam(name = "sortType") String sortType//
            , @WebParam(name = "sortFieldList") String sortFieldList);

}
