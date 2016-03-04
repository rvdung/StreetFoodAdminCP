/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.ui;

import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.dto.ResultDTO;
import com.dungnv.streetfood.dto.UserDTO;
import com.dungnv.streetfood.service.ClientServiceImpl;
import com.dungnv.streetfood.view.CategoryInsert;
import com.dungnv.streetfood.view.CategoryLink;
import com.dungnv.streetfood.view.CategoryView;
import com.dungnv.utils.BundleUtils;
import com.dungnv.utils.Constants;
import com.dungnv.utils.FWUtils;
import com.dungnv.utils.StringUtils;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.dialogs.ConfirmDialog;

/**
 *
 * @author ODIN NGUYEN
 */
public class CategoryItemUI extends VerticalLayout {

    private String itemId;
    private CategoryDTO item;
    private Button btnLock;
    private Button btnEdit;
    private Button btnLink;
    private Button btnDelete;
    CategoryView mainView;

    public CategoryItemUI(CategoryDTO item, CategoryView mainView) {
        setLocale(VaadinSession.getCurrent().getLocale());
        this.mainView = mainView;
        this.item = item == null ? new CategoryDTO() : item;
        this.itemId = this.item.getId();
        init();
        buildAction();
    }

    private void init() {
        this.addStyleName("item-interator");
        this.setSpacing(true);
        this.setWidth("100%");

        CssLayout horizontal = new CssLayout();
        horizontal.setStyleName("padding-5");
        horizontal.setWidth("100%");
        Responsive.makeResponsive(horizontal);
        this.addComponent(horizontal);

        Image imag = new Image();
        if (!StringUtils.isNullOrEmpty(item.getImageUrl())) {
            imag.setSource(new ExternalResource(item.getImageUrl()));
        }
        horizontal.addComponent(imag);

        VerticalLayout vlInfo = new VerticalLayout();
        vlInfo.setStyleName("padding-5");
        vlInfo.setWidth("70%");

        horizontal.addComponent(vlInfo);

        Label lbTitle = new Label(item.getName());
        lbTitle.addStyleName("lb-title");
        if ("1".equals(item.getCategoryStatus())) {
            lbTitle.addStyleName("lb-status-active");
        } else {
            lbTitle.addStyleName("lb-status-inActive");
        }
        vlInfo.addComponent(lbTitle);

        Label lbDesc = new Label(item.getDescription());
        lbDesc.setStyleName("lb-description");
        vlInfo.addComponent(lbDesc);

        HorizontalLayout htToolBar = new HorizontalLayout();
        htToolBar.setStyleName("lb-toolbar");
        horizontal.addComponent(htToolBar);

        btnLock = new Button();
        if ("1".equals(item.getCategoryStatus())) {
            btnLock.setIcon(FontAwesome.LOCK);
        } else {
            btnLock.setIcon(FontAwesome.UNLOCK);
        }

        btnLock.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        btnLock.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        btnLock.setWidth("25px");
        btnLock.setHeight("25px");
        htToolBar.addComponent(btnLock);
        htToolBar.setComponentAlignment(btnLock, Alignment.BOTTOM_RIGHT);

        btnLink = new Button();
        btnLink.setIcon(FontAwesome.LINK);
        btnLink.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        btnLink.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        btnLink.setWidth("25px");
        btnLink.setHeight("25px");
        htToolBar.addComponent(btnLink);
        htToolBar.setComponentAlignment(btnLink, Alignment.BOTTOM_RIGHT);

        btnEdit = new Button();
        btnEdit.setIcon(FontAwesome.EDIT);
        btnEdit.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        btnEdit.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        btnEdit.setWidth("25px");
        btnEdit.setHeight("25px");
        htToolBar.addComponent(btnEdit);
        htToolBar.setComponentAlignment(btnEdit, Alignment.BOTTOM_RIGHT);

        btnDelete = new Button();
        btnDelete.setIcon(FontAwesome.TIMES);
        btnDelete.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        btnDelete.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        btnDelete.setWidth("25px");
        btnDelete.setHeight("25px");
        htToolBar.addComponent(btnDelete);
        htToolBar.setComponentAlignment(btnDelete, Alignment.BOTTOM_RIGHT);
    }

    private void buildAction() {

        btnEdit.addClickListener((Button.ClickEvent event) -> {
            if (item != null) {
                CategoryInsert CategoryInsert = new CategoryInsert(item//
                        , event.getButton().findAncestor(CategoryView.class)//
                        , Constants.ACTION.UPDATE);
                CategoryInsert.setWidth("80%");
                CategoryInsert.setHeight("90%");
                CategoryInsert.setModal(true);
                FWUtils.reloadWindow(CategoryInsert);
                UI.getCurrent().addWindow(CategoryInsert);
            }
        });
        
        btnLink.addClickListener((Button.ClickEvent event) -> {
            if (item != null) {
                CategoryLink CategoryInsert = new CategoryLink(item);
                CategoryInsert.setWidth("80%");
                CategoryInsert.setHeight("75%");
                CategoryInsert.setModal(true);
                FWUtils.reloadWindow(CategoryInsert);
                UI.getCurrent().addWindow(CategoryInsert);
            }
        });

        btnDelete.addClickListener((Button.ClickEvent event) -> {
            ConfirmDialog.show(UI.getCurrent(), BundleUtils.getLanguage("lbl.confirm")//
                    , BundleUtils.getLanguage("message.category.delete.confirm")//
                    , BundleUtils.getLanguage("lbl.yes")//
                    , BundleUtils.getLanguage("lbl.no")//
                    , (ConfirmDialog cd) -> {
                        if (cd.isConfirmed()) {
                            UserDTO user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());
                            ResultDTO result = ClientServiceImpl.getInstance().deleteCategory(user.getUsername()//
                                    , getLocale().getLanguage(), getLocale().getCountry(), null, itemId);
                            if (result != null && Constants.SUCCESS.equals(result.getMessage())) {
                                mainView.onSearch(Boolean.TRUE);
                                UI.getCurrent().removeWindow(event.getButton().findAncestor(Window.class));
                            } else {
                                Notification.show(result == null || result.getKey() == null ? Constants.FAIL
                            : result.getKey(), Notification.Type.ERROR_MESSAGE);
                            }
                        }
                    });
        });

    }

    public CategoryDTO getItem() {
        return item;
    }

    public String getItemId() {
        return itemId;
    }
}
