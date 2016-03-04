/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.ui;

import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.dto.ResultDTO;
import com.dungnv.streetfood.dto.UserDTO;
import com.dungnv.streetfood.service.ClientServiceImpl;
import com.dungnv.streetfood.view.ArticleInsert;
import com.dungnv.streetfood.view.ArticleLink;
import com.dungnv.streetfood.view.ArticleView;
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
public class ArticleItemUI extends VerticalLayout {

    private final String itemId;
    private final ArticleDTO item;
    private Button btnEdit;
    private Button btnLink;
    private Button btnDelete;
    ArticleView mainView;
    Label lbTitle;
    Label lbInfo;
    Label lbIntroduce;

    public ArticleItemUI(ArticleDTO item, ArticleView mainView) {
        setLocale(VaadinSession.getCurrent().getLocale());
        this.mainView = mainView;
        this.item = item == null ? new ArticleDTO() : item;
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

        lbTitle = new Label(item.getTitle());
        lbTitle.addStyleName("lb-title");
        vlInfo.addComponent(lbTitle);

        lbIntroduce = new Label(item.getShortContent());
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

        String info = "";
        if (!StringUtils.isNullOrEmpty(item.getId())) {
            info += "<b>" + com.kbdunn.vaadin.addons.fontawesome.FontAwesome.BARCODE.getHtml() + " " + item.getId() + "</b>";
        }

        if (!StringUtils.isNullOrEmpty(item.getViewCount())) {
            info += "&nbsp&nbsp|&nbsp&nbsp" + com.kbdunn.vaadin.addons.fontawesome.FontAwesome.EYE.getHtml() //
                    + "&nbsp&nbsp:&nbsp&nbsp" + item.getViewCount();
        }

        if (!StringUtils.isNullOrEmpty(info)) {
            lbInfo.setCaption(info);
        }
        
        btnLink.addClickListener((Button.ClickEvent event) -> {
            if (item != null) {
                ArticleLink dishLink = new ArticleLink(item);
                dishLink.setWidth("80%");
                dishLink.setHeight("75%");
                dishLink.setModal(true);
                FWUtils.reloadWindow(dishLink);
                UI.getCurrent().addWindow(dishLink);
            }
        });

        btnEdit.addClickListener((Button.ClickEvent event) -> {
            if (item != null) {
                ArticleInsert articleInsert = new ArticleInsert(item//
                        , event.getButton().findAncestor(ArticleView.class)//
                        , Constants.ACTION.UPDATE);
                articleInsert.setWidth("80%");
                articleInsert.setHeight("90%");
                articleInsert.setModal(true);
                FWUtils.reloadWindow(articleInsert);
                UI.getCurrent().addWindow(articleInsert);
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
                            ResultDTO result = ClientServiceImpl.getInstance().deleteArticle(user.getUsername()//
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

    public ArticleDTO getItem() {
        return item;
    }

    public String getItemId() {
        return itemId;
    }
}
