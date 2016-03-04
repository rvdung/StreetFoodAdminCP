/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.view;

import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.dto.RestaurantDTO;
import com.dungnv.streetfood.dto.ResultDTO;
import com.dungnv.streetfood.dto.UserDTO;
import com.dungnv.streetfood.service.ClientServiceImpl;
import com.dungnv.streetfood.ui.TwinColumnUI;
import com.dungnv.utils.BundleUtils;
import com.dungnv.utils.Constants;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ODIN NGUYEN
 */
public class DishLink extends Window {

    private final DishDTO dto;

    TabSheet tabSheet;
    UserDTO user;

    TwinColumnUI tuiCategory;
    BeanItemContainer<CategoryDTO> categoryLeftContainer;
    BeanItemContainer<CategoryDTO> categoryRightContainer;
    ListSelect lsCategoryLeft;
    ListSelect lsCategoryRight;

    TwinColumnUI tuiRestaurant;
    BeanItemContainer<RestaurantDTO> restaurantLeftContainer;
    BeanItemContainer<RestaurantDTO> restaurantRightContainer;
    ListSelect lsRestaurantLeft;
    ListSelect lsRestaurantRight;
    Boolean isLoadedRestaurant;

    TwinColumnUI tuiArticle;
    BeanItemContainer<ArticleDTO> articleLeftContainer;
    BeanItemContainer<ArticleDTO> articleRightContainer;
    ListSelect lsArticleLeft;
    ListSelect lsArticleRight;
    Boolean isLoadedArticle;

    public DishLink(DishDTO dto) {
        isLoadedRestaurant = false;
        isLoadedArticle = false;
        this.categoryLeftContainer = new BeanItemContainer<>(CategoryDTO.class);
        this.categoryRightContainer = new BeanItemContainer<>(CategoryDTO.class);

        this.restaurantLeftContainer = new BeanItemContainer<>(RestaurantDTO.class);
        this.restaurantRightContainer = new BeanItemContainer<>(RestaurantDTO.class);

        this.articleLeftContainer = new BeanItemContainer<>(ArticleDTO.class);
        this.articleRightContainer = new BeanItemContainer<>(ArticleDTO.class);

        this.dto = dto;
        init();
        setValueCategory();
        setActionCategory();
    }

    private void init() {

        setLocale(VaadinSession.getCurrent().getLocale());

        VerticalLayout vLayout = new VerticalLayout();

        tabSheet = new TabSheet();
        tabSheet.setStyleName(ValoTheme.TABSHEET_EQUAL_WIDTH_TABS);
        tabSheet.setSizeFull();
        vLayout.addComponent(tabSheet);

        // CategoryDish
        tuiCategory = new TwinColumnUI("name");
        tuiCategory.setLeftLabelCaption(BundleUtils.getLanguage("lbl.category.avaiable.list"));
        tuiCategory.setRightLabelCaption(BundleUtils.getLanguage("lbl.category.selected.list"));
        tabSheet.addTab(tuiCategory, BundleUtils.getLanguage("lbl.dish.categoryDish.tab"));

        // RestaurantDish
        tuiRestaurant = new TwinColumnUI("name");
        tuiRestaurant.setLeftLabelCaption(BundleUtils.getLanguage("lbl.restaurant.avaiable.list"));
        tuiRestaurant.setRightLabelCaption(BundleUtils.getLanguage("lbl.restaurant.selected.list"));
        tabSheet.addTab(tuiRestaurant, BundleUtils.getLanguage("lbl.dish.restaurantDish.tab"));

        // DishArticle
        tuiArticle = new TwinColumnUI("title");
        tuiArticle.setLeftLabelCaption(BundleUtils.getLanguage("lbl.article.avaiable.list"));
        tuiArticle.setRightLabelCaption(BundleUtils.getLanguage("lbl.article.selected.list"));
        tabSheet.addTab(tuiArticle, BundleUtils.getLanguage("lbl.dish.dishArticle.tab"));

        setContent(tabSheet);
    }

