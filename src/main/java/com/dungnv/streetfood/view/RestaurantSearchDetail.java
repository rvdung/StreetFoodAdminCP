/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.view;

import com.dungnv.common.ui.OptionGroupUI;
import com.dungnv.streetfood.dto.LocaleDTO;
import com.dungnv.streetfood.dto.RestaurantDTO;
import com.dungnv.streetfood.dto.ResultDTO;
import com.dungnv.streetfood.service.ClientServiceImpl;
import com.dungnv.streetfood.ui.TagSuggestFieldUI;
import com.dungnv.utils.BundleUtils;
import com.dungnv.utils.DateTimeUtils;
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
import org.vaadin.thomas.timefield.TimeField;

/**
 *
 * @author ODIN NGUYEN
 */
public class RestaurantSearchDetail extends Window {

    private VerticalLayout layout;
    private final RestaurantView view;
    private FormLayout form;
    private TextField tfName;
    private TextField tfIntroduce;
    private CheckBox cbActive;
    private CheckBox cbInActive;
    private TagSuggestFieldUI tagSuggestFieldUI;

    private TextField tfViewCountFrom;
    private TextField tfViewCountTo;
    private TextField tfCommentCountFrom;
    private TextField tfCommentCountTo;
    private TextField tfShareCountFrom;
    private TextField tfShareCountTo;
    private TextField tfRatingFrom;
    private TextField tfRatingTo;
    private TextField tfWaitingTimeFrom;
    private TextField tfWaitingTimeTo;

    TimeField tfOperatingTimeStart;
    TimeField tfOperatingTimeEnd;

    private TextField tfPriceFromVn;
    private TextField tfPriceToVn;
    private TextField tfPriceFromEn;
    private TextField tfPriceToEn;

    private TextField tfAddress;
    private TextField tfPhoneNumber;
    private TextField tfCapacity;

    private CheckBox cbCarParkingYes;
    private CheckBox cbCarParkingNo;
    private CheckBox cbMotobikeParkingYes;
    private CheckBox cbMotobikeParkingNo;

    Button btnSearch;
    Button btnExportExcel;
    Button btnExportXML;
    Button btnCancel;
    List<OptionGroupUI> listOgLocale;

    public RestaurantSearchDetail(RestaurantView view) {
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

        tfName = new TextField(BundleUtils.getLanguage("lbl.restaurant.name"));
        tfName.setWidth(80.0f, Unit.PERCENTAGE);
        form.addComponent(tfName);

        tfIntroduce = new TextField(BundleUtils.getLanguage("lbl.restaurant.introduce"));
        tfIntroduce.setWidth(80.0f, Unit.PERCENTAGE);
        form.addComponent(tfIntroduce);

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

        tfAddress = new TextField(BundleUtils.getLanguage("lbl.restaurant.address"));
        tfAddress.setWidth(80.0f, Unit.PERCENTAGE);
        form.addComponent(tfAddress);

        tfPhoneNumber = new TextField(BundleUtils.getLanguage("lbl.restaurant.phoneNumber"));
        tfPhoneNumber.setWidth(80.0f, Unit.PERCENTAGE);
        form.addComponent(tfPhoneNumber);

        tfCapacity = new TextField(BundleUtils.getLanguage("lbl.restaurant.capacity"));
        tfCapacity.setWidth(80.0f, Unit.PERCENTAGE);
        form.addComponent(tfCapacity);

        HorizontalLayout hlCarParking = new HorizontalLayout();
        hlCarParking.setCaption(BundleUtils.getLanguage("lbl.restaurant.carParking"));
        hlCarParking.addStyleName("horizontal");
        hlCarParking.setSpacing(true);
        form.addComponent(hlCarParking);

        cbCarParkingYes = new CheckBox(BundleUtils.getLanguage("lbl.yes"));
        cbCarParkingYes.setValue(Boolean.TRUE);
        hlCarParking.addComponent(cbCarParkingYes);

        cbCarParkingNo = new CheckBox(BundleUtils.getLanguage("lbl.no"));
        cbCarParkingNo.setValue(Boolean.TRUE);
        hlCarParking.addComponent(cbCarParkingNo);

        HorizontalLayout hlMotobikeParking = new HorizontalLayout();
        hlMotobikeParking.setCaption(BundleUtils.getLanguage("lbl.status"));
        hlMotobikeParking.addStyleName("horizontal");
        hlMotobikeParking.setSpacing(true);
        form.addComponent(hlMotobikeParking);

        cbMotobikeParkingYes = new CheckBox(BundleUtils.getLanguage("lbl.yes"));
        cbMotobikeParkingYes.setValue(Boolean.TRUE);
        hlMotobikeParking.addComponent(cbMotobikeParkingYes);

        cbMotobikeParkingNo = new CheckBox(BundleUtils.getLanguage("lbl.no"));
        cbMotobikeParkingNo.setValue(Boolean.TRUE);
        hlMotobikeParking.addComponent(cbMotobikeParkingNo);

        tfOperatingTimeStart = new TimeField();
        tfOperatingTimeEnd = new TimeField();

        HorizontalLayout hlOperatingTime = new HorizontalLayout(tfOperatingTimeStart, new Label(" - "), tfOperatingTimeEnd);
        hlOperatingTime.setCaption(BundleUtils.getLanguage("lbl.restaurant.operatingTime"));
        hlOperatingTime.setSpacing(true);
        form.addComponent(hlOperatingTime);

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
        hlViewCount.setCaption(BundleUtils.getLanguage("lbl.restaurant.viewCount"));
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
        hlCommentCount.setCaption(BundleUtils.getLanguage("lbl.restaurant.commentCount"));
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
        hlShareCount.setCaption(BundleUtils.getLanguage("lbl.restaurant.shareCount"));
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
        hlRating.setCaption(BundleUtils.getLanguage("lbl.restaurant.rating"));
        hlRating.setSpacing(true);
        form.addComponent(hlRating);

        tfPriceFromVn = new TextField();
        tfPriceFromVn.setWidth(200.0f, Unit.PIXELS);
        tfPriceFromVn.addStyleName(ValoTheme.TEXTFIELD_TINY);
        tfPriceFromVn.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);

