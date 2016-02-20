/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.view;

import com.dungnv.streetfood.dto.DishDTO;
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
public class DishSearchDetail extends Window {

    VerticalLayout layout;
    DishView view;
    FormLayout form;
    TextField tfName;
    TextField tfDescription;
    CheckBox cbActive;
    CheckBox cbInActive;
    TagSuggestFieldUI tagSuggestFieldUI;

    Button btnSearch;
    Button btnCancel;

    public DishSearchDetail(DishView view) {
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

        tfName = new TextField(BundleUtils.getLanguage("lbl.category.name"));
        tfName.setWidth(80.0f, Unit.PERCENTAGE);
        form.addComponent(tfName);

        tfDescription = new TextField(BundleUtils.getLanguage("lbl.description"));
        tfDescription.setWidth(80.0f, Unit.PERCENTAGE);
        form.addComponent(tfDescription);

        HorizontalLayout hlStatus = new HorizontalLayout();
        hlStatus.setCaption(BundleUtils.getLanguage("lbl.status"));
        hlStatus.addStyleName("horizontal");
        hlStatus.setSpacing(true);
        form.addComponent(hlStatus);

        cbActive = new CheckBox(BundleUtils.getLanguage("lbl.active"));
        cbActive.setValue(Boolean.TRUE);
        hlStatus.addComponent(cbActive);

        cbInActive = new CheckBox(BundleUtils.getLanguage("lbl.inActive"));
        cbInActive.setValue(Boolean.TRUE);
        hlStatus.addComponent(cbInActive);

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
            DishDTO dto = new DishDTO();
            dto.setName(tfName.getValue());
            dto.setShortDescription(tfDescription.getValue());
            if (cbActive.getValue() && cbInActive.getValue()) {
                dto.setDishStatus(null);
            } else if (cbActive.getValue()) {
                dto.setDishStatus("1");
            } else if (cbInActive.getValue()) {
                dto.setDishStatus("0");
            } else {
                dto.setDishStatus("-1");
            }

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
