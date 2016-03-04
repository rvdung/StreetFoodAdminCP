/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.view;

import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.dto.ResultDTO;
import com.dungnv.streetfood.ui.TagSuggestFieldUI;
import com.dungnv.utils.BundleUtils;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ODIN NGUYEN
 */
public class ArticleSearchDetail extends Window {

    VerticalLayout layout;
    ArticleView view;
    FormLayout form;
    TextField tfTitle;
    TextField tfShortContent;
    TagSuggestFieldUI tagSuggestFieldUI;

    Button btnSearch;
    Button btnCancel;

    public ArticleSearchDetail(ArticleView view) {
        this.view = view;
        init();
        setContent(layout);
        builAction();
    }

    private void init() {
        layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        form = new FormLayout();
        form.addStyleName("light");
//                form.addStyleName("outlined");
        form.setSizeFull();
        form.setMargin(true);
        form.setSpacing(true);
        layout.addComponent(form);

        tfTitle = new TextField(BundleUtils.getLanguage("lbl.article.title"));
        tfTitle.setWidth(80.0f, Unit.PERCENTAGE);
        form.addComponent(tfTitle);

        tfShortContent = new TextField(BundleUtils.getLanguage("lbl.article.shortContent"));
        tfShortContent.setWidth(80.0f, Unit.PERCENTAGE);
        form.addComponent(tfShortContent);

        HorizontalLayout hlStatus = new HorizontalLayout();
        hlStatus.setCaption(BundleUtils.getLanguage("lbl.status"));
        hlStatus.addStyleName("horizontal");
        hlStatus.setSpacing(true);
        form.addComponent(hlStatus);

        tagSuggestFieldUI = new TagSuggestFieldUI(false);
        tagSuggestFieldUI.setWidth(80.0f, Unit.PERCENTAGE);
        form.addComponent(tagSuggestFieldUI);

        HorizontalLayout hlButton = new HorizontalLayout();
        hlButton.setSpacing(true);
        hlButton.setMargin(true);
        form.addComponent(hlButton);

        btnSearch = new Button(BundleUtils.getLanguage("lbl.search"), FontAwesome.SEARCH);
        hlButton.addComponent(btnSearch);

        btnCancel = new Button(BundleUtils.getLanguage("lbl.cancel"), FontAwesome.BAN);
        hlButton.addComponent(btnCancel);

    }

    private void builAction() {
        btnCancel.addClickListener((Button.ClickEvent event) -> {
            UI.getCurrent().removeWindow(event.getButton().findAncestor(Window.class));
        });

        btnSearch.addClickListener((Button.ClickEvent event) -> {
            ArticleDTO dto = new ArticleDTO();
            dto.setTitle(tfTitle.getValue());
            dto.setShortContent(tfShortContent.getValue());
            
            List<ResultDTO> list = tagSuggestFieldUI.getValue();
            List<String> listTag = new ArrayList<>();
            if (list != null && !list.isEmpty()) {
                for (ResultDTO tag : list) {
                    listTag.add(tag.getId());
                }
            }
            dto.setListTag(listTag);
            view.setDtoSearch(dto);
            view.onSearch(Boolean.TRUE);
            UI.getCurrent().removeWindow(event.getButton().findAncestor(Window.class));
        });
    }

}
