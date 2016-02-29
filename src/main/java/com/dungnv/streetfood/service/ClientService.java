
/*
* Copyright (C) 2011 Viettel Telecom. All rights reserved.
* VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.dto.ResultDTO;
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

    @WebMethod(operationName = "getListArticleDTO")
    public List<ArticleDTO> getListArticleDTO(@WebParam(name = "articleDTO") ArticleDTO articleDTO, @WebParam(name = "rowStart") int rowStart, @WebParam(name = "maxRow") int maxRow, @WebParam(name = "sortType") String sortType, @WebParam(name = "sortFieldList") String sortFieldList);

    @WebMethod(operationName = "updateArticle")
    public String updateArticle(@WebParam(name = "username") String username, @WebParam(name = "articleDTO") ArticleDTO articleDTO);

    @WebMethod(operationName = "deleteArticle")
    public String deleteArticle(@WebParam(name = "username") String username, @WebParam(name = "articleDTOId") Long id);

    @WebMethod(operationName = "insertArticle")
    public ResultDTO insertArticle(@WebParam(name = "username") String username, @WebParam(name = "articleDTO") ArticleDTO articleDTO);

    @WebMethod(operationName = "findArticleById")
    public ArticleDTO findArticleById(@WebParam(name = "articleDTOId") Long id);

    @WebMethod(operationName = "insertOrUpdateListArticle")
    public String insertOrUpdateListArticle(@WebParam(name = "articleDTO") List<ArticleDTO> articleDTO);

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
    
    //User
    @WebMethod(operationName = "login")
    public ResultDTO login(@WebParam(name = "userName") String userName//
            , @WebParam(name = "localeCode") String localeCode//
            , @WebParam(name = "countryCode") String countryCode//
            , @WebParam(name = "password") String password);
}
