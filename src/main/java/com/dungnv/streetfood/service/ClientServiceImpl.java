
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.dto.ResultDTO;
import com.dungnv.utils.BundleUtils;
import com.dungnv.ws.provider.CxfWsClientFactory;
import com.dungnv.ws.provider.WsEndpoint;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 *
 * @author tuanpv14
 * @version 1.0
 * @since 8/7/2015 8:15 AM
 */
public class ClientServiceImpl implements ClientService {

    public static final Logger logger = Logger.getLogger(ClientService.class);
    CxfWsClientFactory wsClientFactory;
    private ClientService client;
    public static String strWsWMSUrl = BundleUtils.getResourceConfig("foodstreet_client_ws_url");
    public static String targetNamePath = BundleUtils.getResourceConfig("foodstreet_client_ws_targetNamePath");
    public static String timeOut = BundleUtils.getResourceConfig("timeOut");

    private static ClientServiceImpl instance = null;

   /**
     *
     * @return
     */
    public static synchronized ClientServiceImpl getInstance() {
        if (instance == null) {
            instance = new ClientServiceImpl();
        }
        return instance;
    }

    public ClientServiceImpl() {
        try {

            wsClientFactory = new CxfWsClientFactory();
            Map<String, WsEndpoint> map = new HashMap<String, WsEndpoint>();
            WsEndpoint enpoint = new WsEndpoint();
            enpoint.setAddress(strWsWMSUrl);
            enpoint.setTargetNameSpace(targetNamePath);
            enpoint.setTimeout(Integer.valueOf(timeOut));
            map.put(ClientService.class.getName(), enpoint);
            wsClientFactory.setWsEndpointMap(map);
            client = wsClientFactory.createWsClient(ClientService.class);
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
        }
    }

    @Override
    public List<ArticleDTO> getListArticleDTO(ArticleDTO articleDTO, int rowStart, int maxRow, String sortType, String sortFieldList) {
        return client.getListArticleDTO(articleDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public String updateArticle(String username, ArticleDTO articleDTO) {
        return client.updateArticle(username, articleDTO);
    }

    @Override
    public String deleteArticle(String username, Long id) {
        return client.deleteArticle(username, id);
    }

    @Override
    public ResultDTO insertArticle(String username, ArticleDTO articleDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArticleDTO findArticleById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String insertOrUpdateListArticle(List<ArticleDTO> articleDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultDTO insertCategory(String userName, String localeCode, String countryCode, String token, CategoryDTO categoryDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultDTO updateCategory(String userName, String localeCode, String countryCode, String token, CategoryDTO categoryDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultDTO deleteCategory(String userName, String localeCode, String countryCode, String token, String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultDTO login(String userName, String localeCode, String countryCode, String password) {
        return client.login(userName, localeCode, countryCode, password);
    }

   
}
