/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.view;

import com.dungnv.streetfood.dto.ArticleDTO;
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
public class RestaurantLink extends Window {

    private final RestaurantDTO dto;

    TabSheet tabSheet;
    UserDTO user;

    TwinColumnUI tuiDish;
    BeanItemContainer<DishDTO> dishLeftContainer;
    BeanItemContainer<DishDTO> dishRightContainer;
    ListSelect lsDishLeft;
    ListSelect lsDishRight;

    TwinColumnUI tuiArticle;
    BeanItemContainer<ArticleDTO> articleLeftContainer;
    BeanItemContainer<ArticleDTO> articleRightContainer;
    ListSelect lsArticleLeft;
    ListSelect lsArticleRight;
    Boolean isLoadedArticle;

    public RestaurantLink(RestaurantDTO dto) {
        isLoadedArticle = false;
        this.dishLeftContainer = new BeanItemContainer<>(DishDTO.class);
        this.dishRightContainer = new BeanItemContainer<>(DishDTO.class);

        this.articleLeftContainer = new BeanItemContainer<>(ArticleDTO.class);
        this.articleRightContainer = new BeanItemContainer<>(ArticleDTO.class);

        this.dto = dto;
        init();
        setValueDish();
        setActionDish();
    }

    private void init() {

        setLocale(VaadinSession.getCurrent().getLocale());

        VerticalLayout vLayout = new VerticalLayout();

        tabSheet = new TabSheet();
        tabSheet.setStyleName(ValoTheme.TABSHEET_EQUAL_WIDTH_TABS);
        tabSheet.setSizeFull();
        vLayout.addComponent(tabSheet);

        // RestaurantDish
        tuiDish = new TwinColumnUI("name");
        tuiDish.setLeftLabelCaption(BundleUtils.getLanguage("lbl.dish.avaiable.list"));
        tuiDish.setRightLabelCaption(BundleUtils.getLanguage("lbl.dish.selected.list"));
        tabSheet.addTab(tuiDish, BundleUtils.getLanguage("lbl.restaurant.restaurantDish.tab"));

        // RestaurantArticle
        tuiArticle = new TwinColumnUI("title");
        tuiArticle.setLeftLabelCaption(BundleUtils.getLanguage("lbl.article.avaiable.list"));
        tuiArticle.setRightLabelCaption(BundleUtils.getLanguage("lbl.article.selected.list"));
        tabSheet.addTab(tuiArticle, BundleUtils.getLanguage("lbl.restaurant.RestaurantArticle.tab"));

        setContent(tabSheet);
    }

    private void setValueDish() {

        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {

                if (!isLoadedArticle && event.getTabSheet().getSelectedTab().equals(tuiArticle)) {
                    isLoadedArticle = true;
                    setValueArticle();
                    setActionArticle();
                    tabSheet.removeSelectedTabChangeListener(this);
                }
            }
        });

        lsDishLeft = tuiDish.getMsLeft().getLsItem();
        lsDishRight = tuiDish.getMsRight().getLsItem();

        user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());

        DishDTO dishDTO = new DishDTO();
        dishDTO.setNotRestaurantId(dto.getId());
        dishDTO.setIsGetOnlyIdentified("1");
        List<DishDTO> dishLeftList = ClientServiceImpl.getInstance().getListDishDTOLess(user.getUsername()//
                , getLocale().getLanguage(), getLocale().getCountry(), null, dishDTO, 0, 0, false, "ASC", "name");

        dishDTO.setNotRestaurantId(null);
        dishDTO.setRestaurantId(dto.getId());
        List<DishDTO> dishRightList = ClientServiceImpl.getInstance().getListDishDTOLess(user.getUsername()//
                , getLocale().getLanguage(), getLocale().getCountry(), null, dishDTO, 0, 0, false, "ASC", "name");

        if (dishLeftList != null) {
            dishLeftContainer.addAll(dishLeftList);
        }
        if (dishRightList != null) {
            dishRightContainer.addAll(dishRightList);
        }

        lsDishLeft.setContainerDataSource(dishLeftContainer);
        lsDishRight.setContainerDataSource(dishRightContainer);
    }

    private void setValueArticle() {

        lsArticleLeft = tuiArticle.getMsLeft().getLsItem();
        lsArticleRight = tuiArticle.getMsRight().getLsItem();

        user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());

        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setNotRestaurantId(dto.getId());
        articleDTO.setIsGetOnlyIdentified("1");
        List<ArticleDTO> articleLeftList = ClientServiceImpl.getInstance().getListArticleDTOLess(user.getUsername()//
                , getLocale().getLanguage(), getLocale().getCountry(), null, articleDTO, 0, 0, false, "ASC", "name");

        articleDTO.setNotRestaurantId(null);
        articleDTO.setRestaurantId(dto.getId());
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

    private void setActionDish() {
        tuiDish.getBtnSave().addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                dishRightContainer.removeAllContainerFilters();
                List<DishDTO> list = dishRightContainer.getItemIds();
                List<String> listId = new ArrayList<>();
                if (list != null) {
                    list.stream().forEach((dto) -> {
                        listId.add(dto.getId());
                    });
                }

                ResultDTO result = ClientServiceImpl.getInstance().insertListDishToRestaurant(user.getUsername()//
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

                ResultDTO result = ClientServiceImpl.getInstance().insertListArticleToRestaurant(user.getUsername()//
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
