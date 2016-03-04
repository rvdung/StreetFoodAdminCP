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
public class ArticleLink extends Window {

    private final ArticleDTO dto;

    TabSheet tabSheet;
    UserDTO user;

    TwinColumnUI tuiDish;
    BeanItemContainer<DishDTO> dishLeftContainer;
    BeanItemContainer<DishDTO> dishRightContainer;
    ListSelect lsDishLeft;
    ListSelect lsDishRight;

    TwinColumnUI tuiRestaurant;
    BeanItemContainer<RestaurantDTO> restaurantLeftContainer;
    BeanItemContainer<RestaurantDTO> restaurantRightContainer;
    ListSelect lsRestaurantLeft;
    ListSelect lsRestaurantRight;
    Boolean isLoadedRestaurant;

    public ArticleLink(ArticleDTO dto) {
        isLoadedRestaurant = false;
        this.dishLeftContainer = new BeanItemContainer<>(DishDTO.class);
        this.dishRightContainer = new BeanItemContainer<>(DishDTO.class);

        this.restaurantLeftContainer = new BeanItemContainer<>(RestaurantDTO.class);
        this.restaurantRightContainer = new BeanItemContainer<>(RestaurantDTO.class);

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

        // DishArticle
        tuiDish = new TwinColumnUI("name");
        tuiDish.setLeftLabelCaption(BundleUtils.getLanguage("lbl.dish.avaiable.list"));
        tuiDish.setRightLabelCaption(BundleUtils.getLanguage("lbl.dish.selected.list"));
        tabSheet.addTab(tuiDish, BundleUtils.getLanguage("lbl.article.dishArticle.tab"));

        // RestaurantArticle
        tuiRestaurant = new TwinColumnUI("name");
        tuiRestaurant.setLeftLabelCaption(BundleUtils.getLanguage("lbl.restaurant.avaiable.list"));
        tuiRestaurant.setRightLabelCaption(BundleUtils.getLanguage("lbl.restaurant.selected.list"));
        tabSheet.addTab(tuiRestaurant, BundleUtils.getLanguage("lbl.article.restaurantArticle.tab"));

        setContent(tabSheet);
    }

    private void setValueDish() {

        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {

                if (!isLoadedRestaurant && event.getTabSheet().getSelectedTab().equals(tuiRestaurant)) {
                    isLoadedRestaurant = true;
                    setValueRestaurant();
                    setActionRestaurant();
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

    private void setValueRestaurant() {

        lsRestaurantLeft = tuiRestaurant.getMsLeft().getLsItem();
        lsRestaurantRight = tuiRestaurant.getMsRight().getLsItem();

        user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());

        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setNotArticleId(dto.getId());
        restaurantDTO.setIsGetOnlyIdentified("1");
        List<RestaurantDTO> restaurantLeftList = ClientServiceImpl.getInstance().getListRestaurantDTOLess(user.getUsername()//
                , getLocale().getLanguage(), getLocale().getCountry(), null, restaurantDTO, 0, 0, false, "ASC", "name");

        restaurantDTO.setNotArticleId(null);
        restaurantDTO.setArticleId(dto.getId());
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

                ResultDTO result = ClientServiceImpl.getInstance().insertListRestaurantToArticle(user.getUsername()//
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