    private void setValueCategory() {

        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                if (!isLoadedRestaurant && event.getTabSheet().getSelectedTab().equals(tuiRestaurant)) {
                    isLoadedRestaurant = true;
                    setValueRestaurant();
                    setActionRestaurant();
                }

                if (!isLoadedArticle && event.getTabSheet().getSelectedTab().equals(tuiArticle)) {
                    isLoadedArticle = true;
                    setValueArticle();
                    setActionArticle();
                }
                if (isLoadedRestaurant && isLoadedArticle) {
                    tabSheet.removeSelectedTabChangeListener(this);
                }
            }
        });

        lsCategoryLeft = tuiCategory.getMsLeft().getLsItem();
        lsCategoryRight = tuiCategory.getMsRight().getLsItem();

        user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setNotDishId(dto.getId());
        categoryDTO.setIsGetOnlyIdentified("1");
        List<CategoryDTO> categoryLeftList = ClientServiceImpl.getInstance().getListCategoryDTOLess(user.getUsername()//
                , getLocale().getLanguage(), getLocale().getCountry(), null, categoryDTO, 0, 0, false, "ASC", "name");

        categoryDTO.setNotDishId(null);
        categoryDTO.setDishId(dto.getId());
        List<CategoryDTO> categoryRightList = ClientServiceImpl.getInstance().getListCategoryDTOLess(user.getUsername()//
                , getLocale().getLanguage(), getLocale().getCountry(), null, categoryDTO, 0, 0, false, "ASC", "name");

        if (categoryLeftList != null) {
            categoryLeftContainer.addAll(categoryLeftList);
        }
        if (categoryRightList != null) {
            categoryRightContainer.addAll(categoryRightList);
        }

        lsCategoryLeft.setContainerDataSource(categoryLeftContainer);
        lsCategoryRight.setContainerDataSource(categoryRightContainer);
    }

    private void setValueRestaurant() {

        lsRestaurantLeft = tuiRestaurant.getMsLeft().getLsItem();
        lsRestaurantRight = tuiRestaurant.getMsRight().getLsItem();

        user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());

        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setNotDishId(dto.getId());
        restaurantDTO.setIsGetOnlyIdentified("1");
        List<RestaurantDTO> restaurantLeftList = ClientServiceImpl.getInstance().getListRestaurantDTOLess(user.getUsername()//
                , getLocale().getLanguage(), getLocale().getCountry(), null, restaurantDTO, 0, 0, false, "ASC", "name");

        restaurantDTO.setNotDishId(null);
        restaurantDTO.setDishId(dto.getId());
        List<RestaurantDTO> restaurantRightList = ClientServiceImpl.getInstance().getListRestaurantDTOLess(user.getUsername()//
                , getLocale().getLanguage(), getLocale().getCountry(), null, restaurantDTO, 0, 0, false, "ASC", "name");

        if (restaurantLeftList != null) {
            restaurantLeftContainer.addAll(restaurantLeftList);
        }
        if (restaurantRightList != null) {
            restaurantRightContainer.addAll(restaurantRightList);
        }

        lsRestaurantLeft.setContainerDataSource(restaurantLeftContainer);
        lsRestaurantRight.setContainerDataSource(restaurantRightContainer);
    }

    private void setValueArticle() {

        lsArticleLeft = tuiArticle.getMsLeft().getLsItem();
        lsArticleRight = tuiArticle.getMsRight().getLsItem();

        user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());

        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setNotDishId(dto.getId());
        articleDTO.setIsGetOnlyIdentified("1");
        List<ArticleDTO> articleLeftList = ClientServiceImpl.getInstance().getListArticleDTOLess(user.getUsername()//
                , getLocale().getLanguage(), getLocale().getCountry(), null, articleDTO, 0, 0, false, "ASC", "name");

        articleDTO.setNotDishId(null);
        articleDTO.setDishId(dto.getId());
        List<ArticleDTO> articleRightList = ClientServiceImpl.getInstance().getListArticleDTOLess(user.getUsername()//
                , getLocale().getLanguage(), getLocale().getCountry(), null, articleDTO, 0, 0, false, "ASC", "name");

        if (articleLeftList != null) {
            articleLeftContainer.addAll(articleLeftList);
        }
        if (articleRightList != null) {
            articleRightContainer.addAll(articleRightList);
        }

        lsArticleLeft.setContainerDataSource(articleLeftContainer);
        lsArticleRight.setContainerDataSource(articleRightContainer);
    }

    private void setActionCategory() {
        tuiCategory.getBtnSave().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                categoryRightContainer.removeAllContainerFilters();
                List<CategoryDTO> list = categoryRightContainer.getItemIds();
                List<String> listId = new ArrayList<>();
                if (list != null) {
                    list.stream().forEach((dto) -> {
                        listId.add(dto.getId());
                    });
                }

                ResultDTO result = ClientServiceImpl.getInstance().insertListCategoryToDish(user.getUsername()//
                        , getLocale().getLanguage(), getLocale().getCountry(), null, dto.getId(), listId);
                if (result != null && Constants.SUCCESS.equals(result.getMessage())) {
                    UI.getCurrent().removeWindow(event.getButton().findAncestor(Window.class));
                } else {
                    Notification.show(result == null || result.getKey() == null ? Constants.FAIL
                            : result.getKey(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });
    }

    private void setActionRestaurant() {
        tuiRestaurant.getBtnSave().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                restaurantRightContainer.removeAllContainerFilters();
                List<RestaurantDTO> list = restaurantRightContainer.getItemIds();
                List<String> listId = new ArrayList<>();
                if (list != null) {
                    list.stream().forEach((dto) -> {
                        listId.add(dto.getId());
                    });
                }

                ResultDTO result = ClientServiceImpl.getInstance().insertListRestaurantToDish(user.getUsername()//
                        , getLocale().getLanguage(), getLocale().getCountry(), null, dto.getId(), listId);
                if (result != null && Constants.SUCCESS.equals(result.getMessage())) {
                    UI.getCurrent().removeWindow(event.getButton().findAncestor(Window.class));
                } else {
                    Notification.show(result == null || result.getKey() == null ? Constants.FAIL
                            : result.getKey(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });
    }

    private void setActionArticle() {
        tuiArticle.getBtnSave().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                articleRightContainer.removeAllContainerFilters();
                List<ArticleDTO> list = articleRightContainer.getItemIds();
                List<String> listId = new ArrayList<>();
                if (list != null) {
                    list.stream().forEach((dto) -> {
                        listId.add(dto.getId());
                    });
                }

                ResultDTO result = ClientServiceImpl.getInstance().insertListArticleToDish(user.getUsername()//
                        , getLocale().getLanguage(), getLocale().getCountry(), null, dto.getId(), listId);
                if (result != null && Constants.SUCCESS.equals(result.getMessage())) {
                    UI.getCurrent().removeWindow(event.getButton().findAncestor(Window.class));
                } else {
                    Notification.show(result == null || result.getKey() == null ? Constants.FAIL
                            : result.getKey(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });
    }

}
