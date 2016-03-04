/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.ui;

import com.dungnv.streetfood.dto.SlideShowDTO;
import com.dungnv.streetfood.dto.ResultDTO;
import com.dungnv.streetfood.dto.UserDTO;
import com.dungnv.streetfood.service.ClientServiceImpl;
import com.dungnv.streetfood.view.SlideShowInsert;
import com.dungnv.streetfood.view.SlideShowView;
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
public class SlideShowItemUI extends VerticalLayout {

    private final String itemId;
    private final SlideShowDTO item;
    private Button btnEdit;
    private Button btnDelete;
    SlideShowView mainView;
    Label lbTitle;
    Label lbInfo;
    Label lbIntroduce;

    public SlideShowItemUI(SlideShowDTO item, SlideShowView mainView) {
        setLocale(VaadinSession.getCurrent().getLocale());
        this.mainView = mainView;
        this.item = item == null ? new SlideShowDTO() : item;
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

        lbIntroduce = new Label(item.getDescription());
        lbIntroduce.setStyleName("lb-description");
        vlInfo.addComponent(lbIntroduce);

        HorizontalLayout htInfo = new HorizontalLayout();
        vlInfo.addComponent(htInfo);

        lbInfo = new Label();
        lbInfo.setCaptionAsHtml(true);
        htInfo.addComponent(lbInfo);

        HorizontalLayout htToolBar = new HorizontalLayout();
        htToolBar.setStyleName("lb-toolbar");
        horizontal.addComponent(htToolBar);

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

        lbTitle.addStyleName("lb-status-active");

        String info = "";

        if (!StringUtils.isNullOrEmpty(item.getId())) {
            info += StringUtils.isNullOrEmpty(info)
                    ? FontAwesome.OUTDENT.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + item.getId()
                    : "&nbsp|" + FontAwesome.OUTDENT.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + item.getId();
        }
        
        if (!StringUtils.isNullOrEmpty(item.getValidFromGmt())) {
            info += StringUtils.isNullOrEmpty(info)
                    ? FontAwesome.OUTDENT.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + item.getValidFromGmt()
                    : "&nbsp|" + FontAwesome.OUTDENT.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + item.getValidFromGmt();
        }
        
        if (!StringUtils.isNullOrEmpty(item.getValidToGmt())) {
            info += StringUtils.isNullOrEmpty(info)
                    ? FontAwesome.OUTDENT.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + item.getValidToGmt()
                    : "&nbsp|" + FontAwesome.OUTDENT.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + item.getValidToGmt();
        }
        
        if (!StringUtils.isNullOrEmpty(info)) {
            lbInfo.setCaption(info);
        }

        btnEdit.addClickListener((Button.ClickEvent event) -> {
            if (item != null) {
                SlideShowInsert slideShowInsert = new SlideShowInsert(item//
                        , event.getButton().findAncestor(SlideShowView.class)//
                        , Constants.ACTION.UPDATE);
                slideShowInsert.setWidth("80%");
                slideShowInsert.setHeight("90%");
                slideShowInsert.setModal(true);
                FWUtils.reloadWindow(slideShowInsert);
                UI.getCurrent().addWindow(slideShowInsert);
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
                            ResultDTO result = ClientServiceImpl.getInstance().deleteSlideShow(user.getUsername()//
                                    , getLocale().getLanguage(), getLocale().getCountry(), null, Long.valueOf(itemId));
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

    public SlideShowDTO getItem() {
        return item;
    }

    public String getItemId() {
        return itemId;
    }
}
