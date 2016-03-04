/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.view;

import com.dungnv.common.ui.OptionGroupUI;
import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.dto.LocaleDTO;
import com.dungnv.streetfood.dto.ResultDTO;
import com.dungnv.streetfood.service.ClientServiceImpl;
import com.dungnv.streetfood.ui.TagSuggestFieldUI;
import com.dungnv.utils.BundleUtils;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ODIN NGUYEN
 */
public class CategorySearchDetail extends Window {

    VerticalLayout layout;
    CategoryView view;
    FormLayout form;
    TextField tfName;
    TextField tfDescription;
    CheckBox cbActive;
    CheckBox cbInActive;
    TagSuggestFieldUI tagSuggestFieldUI;

    Button btnSearch;
    Button btnExportExcel;
    Button btnExportXML;
    Button btnCancel;

    List<OptionGroupUI> listOgLocale;

    public CategorySearchDetail(CategoryView view) {
        listOgLocale = new ArrayList<>();
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

        Map<String, LocaleDTO> mapLocale = ClientServiceImpl.getAllLocales();
        if (mapLocale != null && !mapLocale.isEmpty()) {
            List<LocaleDTO> listLocale = new ArrayList<>(mapLocale.values());
            listLocale.stream().map((localeDTO) -> new OptionGroupUI(localeDTO.getLocale()//
                    , localeDTO.getId())).forEach((ogLocale) -> {
                form.addComponent(ogLocale);
                listOgLocale.add(ogLocale);
            });
        }

        HorizontalLayout hlButton = new HorizontalLayout();
        hlButton.setSpacing(true);
        hlButton.setMargin(true);
        form.addComponent(hlButton);

        btnSearch = new Button(BundleUtils.getLanguage("lbl.search"), FontAwesome.SEARCH);
        hlButton.addComponent(btnSearch);

        btnExportExcel = new Button(BundleUtils.getLanguage("lbl.exportExcel"), FontAwesome.FILE_EXCEL_O);
        hlButton.addComponent(btnExportExcel);

        btnExportXML = new Button(BundleUtils.getLanguage("lbl.exportXML"), FontAwesome.FILE_CODE_O);
        hlButton.addComponent(btnExportXML);

        btnCancel = new Button(BundleUtils.getLanguage("lbl.cancel"), FontAwesome.BAN);
        hlButton.addComponent(btnCancel);

    }

    private void builAction() {
        btnCancel.addClickListener((Button.ClickEvent event) -> {
            UI.getCurrent().removeWindow(event.getButton().findAncestor(Window.class));
        });

        btnSearch.addClickListener((Button.ClickEvent event) -> {
            CategoryDTO dto = new CategoryDTO();
            dto.setName(tfName.getValue());
            dto.setDescription(tfDescription.getValue());
            if (cbActive.getValue() && cbInActive.getValue()) {
                dto.setCategoryStatus(null);
            } else if (cbActive.getValue()) {
                dto.setCategoryStatus("1");
            } else if (cbInActive.getValue()) {
                dto.setCategoryStatus("0");
            } else {
                dto.setCategoryStatus("-1");
            }

            List<String> listLocale = new ArrayList<>();
            List<String> listNotLocale = new ArrayList<>();
            for (OptionGroupUI og : listOgLocale) {
                if (og.getValue() != null) {
                    if (og.getValue().equals(BundleUtils.getLanguage("lbl.yes"))) {
                        listLocale.add((String) og.getData());
                    } else if (og.getValue().equals(BundleUtils.getLanguage("lbl.no"))) {
                        listNotLocale.add((String) og.getData());
                    }
                }
            }
            dto.setListLocale(listLocale);
            dto.setListNotLocale(listNotLocale);

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
