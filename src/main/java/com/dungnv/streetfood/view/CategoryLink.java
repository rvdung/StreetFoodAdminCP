/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.view;

import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.dto.ResultDTO;
import com.dungnv.streetfood.dto.UserDTO;
import com.dungnv.streetfood.service.ClientServiceImpl;
import com.dungnv.streetfood.ui.TwinColumnUI;
import com.dungnv.utils.BundleUtils;
import com.dungnv.utils.Constants;
import com.kbdunn.vaadin.addons.fontawesome.FontAwesome;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
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
public class CategoryLink extends Window {

    private final CategoryDTO dto;

    TabSheet tabSheet;
    TwinColumnUI tuiDish;
    Button btnCancel;
    UserDTO user;

    BeanItemContainer<DishDTO> dishLeftContainer;
    BeanItemContainer<DishDTO> dishRightContainer;
    ListSelect lsDishLeft;
    ListSelect lsDishRight;

    public CategoryLink(CategoryDTO dto) {
        this.dishLeftContainer = new BeanItemContainer<>(DishDTO.class);
        this.dishRightContainer = new BeanItemContainer<>(DishDTO.class);
        this.dto = dto;
        init();
        setValue();
        setAction();
    }

    private void init() {

        setLocale(VaadinSession.getCurrent().getLocale());
        tabSheet = new TabSheet();
        tabSheet.setStyleName(ValoTheme.TABSHEET_EQUAL_WIDTH_TABS);
        tabSheet.setSizeFull();

        VerticalLayout vLayout = new VerticalLayout();

        tuiDish = new TwinColumnUI("name");
        tuiDish.setLeftLabelCaption(BundleUtils.getLanguage("lbl.dish.avaiable.list"));
        tuiDish.setRightLabelCaption(BundleUtils.getLanguage("lbl.dish.selected.list"));
        vLayout.addComponent(tuiDish);

        lsDishLeft = tuiDish.getMsLeft().getLsItem();
        lsDishRight = tuiDish.getMsRight().getLsItem();
        HorizontalLayout hlButtonFooter = new HorizontalLayout();
        hlButtonFooter.setSpacing(true);
        hlButtonFooter.setMargin(true);
        vLayout.addComponent(hlButtonFooter);
        vLayout.setComponentAlignment(hlButtonFooter, Alignment.BOTTOM_RIGHT);

        btnCancel = new Button(BundleUtils.getLanguage("lbl.cancel"), FontAwesome.BAN);
        hlButtonFooter.addComponent(btnCancel);

        tabSheet.addTab(vLayout, BundleUtils.getLanguage("lbl.category.categoryDish.tab"));
        setContent(tabSheet);
    }

    private void setValue() {

        user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());

        DishDTO dishDTO = new DishDTO();
        dishDTO.setNotCategoryId(dto.getId());
        dishDTO.setIsGetOnlyIdentified("1");
        List<DishDTO> dishLeftList = ClientServiceImpl.getInstance().getListDishDTOLess(user.getUsername()//
                , getLocale().getLanguage(), getLocale().getCountry(), null, dishDTO, 0, 0, false, "ASC", "name");

        dishDTO.setNotCategoryId(null);
        dishDTO.setCategoryId(dto.getId());
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

    private void setAction() {
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
                ResultDTO result = ClientServiceImpl.getInstance().insertListDishToCategory(user.getUsername()//
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
