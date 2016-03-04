/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.view;

import com.dungnv.common.ui.OptionGroupUI;
import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.dto.LocaleDTO;
import com.dungnv.streetfood.dto.ResultDTO;
import com.dungnv.streetfood.service.ClientServiceImpl;
import com.dungnv.streetfood.ui.TagSuggestFieldUI;
import com.dungnv.utils.BundleUtils;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.vaadin.csvalidation.CSValidator;

/**
 *
 * @author ODIN NGUYEN
 */
public class DishSearchDetail extends Window {

    VerticalLayout layout;
    DishView view;
    FormLayout form;
    TextField tfName;
    TextField tfShortDescription;
    CheckBox cbActive;
    CheckBox cbInActive;
    TagSuggestFieldUI tagSuggestFieldUI;

    TextField tfViewCountFrom;
    TextField tfViewCountTo;
    TextField tfCommentCountFrom;
    TextField tfCommentCountTo;
    TextField tfShareCountFrom;
    TextField tfShareCountTo;
    TextField tfRatingFrom;
    TextField tfRatingTo;
    
    Button btnSearch;
    Button btnExportExcel;
    Button btnExportXML;
    Button btnCancel;
    List<OptionGroupUI> listOgLocale;

    public DishSearchDetail(DishView view) {
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

        tfName = new TextField(BundleUtils.getLanguage("lbl.dish.name"));
        tfName.setWidth(80.0f, Unit.PERCENTAGE);
        form.addComponent(tfName);

        tfShortDescription = new TextField(BundleUtils.getLanguage("lbl.dish.shortDescription"));
        tfShortDescription.setWidth(80.0f, Unit.PERCENTAGE);
        form.addComponent(tfShortDescription);

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

        String regexDouble = "[0-9]*.?[0-9]?";
        String regexInteger = "[0-9]*";

        tfViewCountFrom = new TextField();
        tfViewCountFrom.setWidth(200.0f, Unit.PIXELS);
        tfViewCountFrom.addStyleName(ValoTheme.TEXTFIELD_TINY);
        tfViewCountFrom.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);

        CSValidator vlViewCountFrom = new CSValidator();
        vlViewCountFrom.extend(tfViewCountFrom);
        vlViewCountFrom.setRegExp(regexInteger);
        vlViewCountFrom.setPreventInvalidTyping(true);
        tfViewCountFrom.addValidator(new RegexpValidator(regexInteger, "Not a number"));

        tfViewCountTo = new TextField();
        tfViewCountTo.setWidth(200.0f, Unit.PIXELS);
        tfViewCountTo.addStyleName(ValoTheme.TEXTFIELD_TINY);
        tfViewCountTo.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
        CSValidator vlViewCountTo = new CSValidator();
        vlViewCountTo.extend(tfViewCountTo);
        vlViewCountTo.setRegExp(regexInteger);
        vlViewCountTo.setPreventInvalidTyping(true);
        tfViewCountTo.addValidator(new RegexpValidator(regexInteger, "Not a number"));

        HorizontalLayout hlViewCount = new HorizontalLayout(tfViewCountFrom, new Label("-"), tfViewCountTo);
        hlViewCount.setCaption(BundleUtils.getLanguage("lbl.dish.viewCount"));
        hlViewCount.setSpacing(true);
        form.addComponent(hlViewCount);

