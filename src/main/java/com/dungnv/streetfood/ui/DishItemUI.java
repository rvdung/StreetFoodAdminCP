/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.ui;

import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.dto.ResultDTO;
import com.dungnv.streetfood.dto.UserDTO;
import com.dungnv.streetfood.service.ClientServiceImpl;
import com.dungnv.streetfood.view.DishInsert;
import com.dungnv.streetfood.view.DishView;
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
public class DishItemUI extends VerticalLayout {

    private String itemId;
    private DishDTO item;
    private Button btnLock;
    private Button btnEdit;
    private Button btnDelete;
    DishView mainView;
    Label lbTitle;
    Label lbInfo;
    Label lbDesc;

    public DishItemUI(DishDTO item, DishView mainView) {
        setLocale(VaadinSession.getCurrent().getLocale());
        this.mainView = mainView;
        this.item = item == null ? new DishDTO() : item;
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

        lbTitle = new Label(item.getName());
        lbTitle.addStyleName("lb-title");
        vlInfo.addComponent(lbTitle);

        lbDesc = new Label(item.getShortDescription());
        lbDesc.setStyleName("lb-description");
        vlInfo.addComponent(lbDesc);

        HorizontalLayout htInfo = new HorizontalLayout();
        vlInfo.addComponent(htInfo);

        lbInfo = new Label();
        lbInfo.setCaptionAsHtml(true);
        htInfo.addComponent(lbInfo);

        HorizontalLayout htToolBar = new HorizontalLayout();
        htToolBar.setStyleName("lb-toolbar");
        horizontal.addComponent(htToolBar);

        btnLock = new Button();
        if ("1".equals(item.getDishStatus())) {
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

        if ("1".equals(item.getDishStatus())) {
            lbTitle.addStyleName("lb-status-active");
        } else {
            lbTitle.addStyleName("lb-status-inActive");
        }

        String info = "";

        if (!StringUtils.isNullOrEmpty(item.getRating())) {
            Integer rating = Integer.valueOf(item.getRating());
            for (int i = 0; i < 5; i++) {
                if (i < rating) {
                    info += "<span style=\"color:#197DE1; padding-right:5px;\">" + FontAwesome.STAR.getHtml() +"</span>";
                } else {
                    info += FontAwesome.STAR_O.getHtml();
                }
            }
        }

        if (!StringUtils.isNullOrEmpty(item.getViewCount())) {
            info += StringUtils.isNullOrEmpty(item.getViewCount())
                    ? FontAwesome.EYE.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + item.getViewCount()
                    : "&nbsp|" + FontAwesome.EYE.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + item.getViewCount();
        }
        if (!StringUtils.isNullOrEmpty(item.getCommentCount())) {
            info += StringUtils.isNullOrEmpty(item.getCommentCount())
                    ? FontAwesome.COMMENTS.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + item.getCommentCount()
                    : "&nbsp|" + FontAwesome.COMMENTS.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + item.getCommentCount();
        }
        if (!StringUtils.isNullOrEmpty(item.getShareCount())) {
            info += StringUtils.isNullOrEmpty(item.getShareCount())
                    ? FontAwesome.SHARE_ALT.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + item.getShareCount()
                    : "&nbsp|" + FontAwesome.SHARE_ALT.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + item.getShareCount();
        }

        if (!StringUtils.isNullOrEmpty(info)) {
            lbInfo.setCaption(info);
        }

        btnEdit.addClickListener((Button.ClickEvent event) -> {
            if (item != null) {
                DishInsert dishInsert = new DishInsert(item//
                        , event.getButton().findAncestor(DishView.class)//
                        , Constants.ACTION.UPDATE);
                dishInsert.setWidth("80%");
                dishInsert.setHeight("90%");
                dishInsert.setModal(true);
                FWUtils.reloadWindow(dishInsert);
                UI.getCurrent().addWindow(dishInsert);
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
                            ResultDTO result = ClientServiceImpl.getInstance().deleteDish(user.getUsername()//
                                    , getLocale().getLanguage(), getLocale().getCountry(), null, Long.valueOf(itemId));
                            if (result != null && Constants.SUCCESS.equals(result.getMessage())) {
                                mainView.onSearch(Boolean.TRUE);
                                UI.getCurrent().removeWindow(event.getButton().findAncestor(Window.class));
                            } else {
                                Notification.show(result.getMessage(), Notification.Type.ERROR_MESSAGE);
                            }
                        }
                    });
        });

    }

    public DishDTO getItem() {
        return item;
    }

    public String getItemId() {
        return itemId;
    }
}