        CSValidator vlPriceFromVn = new CSValidator();
        vlPriceFromVn.extend(tfPriceFromVn);
        vlPriceFromVn.setRegExp(regexDouble);
        vlPriceFromVn.setPreventInvalidTyping(true);
        tfPriceFromVn.addValidator(new RegexpValidator(regexDouble, "Not a number"));

        tfPriceToVn = new TextField();
        tfPriceToVn.setWidth(200.0f, Unit.PIXELS);
        tfPriceToVn.addStyleName(ValoTheme.TEXTFIELD_TINY);
        tfPriceToVn.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);

        CSValidator vlPriceToVn = new CSValidator();
        vlPriceToVn.extend(tfPriceToVn);
        vlPriceToVn.setRegExp(regexDouble);
        vlPriceToVn.setPreventInvalidTyping(true);
        tfPriceToVn.addValidator(new RegexpValidator(regexDouble, "Not a number"));

        HorizontalLayout hlPriceVn = new HorizontalLayout(tfPriceFromVn, new Label("-"), tfPriceToVn, new Label("VND"));
        hlPriceVn.setCaption(BundleUtils.getLanguage("lbl.restaurant.priceVn"));
        hlPriceVn.setSpacing(true);
        form.addComponent(hlPriceVn);

        tfPriceFromEn = new TextField();
        tfPriceFromEn.setWidth(200.0f, Unit.PIXELS);
        tfPriceFromEn.addStyleName(ValoTheme.TEXTFIELD_TINY);
        tfPriceFromEn.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);

        CSValidator vlPriceFromEn = new CSValidator();
        vlPriceFromEn.extend(tfPriceFromEn);
        vlPriceFromEn.setRegExp(regexDouble);
        vlPriceFromEn.setPreventInvalidTyping(true);
        tfPriceFromEn.addValidator(new RegexpValidator(regexDouble, "Not a number"));

        tfPriceToEn = new TextField();
        tfPriceToEn.setWidth(200.0f, Unit.PIXELS);
        tfPriceToEn.addStyleName(ValoTheme.TEXTFIELD_TINY);
        tfPriceToEn.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);

        CSValidator vlPriceToEn = new CSValidator();
        vlPriceToEn.extend(tfPriceToEn);
        vlPriceToEn.setRegExp(regexDouble);
        vlPriceToEn.setPreventInvalidTyping(true);
        tfPriceToEn.addValidator(new RegexpValidator(regexDouble, "Not a number"));

        HorizontalLayout hlPriceEn = new HorizontalLayout(tfPriceFromEn, new Label("-"), tfPriceToEn, new Label("USD"));
        hlPriceEn.setCaption(BundleUtils.getLanguage("lbl.restaurant.priceEn"));
        hlPriceEn.setSpacing(true);
        form.addComponent(hlPriceEn);

        tfWaitingTimeFrom = new TextField();
        tfWaitingTimeFrom.setWidth(200.0f, Unit.PIXELS);
        tfWaitingTimeFrom.addStyleName(ValoTheme.TEXTFIELD_TINY);
        tfWaitingTimeFrom.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);

        CSValidator vlWaitingTimeFrom = new CSValidator();
        vlWaitingTimeFrom.extend(tfWaitingTimeFrom);
        vlWaitingTimeFrom.setRegExp(regexDouble);
        vlWaitingTimeFrom.setPreventInvalidTyping(true);
        tfWaitingTimeFrom.addValidator(new RegexpValidator(regexDouble, "Not a number"));

        tfWaitingTimeTo = new TextField();
        tfWaitingTimeTo.setWidth(200.0f, Unit.PIXELS);
        tfWaitingTimeTo.addStyleName(ValoTheme.TEXTFIELD_TINY);
        tfWaitingTimeTo.addStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
        CSValidator vlWaitingTimeTo = new CSValidator();
        vlWaitingTimeTo.extend(tfWaitingTimeTo);
        vlWaitingTimeTo.setRegExp(regexDouble);
        vlWaitingTimeTo.setPreventInvalidTyping(true);
        tfWaitingTimeTo.addValidator(new RegexpValidator(regexDouble, "Not a number"));

        HorizontalLayout hlWaitingTime = new HorizontalLayout(tfWaitingTimeFrom, new Label("-"), tfWaitingTimeTo);
        hlWaitingTime.setCaption(BundleUtils.getLanguage("lbl.dish.waitingTime"));
        hlWaitingTime.setSpacing(true);
        form.addComponent(hlWaitingTime);

        Map<String, LocaleDTO> mapLocale = ClientServiceImpl.getAllLocales();
        if (mapLocale != null && !mapLocale.isEmpty()) {
            List<LocaleDTO> listLocale = new ArrayList<>(mapLocale.values());
            listLocale.stream().map((localeDTO) -> new OptionGroupUI(localeDTO.getLocale()//
                    , localeDTO.getId())).forEach((ogLocale) -> {
                form.addComponent(ogLocale);
                listOgLocale.add(ogLocale);
            });
        }

        tagSuggestFieldUI = new TagSuggestFieldUI(false);
        tagSuggestFieldUI.setWidth(80.0f, Unit.PERCENTAGE);
        form.addComponent(tagSuggestFieldUI);

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
            RestaurantDTO dto = new RestaurantDTO();
            dto.setName(tfName.getValue());
            dto.setIntroduce(tfIntroduce.getValue());
            if (cbActive.getValue() && cbInActive.getValue()) {
                dto.setRestaurantStatus(null);
            } else if (cbActive.getValue()) {
                dto.setRestaurantStatus("1");
            } else if (cbInActive.getValue()) {
                dto.setRestaurantStatus("0");
            } else {
                dto.setRestaurantStatus("-1");
            }

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
            dto.setWaitingTimeFrom(tfWaitingTimeFrom.getValue());
            dto.setWaitingTimeTo(tfWaitingTimeTo.getValue());

            dto.setPriceFromVn(tfPriceFromVn.getValue());
            dto.setPriceToVn(tfPriceToVn.getValue());

            dto.setPriceFromEn(tfPriceFromEn.getValue());
            dto.setPriceToEn(tfPriceToEn.getValue());

            dto.setAddress(tfAddress.getValue());
            dto.setPhoneNumber(tfPhoneNumber.getValue());
            dto.setCapacity(tfCapacity.getValue());

            if (cbCarParkingYes.getValue() && cbCarParkingNo.getValue()) {
                dto.setCarParking(null);
            } else if (cbCarParkingYes.getValue()) {
                dto.setCarParking("1");
            } else if (cbCarParkingNo.getValue()) {
                dto.setCarParking("0");
            } else {
                dto.setCarParking("-1");
            }

            if (cbMotobikeParkingYes.getValue() && cbMotobikeParkingNo.getValue()) {
                dto.setMotobikeParking(null);
            } else if (cbMotobikeParkingYes.getValue()) {
                dto.setMotobikeParking("1");
            } else if (cbMotobikeParkingNo.getValue()) {
                dto.setMotobikeParking("0");
            } else {
                dto.setMotobikeParking("-1");
            }

            dto.setOperatingTimeStart(tfOperatingTimeStart.getValue() == null ? null
                    : DateTimeUtils.convertDateToString(tfOperatingTimeStart.getValue(), "HH:mm"));

            dto.setOperatingTimeEnd(tfOperatingTimeEnd.getValue() == null ? null
                    : DateTimeUtils.convertDateToString(tfOperatingTimeEnd.getValue(), "HH:mm"));

            List<ResultDTO> list = tagSuggestFieldUI.getValue();
            List<String> listTag = new ArrayList<>();
            if (list
                    != null && !list.isEmpty()) {
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