        tfCommentCountFrom = new TextField();
        tfCommentCountFrom.setWidth(200.0f, Unit.PIXELS);
        tfCommentCountFrom.addStyleName(ValoTheme.TEXTFIELD_TINY);
        tfCommentCountFrom.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);

        CSValidator vlCommentCountFrom = new CSValidator();
        vlCommentCountFrom.extend(tfCommentCountFrom);
        vlCommentCountFrom.setRegExp(regexInteger);
        vlCommentCountFrom.setPreventInvalidTyping(true);
        tfCommentCountFrom.addValidator(new RegexpValidator(regexInteger, "Not a number"));

        tfCommentCountTo = new TextField();
        tfCommentCountTo.setWidth(200.0f, Unit.PIXELS);
        tfCommentCountTo.addStyleName(ValoTheme.TEXTFIELD_TINY);
        tfCommentCountTo.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
        CSValidator vlCommentCountTo = new CSValidator();
        vlCommentCountTo.extend(tfCommentCountTo);
        vlCommentCountTo.setRegExp(regexInteger);
        vlCommentCountTo.setPreventInvalidTyping(true);
        tfCommentCountTo.addValidator(new RegexpValidator(regexInteger, "Not a number"));

        HorizontalLayout hlCommentCount = new HorizontalLayout(tfCommentCountFrom, new Label("-"), tfCommentCountTo);
        hlCommentCount.setCaption(BundleUtils.getLanguage("lbl.dish.commentCount"));
        hlCommentCount.setSpacing(true);
        form.addComponent(hlCommentCount);

        tfShareCountFrom = new TextField();
        tfShareCountFrom.setWidth(200.0f, Unit.PIXELS);
        tfShareCountFrom.addStyleName(ValoTheme.TEXTFIELD_TINY);
        tfShareCountFrom.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);

        CSValidator vlShareCountFrom = new CSValidator();
        vlShareCountFrom.extend(tfShareCountFrom);
        vlShareCountFrom.setRegExp(regexInteger);
        vlShareCountFrom.setPreventInvalidTyping(true);
        tfShareCountFrom.addValidator(new RegexpValidator(regexInteger, "Not a number"));

        tfShareCountTo = new TextField();
        tfShareCountTo.setWidth(200.0f, Unit.PIXELS);
        tfShareCountTo.addStyleName(ValoTheme.TEXTFIELD_TINY);
        tfShareCountTo.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
        CSValidator vlShareCountTo = new CSValidator();
        vlShareCountTo.extend(tfShareCountTo);
        vlShareCountTo.setRegExp(regexInteger);
        vlShareCountTo.setPreventInvalidTyping(true);
        tfShareCountTo.addValidator(new RegexpValidator(regexInteger, "Not a number"));

        HorizontalLayout hlShareCount = new HorizontalLayout(tfShareCountFrom, new Label("-"), tfShareCountTo);
        hlShareCount.setCaption(BundleUtils.getLanguage("lbl.dish.shareCount"));
        hlShareCount.setSpacing(true);
        form.addComponent(hlShareCount);

        tfRatingFrom = new TextField();
        tfRatingFrom.setWidth(200.0f, Unit.PIXELS);
        tfRatingFrom.addStyleName(ValoTheme.TEXTFIELD_TINY);
        tfRatingFrom.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);

        CSValidator vlRatingFrom = new CSValidator();
        vlRatingFrom.extend(tfRatingFrom);
        vlRatingFrom.setRegExp(regexDouble);
        vlRatingFrom.setPreventInvalidTyping(true);
        tfRatingFrom.addValidator(new RegexpValidator(regexDouble, "Not a number"));

        tfRatingTo = new TextField();
        tfRatingTo.setWidth(200.0f, Unit.PIXELS);
        tfRatingTo.addStyleName(ValoTheme.TEXTFIELD_TINY);
        tfRatingTo.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
        CSValidator vlRatingTo = new CSValidator();
        vlRatingTo.extend(tfRatingTo);
        vlRatingTo.setRegExp(regexDouble);
        vlRatingTo.setPreventInvalidTyping(true);
        tfRatingTo.addValidator(new RegexpValidator(regexDouble, "Not a number"));

        HorizontalLayout hlRating = new HorizontalLayout(tfRatingFrom, new Label("-"), tfRatingTo);
        hlRating.setCaption(BundleUtils.getLanguage("lbl.dish.rating"));
        hlRating.setSpacing(true);
        form.addComponent(hlRating);

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

        btnSearch.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                DishDTO dto = new DishDTO();
                dto.setName(tfName.getValue());
                dto.setShortDescription(tfShortDescription.getValue());
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
                    list.stream().forEach((tag) -> {
                        listTag.add(tag.getId());
                    });
                }
                dto.setListTag(listTag);

                List<String> listLocale = new ArrayList<>();
                List<String> listNotLocale = new ArrayList<>();
                listOgLocale.stream().filter((og) -> (og.getValue() != null)).forEach((og) -> {
                    if (og.getValue().equals(BundleUtils.getLanguage("lbl.yes"))) {
                        listLocale.add((String) og.getData());
                    } else if (og.getValue().equals(BundleUtils.getLanguage("lbl.no"))) {
                        listNotLocale.add((String) og.getData());
                    }
                });

                dto.setListLocale(listLocale);
                dto.setListNotLocale(listNotLocale);

                dto.setViewCountFrom(tfViewCountFrom.getValue());
                dto.setViewCountTo(tfViewCountTo.getValue());
                dto.setCommentCountFrom(tfCommentCountFrom.getValue());
                dto.setCommentCountTo(tfCommentCountTo.getValue());
                dto.setShareCountFrom(tfShareCountFrom.getValue());
                dto.setShareCountTo(tfShareCountTo.getValue());
                dto.setRatingFrom(tfRatingFrom.getValue());
                dto.setRatingTo(tfRatingTo.getValue());

                view.setDtoSearch(dto);
                view.onSearch(Boolean.TRUE);
                UI.getCurrent().removeWindow(event.getButton().findAncestor(Window.class));
            }
        });
    }

}
