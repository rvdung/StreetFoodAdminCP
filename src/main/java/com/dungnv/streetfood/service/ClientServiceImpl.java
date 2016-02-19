
/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.dungnv.streetfood.service;

import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.dto.LocaleDTO;
import com.dungnv.streetfood.dto.ResultDTO;
import com.dungnv.streetfood.dto.TagsDTO;
import com.dungnv.streetfood.dto.UserDTO;
import com.dungnv.utils.BundleUtils;
import com.dungnv.utils.DateTimeUtils;
import com.dungnv.ws.provider.CxfWsClientFactory;
import com.dungnv.ws.provider.WsEndpoint;
import com.vaadin.server.VaadinSession;
import java.util.Calendar;
import java.util.Date;
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

    private static Map<String, TagsDTO> allTags = new HashMap<String, TagsDTO>();
    private static Date reloadAllTags;
    private static Map<String, LocaleDTO> allLocales = new HashMap<String, LocaleDTO>();
    private static Date reloadAllLocales;

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
        return client.insertCategory(userName, localeCode, countryCode, token, categoryDTO);
    }

    @Override
    public ResultDTO updateCategory(String userName, String localeCode, String countryCode, String token, CategoryDTO categoryDTO) {
        return client.updateCategory(userName, localeCode, countryCode, token, categoryDTO);
    }

    @Override
    public ResultDTO deleteCategory(String userName, String localeCode, String countryCode, String token, String id) {
        return client.deleteCategory(userName, localeCode, countryCode, token, id);
    }

    @Override
    public ResultDTO login(String userName, String localeCode, String countryCode, String password) {
        return client.login(userName, localeCode, countryCode, password);
    }

    public synchronized static Map<String, TagsDTO> getAllTags() {
        if (allTags == null || reloadAllTags == null
                || DateTimeUtils.getTimeBeetweenDates(reloadAllTags, new Date(), Calendar.MINUTE) > 5) {
            if (allTags != null) {
                allTags.clear();
            }
            UserDTO user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());
            List<TagsDTO> list = getInstance().getListTagsDTO(user.getUsername(), "en", "US", null, new TagsDTO(), 0, 0, "ASC", "name");
            for (TagsDTO tags : list) {
                allTags.put(tags.getName().toLowerCase(), tags);
            }
        }
        return allTags;
    }

    public synchronized static Map<String, LocaleDTO> getAllLocales() {
        if (allLocales == null || reloadAllLocales == null
                || DateTimeUtils.getTimeBeetweenDates(reloadAllLocales, new Date(), Calendar.MINUTE) > 5) {
            if (allLocales != null) {
                allLocales.clear();
            }
            UserDTO user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());
            List<LocaleDTO> list = getInstance().getListLocaleDTO(user.getUsername(), "en", "US", null//
                    , new LocaleDTO(null, null, null, "1"), 0, 0, "ASC", "locale");
            for (LocaleDTO dto : list) {
                allLocales.put(dto.getId(), dto);
            }
        }
        return allLocales;
    }

    @Override
    public List<CategoryDTO> getListCategoryDTOLess(String userName, String localeCode//
            , String countryCode, String token, CategoryDTO categoryDTO, int rowStart//
            , int maxRow, boolean isCount, String sortType, String sortFieldList) {
        return client.getListCategoryDTOLess(userName, localeCode, countryCode, token, categoryDTO, rowStart, maxRow, isCount, sortType, sortFieldList);
    }

    @Override
    public CategoryDTO getCategoryDetail(String userName, String localeCode//
            , String countryCode, String token, String id) {
        return client.getCategoryDetail(userName, localeCode, countryCode, token, id);
    }

    @Override
    public List<TagsDTO> getListTagsDTO(String userName, String localeCode//
            , String countryCode, String token, TagsDTO tagsDTO, int rowStart//
            , int maxRow, String sortType, String sortFieldList) {
        return client.getListTagsDTO(userName, localeCode, countryCode, token//
                , tagsDTO, rowStart, maxRow, sortType, sortFieldList);
    }

    @Override
    public List<LocaleDTO> getListLocaleDTO(String userName, String localeCode//
            , String countryCode, String token, LocaleDTO localeDTO, int rowStart//
            , int maxRow, String sortType, String sortFieldList) {
        return client.getListLocaleDTO(userName, localeCode, countryCode, token//
                , localeDTO, rowStart, maxRow, sortType, sortFieldList);
    }

}
