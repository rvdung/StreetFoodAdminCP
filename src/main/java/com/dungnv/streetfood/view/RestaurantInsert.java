/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.view;

import com.dungnv.streetfood.dto.RestaurantDTO;
import com.dungnv.streetfood.dto.RestaurantLanguageDTO;
import com.dungnv.streetfood.dto.LocaleDTO;
import com.dungnv.streetfood.dto.ResultDTO;
import com.dungnv.streetfood.dto.UserDTO;
import com.dungnv.streetfood.service.ClientServiceImpl;
import com.dungnv.streetfood.ui.InsertImageUI;
import com.dungnv.streetfood.ui.RichTextWinDowUI;
import com.dungnv.streetfood.ui.TagSuggestFieldUI;
import com.dungnv.utils.BundleUtils;
import com.dungnv.utils.Constants;
import com.dungnv.utils.DateTimeUtils;
import com.dungnv.utils.FWException;
import com.dungnv.utils.StringUtils;
import com.kbdunn.vaadin.addons.fontawesome.FontAwesome;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.vaadin.csvalidation.CSValidator;
import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardProgressListener;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;
import org.vaadin.thomas.timefield.TimeField;

/**
 *
 * @author ODIN NGUYEN
 */
public class RestaurantInsert extends Window implements WizardProgressListener {

    private final RestaurantDTO dto;
    VerticalLayout layout;
    RestaurantView view;
    private Wizard wizard;
    Constants.ACTION action;

    public RestaurantInsert(RestaurantDTO dto, RestaurantView view, Constants.ACTION action) {

        setLocale(VaadinSession.getCurrent().getLocale());
        if (dto != null && !StringUtils.isNullOrEmpty(dto.getId())) {
            UserDTO user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());
            dto = ClientServiceImpl.getInstance().getRestaurantDetail(user.getUsername()//
                    , getLocale().getLanguage(), getLocale().getCountry(), null, dto.getId());
        } else {
            dto = null;
        }

        this.dto = dto == null ? new RestaurantDTO() : dto;
        this.view = view;
        this.action = action;
        init();
        setContent(layout);
    }

    public final void init() {

        layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

        wizard = new Wizard();
        wizard.setHeight("80%");
        wizard.setUriFragmentEnabled(true);
        wizard.addListener(this);
        ((HorizontalLayout) wizard.getFinishButton().findAncestor(HorizontalLayout.class)).setMargin(new MarginInfo(true, false, true, true));
        if (Constants.ACTION.INSERT.equals(action)) {
            wizard.addStep(new IntroStep(), BundleUtils.getLanguage("lbl.restaurant.insert"));
        } else if (Constants.ACTION.UPDATE.equals(action)) {
            wizard.addStep(new IntroStep(), BundleUtils.getLanguage("lbl.restaurant.update"));
        }
        wizard.addStep(new SecondStep(), BundleUtils.getLanguage("lbl.language.update"));

        layout.addComponent(wizard);
        layout.setComponentAlignment(wizard, Alignment.TOP_CENTER);
    }

    @Override
    public void activeStepChanged(WizardStepActivationEvent event) {

    }

    @Override
    public void stepSetChanged(WizardStepSetChangedEvent event) {

    }

    @Override
    public void wizardCompleted(WizardCompletedEvent event) {
        UserDTO user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());
        ResultDTO result = null;
        if (null != action) {
            switch (action) {
                case INSERT:
                    result = ClientServiceImpl.getInstance().insertRestaurant(user.getUsername()//
                            , getLocale().getLanguage(), getLocale().getCountry(), null, dto);
                    break;
                case UPDATE:
                    result = ClientServiceImpl.getInstance().updateRestaurant(user.getUsername()//
                            , getLocale().getLanguage(), getLocale().getCountry(), null, dto);
                    break;
                default:
                    UI.getCurrent().removeWindow(event.getWizard().findAncestor(Window.class));
                    return;
            }
        }

        if (result != null && Constants.SUCCESS.equals(result.getMessage())) {
            view.onSearch(Boolean.TRUE);
            UI.getCurrent().removeWindow(event.getWizard().findAncestor(Window.class));
        } else {
            Notification.show(result == null || result.getKey() == null ? Constants.FAIL
                    : result.getKey(), Notification.Type.ERROR_MESSAGE);
        }
    }

    @Override
    public void wizardCancelled(WizardCancelledEvent event) {
        UI.getCurrent().removeWindow(event.getWizard().findAncestor(Window.class));
    }

    class IntroStep implements WizardStep {

        VerticalLayout vLayout;
        TextField tfName;
        InsertImageUI tfImageUrl;
        TextField tfAddress;
        TextField tfPhoneNumber;
        TextField tfSiteUrl;
        TextField tfPriceFromVn;
        TextField tfPriceToVn;
        TextField tfPriceFromEn;
        TextField tfPriceToEn;
        OptionGroup ogCarParking;
        OptionGroup ogMotobikeParking;
        TextField tfCapacity;
        TextField tfWaitingTime;
        TimeField tfOperatingTimeStart;
        TimeField tfOperatingTimeEnd;
        RichTextWinDowUI tfIntroduce;
        OptionGroup ogStatus;
        TagSuggestFieldUI tagSuggestFieldUI;
        FormLayout formLayout;
        Label lbInfo;

        @Override
        public String getCaption() {
            if (Constants.ACTION.INSERT.equals(action)) {
                return BundleUtils.getLanguage("lbl.restaurant.insert");
            } else if (Constants.ACTION.UPDATE.equals(action)) {
                return BundleUtils.getLanguage("lbl.restaurant.update");
            }
            return "1";
        }

        @Override
        public Component getContent() {
            vLayout = new VerticalLayout();
            vLayout.setWidth(100.0f, Unit.PERCENTAGE);
//            vLayout.setHeight(400.0f, Unit.PIXELS);
//            vLayout.addStyleName("wizard-layout"); 
            vLayout.setMargin(true);

            lbInfo = new Label();
            lbInfo.setWidth(100.0f, Unit.PERCENTAGE);
            lbInfo.setCaptionAsHtml(true);
            vLayout.addComponent(lbInfo);

            formLayout = new FormLayout();
            formLayout.addStyleName("light");
            formLayout.setSizeFull();
            formLayout.setWidth("100%");
            formLayout.setSpacing(true);
            vLayout.addComponent(formLayout);

            tfName = new TextField(BundleUtils.getLanguage("lbl.restaurant.name"));
            tfName.setWidth(100.0f, Unit.PERCENTAGE);
            tfName.setRequired(true);
            formLayout.addComponent(tfName);

            ogStatus = new OptionGroup(BundleUtils.getLanguage("lbl.status"));
            ogStatus.setRequired(true);
            ogStatus.addItem(BundleUtils.getLanguage("lbl.active"));
            ogStatus.addItem(BundleUtils.getLanguage("lbl.inActive"));
            ogStatus.addStyleName("horizontal");
            formLayout.addComponent(ogStatus);

            tfAddress = new TextField(BundleUtils.getLanguage("lbl.restaurant.address"));
            tfAddress.setWidth(100.0f, Unit.PERCENTAGE);
            formLayout.addComponent(tfAddress);

            tfPhoneNumber = new TextField(BundleUtils.getLanguage("lbl.restaurant.phoneNumber"));
            tfPhoneNumber.setWidth(100.0f, Unit.PERCENTAGE);
            formLayout.addComponent(tfPhoneNumber);

            tfSiteUrl = new TextField(BundleUtils.getLanguage("lbl.restaurant.siteUrl"));
            tfSiteUrl.setWidth(100.0f, Unit.PERCENTAGE);
            formLayout.addComponent(tfSiteUrl);

            String regexNumber = "[0-9\\.]*";
            tfPriceFromVn = new TextField();
            tfPriceFromVn.setWidth(200.0f, Unit.PIXELS);

            CSValidator vlPriceFromVn = new CSValidator();
            vlPriceFromVn.extend(tfPriceFromVn);
            vlPriceFromVn.setRegExp(regexNumber);
            vlPriceFromVn.setPreventInvalidTyping(true);
            tfPriceFromVn.addValidator(new RegexpValidator(regexNumber, "Not a number"));

            tfPriceToVn = new TextField();
            tfPriceToVn.setWidth(200.0f, Unit.PIXELS);
            CSValidator vlPriceToVn = new CSValidator();
            vlPriceToVn.extend(tfPriceToVn);
            vlPriceToVn.setRegExp(regexNumber);
            vlPriceToVn.setPreventInvalidTyping(true);
            tfPriceToVn.addValidator(new RegexpValidator(regexNumber, "Not a number"));

            HorizontalLayout hlPriceVn = new HorizontalLayout(tfPriceFromVn, new Label("-"), tfPriceToVn, new Label("VND"));
            hlPriceVn.setCaption(BundleUtils.getLanguage("lbl.restaurant.priceVn"));
            hlPriceVn.setSpacing(true);
            formLayout.addComponent(hlPriceVn);

            tfPriceFromEn = new TextField();
            tfPriceFromEn.setWidth(200.0f, Unit.PIXELS);
            CSValidator vlPriceFromEn = new CSValidator();
            vlPriceFromEn.extend(tfPriceFromEn);
            vlPriceFromEn.setRegExp(regexNumber);
            vlPriceFromEn.setPreventInvalidTyping(true);
            tfPriceFromEn.addValidator(new RegexpValidator(regexNumber, "Not a number"));

            tfPriceToEn = new TextField();
            tfPriceToEn.setWidth(200.0f, Unit.PIXELS);
            CSValidator vlPriceToEn = new CSValidator();
            vlPriceToEn.extend(tfPriceToEn);
            vlPriceToEn.setRegExp(regexNumber);
            vlPriceToEn.setPreventInvalidTyping(true);
            tfPriceToEn.addValidator(new RegexpValidator(regexNumber, "Not a number"));

            HorizontalLayout hlPriceEn = new HorizontalLayout(tfPriceFromEn, new Label("-"), tfPriceToEn, new Label("USD"));

            hlPriceEn.setCaption(BundleUtils.getLanguage("lbl.restaurant.priceEn"));
            hlPriceEn.setSpacing(true);
            formLayout.addComponent(hlPriceEn);

            ogMotobikeParking = new OptionGroup(BundleUtils.getLanguage("lbl.restaurant.motobikeParking"));

            ogMotobikeParking.addItem(BundleUtils.getLanguage("lbl.yes"));
            ogMotobikeParking.addItem(BundleUtils.getLanguage("lbl.no"));
            ogMotobikeParking.addStyleName("horizontal");
            formLayout.addComponent(ogMotobikeParking);

            ogCarParking = new OptionGroup(BundleUtils.getLanguage("lbl.restaurant.carParking"));

            ogCarParking.addItem(BundleUtils.getLanguage("lbl.yes"));
            ogCarParking.addItem(BundleUtils.getLanguage("lbl.no"));
            ogCarParking.addStyleName("horizontal");
            formLayout.addComponent(ogCarParking);

            tfCapacity = new TextField(BundleUtils.getLanguage("lbl.restaurant.capacity"));

            tfCapacity.setWidth(100.0f, Unit.PERCENTAGE);
            tfCapacity.setRequired(true);
            formLayout.addComponent(tfCapacity);

            tfWaitingTime = new TextField(BundleUtils.getLanguage("lbl.restaurant.waitingTime"));

            tfWaitingTime.setWidth(100.0f, Unit.PERCENTAGE);
            tfWaitingTime.setRequired(true);
            formLayout.addComponent(tfWaitingTime);

            tfOperatingTimeStart = new TimeField();
            tfOperatingTimeEnd = new TimeField();

            HorizontalLayout hlOperatingTime = new HorizontalLayout(tfOperatingTimeStart, new Label(" - "), tfOperatingTimeEnd);
            hlOperatingTime.setCaption(BundleUtils.getLanguage("lbl.restaurant.operatingTime"));
            hlOperatingTime.setSpacing(true);
            formLayout.addComponent(hlOperatingTime);

            tfImageUrl = new InsertImageUI(BundleUtils.getLanguage("lbl.restaurant.imageUrl"), true);

            tfImageUrl.setWidth(100.0f, Unit.PERCENTAGE);
            formLayout.addComponent(tfImageUrl);

            tfIntroduce = new RichTextWinDowUI(BundleUtils.getLanguage("lbl.restaurant.introduce"));

            tfIntroduce.setWidth(100.0f, Unit.PERCENTAGE);
            formLayout.addComponent(tfIntroduce);

            tagSuggestFieldUI = new TagSuggestFieldUI(true);

            tagSuggestFieldUI.setWidth(100.0f, Unit.PERCENTAGE);
            formLayout.addComponent(tagSuggestFieldUI);

            HorizontalLayout hlButton = new HorizontalLayout();

            hlButton.setSpacing(true);
            hlButton.setMargin(true);
            formLayout.addComponent(hlButton);

            getValue();
            return vLayout;
        }

        private void getValue() {
            String info = "";
            if (!StringUtils.isNullOrEmpty(dto.getId())) {
                info += "<b>" + FontAwesome.BARCODE.getHtml() + " " + dto.getId() + "</b></br>";
            }
            if (!StringUtils.isNullOrEmpty(dto.getRating())) {
                Double rating = Double.valueOf(dto.getRating());
                for (int i = 0; i < 5; i++) {
                    if (i < rating) {
                        info += "<span style=\"color:#197DE1; padding-right:5px;\">" + FontAwesome.STAR.getHtml() + "</span>";
                    } else {
                        info += FontAwesome.STAR_O.getHtml();
                    }
                }
            }

            if (!StringUtils.isNullOrEmpty(dto.getViewCount())) {
                info += StringUtils.isNullOrEmpty(dto.getViewCount())
                        ? FontAwesome.EYE.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + dto.getViewCount()
                        : "&nbsp|" + FontAwesome.EYE.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + dto.getViewCount();
            }
            if (!StringUtils.isNullOrEmpty(dto.getCommentCount())) {
                info += StringUtils.isNullOrEmpty(dto.getCommentCount())
                        ? FontAwesome.COMMENTS.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + dto.getCommentCount()
                        : "&nbsp|" + FontAwesome.COMMENTS.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + dto.getCommentCount();
            }
            if (!StringUtils.isNullOrEmpty(dto.getShareCount())) {
                info += StringUtils.isNullOrEmpty(dto.getShareCount())
                        ? FontAwesome.SHARE_ALT.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + dto.getShareCount()
                        : "&nbsp|" + FontAwesome.SHARE_ALT.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + dto.getShareCount();
            }

            if (!StringUtils.isNullOrEmpty(dto.getRestaurantCreateTime())) {
                info += "</br>" + FontAwesome.CALENDAR.getHtml() + " " + dto.getRestaurantCreateTime();
                if (!StringUtils.isNullOrEmpty(dto.getRestaurantCreateTimeGmt())) {
                    info += " - " + dto.getRestaurantCreateTimeGmt() + " GMT";
                }
            }
            if (!StringUtils.isNullOrEmpty(dto.getRestaurantUpdateTime())) {
                info += "</br>" + FontAwesome.CALENDAR_CHECK_O.getHtml() + " " + dto.getRestaurantUpdateTime();
                if (!StringUtils.isNullOrEmpty(dto.getRestaurantUpdateTimeGmt())) {
                    info += " - " + dto.getRestaurantUpdateTimeGmt() + " GMT";
                }
            }

            lbInfo.setCaption(info);

            tfName.setValue(dto.getName() == null ? "" : dto.getName());
            tfAddress.setValue(dto.getAddress() == null ? "" : dto.getAddress());
            tfPhoneNumber.setValue(dto.getPhoneNumber() == null ? "" : dto.getPhoneNumber());
            tfSiteUrl.setValue(dto.getSiteUrl() == null ? "" : dto.getSiteUrl());
            tfPriceFromVn.setValue(dto.getPriceFromVn() == null ? "" : dto.getPriceFromVn());
            tfPriceToVn.setValue(dto.getPriceToVn() == null ? "" : dto.getPriceToVn());
            tfPriceFromEn.setValue(dto.getPriceFromEn() == null ? "" : dto.getPriceFromEn());
            tfPriceToEn.setValue(dto.getPriceToEn() == null ? "" : dto.getPriceToEn());

            ogCarParking.setValue(StringUtils.isNullOrEmpty(dto.getCarParking()) || dto.getCarParking().equals("1")
                    ? BundleUtils.getLanguage("lbl.yes")
                    : BundleUtils.getLanguage("lbl.no"));

            ogMotobikeParking.setValue(StringUtils.isNullOrEmpty(dto.getMotobikeParking()) || dto.getMotobikeParking().equals("1")
                    ? BundleUtils.getLanguage("lbl.yes")
                    : BundleUtils.getLanguage("lbl.no"));

            tfCapacity.setValue(dto.getCapacity() == null ? "" : dto.getCapacity());
            tfWaitingTime.setValue(dto.getWaitingTime() == null ? "" : dto.getWaitingTime());

            if (!StringUtils.isNullOrEmpty(dto.getOperatingTimeStart())) {
                try {
                    tfOperatingTimeStart.setValue(DateTimeUtils.convertStringToTime(dto.getOperatingTimeStart(), "HH:mm"));
                } catch (Exception e) {
                }
            } else {
                try {
                    tfOperatingTimeStart.setValue(DateTimeUtils.convertStringToTime("07:00", "HH:mm"));
                } catch (Exception ex) {
                    Logger.getLogger(RestaurantInsert.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (!StringUtils.isNullOrEmpty(dto.getOperatingTimeEnd())) {
                try {
                    tfOperatingTimeEnd.setValue(DateTimeUtils.convertStringToTime(dto.getOperatingTimeEnd(), "HH:mm"));
                } catch (Exception e) {
                }
            } else {
                try {
                    tfOperatingTimeEnd.setValue(DateTimeUtils.convertStringToTime("23:59", "HH:mm"));
                } catch (Exception ex) {
                    Logger.getLogger(RestaurantInsert.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (dto.getListImgUrl() != null && !dto.getListImgUrl().isEmpty()) {
                tfImageUrl.setValue(dto.getListImgUrl());
            }

            tfIntroduce.setValue(StringUtils.decodeBase64(dto.getIntroduce()));
            ogStatus.setValue(StringUtils.isNullOrEmpty(dto.getRestaurantStatus()) || dto.getRestaurantStatus().equals("1")
                    ? BundleUtils.getLanguage("lbl.active")
                    : BundleUtils.getLanguage("lbl.inActive"));
            if (dto.getListTag() != null && !dto.getListTag().isEmpty()) {
                List<ResultDTO> list = new ArrayList<>();
                dto.getListTag().stream().forEach((tag) -> {
                    list.add(new ResultDTO(null, tag));
                });
                tagSuggestFieldUI.setValue(list);
            }
        }

        private boolean setValue() {
            dto.setName(tfName.getValue());
            dto.setAddress(tfAddress.getValue());

            dto.setPhoneNumber(tfPhoneNumber.getValue());
            dto.setSiteUrl(tfSiteUrl.getValue());

            dto.setPriceFromVn(tfPriceFromVn.getValue());
            dto.setPriceToVn(tfPriceToVn.getValue());

            dto.setPriceFromEn(tfPriceFromEn.getValue());
            dto.setPriceToEn(tfPriceToEn.getValue());

            dto.setCarParking(ogCarParking.getValue().equals(BundleUtils.getLanguage("lbl.yes"))
                    ? "1" : "0");

            dto.setMotobikeParking(ogMotobikeParking.getValue().equals(BundleUtils.getLanguage("lbl.yes"))
                    ? "1" : "0");

            dto.setCapacity(tfCapacity.getValue());
            dto.setWaitingTime(tfWaitingTime.getValue());

            dto.setWaitingTime(tfWaitingTime.getValue());
            dto.setOperatingTimeStart(tfOperatingTimeStart.getValue() == null ? null
                    : DateTimeUtils.convertDateToString(tfOperatingTimeStart.getValue(), "HH:mm"));

            dto.setOperatingTimeEnd(tfOperatingTimeEnd.getValue() == null ? null
                    : DateTimeUtils.convertDateToString(tfOperatingTimeEnd.getValue(), "HH:mm"));

            try {
                dto.setListImgUrl(tfImageUrl.getValue());
            } catch (FWException e) {
                Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
                return false;
            }
            dto.setIntroduce(StringUtils.encodeBase64(tfIntroduce.getValue()));
            dto.setRestaurantStatus(ogStatus.getValue().equals(BundleUtils.getLanguage("lbl.active"))
                    ? "1" : "0");
            List<ResultDTO> list = tagSuggestFieldUI.getValue();
            List<String> listTag = new ArrayList<>();
            if (list != null && !list.isEmpty()) {
                for (ResultDTO tag : list) {
                    listTag.add(tag.getMessage());
                }
            }
            dto.setListTag(listTag);
            return true;
        }

        @Override
        public boolean onAdvance() {
            return setValue();
        }

        @Override
        public boolean onBack() {
            setValue();
            return true;
        }
    }

    class SecondStep implements WizardStep {

        VerticalLayout layout;
        Map<String, Tab> mapTabLocale;
        Map<String, CheckBox> mapCheckBox;

        public SecondStep() {
            mapTabLocale = new HashMap<>();
            mapCheckBox = new HashMap<>();
        }

        @Override
        public String getCaption() {
            return BundleUtils.getLanguage("lbl.language.update");
        }

        @Override
        public Component getContent() {
            layout = new VerticalLayout();
            layout.setMargin(true);
            layout.setSpacing(true);
            layout.setSizeFull();

            HorizontalLayout hlLanguage = new HorizontalLayout();
            hlLanguage.setCaption(BundleUtils.getLanguage("lbl.locale.choose"));
            hlLanguage.addStyleName("horizontal");
            hlLanguage.setSpacing(true);
            layout.addComponent(hlLanguage);

            TabSheet tsLocale = new TabSheet();
            layout.addComponent(tsLocale);

            Map<String, LocaleDTO> mapLocale = ClientServiceImpl.getAllLocales();
            if (mapLocale != null && !mapLocale.isEmpty()) {
                List<LocaleDTO> listLocale = new ArrayList<>(mapLocale.values());
                for (LocaleDTO localeDTO : listLocale) {
                    CheckBox cbLanguage = new CheckBox(localeDTO.getLocale());
                    hlLanguage.addComponent(cbLanguage);
                    mapCheckBox.put(localeDTO.getId(), cbLanguage);

                    Tab tab = tsLocale.addTab(new CatgoryLanguageUI(localeDTO, null), localeDTO.getLocale());
                    mapTabLocale.put(localeDTO.getId(), tab);
                }
            }

            getValue();

            return layout;
        }

        private void setValue() {
            if (!mapTabLocale.isEmpty()) {
                List<Tab> listTab = new ArrayList<>(mapTabLocale.values());
                List<RestaurantLanguageDTO> list = new ArrayList<>();
                for (Tab tab : listTab) {
                    CatgoryLanguageUI ui = (CatgoryLanguageUI) tab.getComponent();
                    CheckBox cb = mapCheckBox.get(ui.getLocaleDTO().getId());
                    if (cb != null && cb.getValue()) {
                        RestaurantLanguageDTO langDTO = ui.getRestaurantLanguageDTO() == null //
                                ? new RestaurantLanguageDTO() //
                                : ui.getRestaurantLanguageDTO();
                        langDTO.setName(ui.getTfName().getValue());
                        langDTO.setAddress(ui.getTfAddress().getValue());
                        langDTO.setIntroduce(StringUtils.encodeBase64(ui.getTfIntroduce().getValue()));
                        langDTO.setLanguageCode(ui.getLocaleDTO().getId());
                        list.add(langDTO);
                    }
                }
                dto.setListLanguage(list);
            }
        }

        private void getValue() {
            if (dto.getListLanguage() != null && !dto.getListLanguage().isEmpty()) {
                for (RestaurantLanguageDTO langDTO : dto.getListLanguage()) {
                    CheckBox cb = mapCheckBox.get(langDTO.getLanguageCode());
                    if (cb != null) {
                        cb.setValue(true);
                    }
                    Tab tab = mapTabLocale.get(langDTO.getLanguageCode());
                    if (tab != null) {
                        CatgoryLanguageUI ui = (CatgoryLanguageUI) tab.getComponent();
                        ui.setRestaurantLanguageDTO(langDTO);
                        ui.getTfName().setValue(langDTO.getName() == null ? null : langDTO.getName());
                        ui.getTfAddress().setValue(langDTO.getAddress() == null ? null : langDTO.getAddress());
                        ui.getTfIntroduce().setValue(StringUtils.decodeBase64(langDTO.getIntroduce()));
                    }
                }
            }
        }

        @Override
        public boolean onAdvance() {
            setValue();
            return true;
        }

        @Override
        public boolean onBack() {
            setValue();
            return true;
        }
    }

    class CatgoryLanguageUI extends FormLayout {

        LocaleDTO localeDTO;
        TextField tfName;
        TextField tfAddress;
        RichTextWinDowUI tfIntroduce;
        RestaurantLanguageDTO restaurantLangageDTO;

        public CatgoryLanguageUI(LocaleDTO localeDTO, RestaurantLanguageDTO restaurantLangageDTO) {
            this.localeDTO = localeDTO;
            this.restaurantLangageDTO = restaurantLangageDTO;
            init();
        }

        public void init() {
            this.addStyleName("light");
            this.setSizeFull();
            this.setWidth("100%");
            this.setMargin(true);
            this.setSpacing(true);
//            layout.addComponent(formLayout);

            tfName = new TextField(BundleUtils.getLanguage("lbl.restaurant.name"));
            tfName.setWidth(100.0f, Unit.PERCENTAGE);
            tfName.setRequired(true);
            this.addComponent(tfName);

            tfAddress = new TextField(BundleUtils.getLanguage("lbl.restaurant.address"));
            tfAddress.setWidth(100.0f, Unit.PERCENTAGE);
            tfAddress.setRequired(true);
            this.addComponent(tfAddress);

            tfIntroduce = new RichTextWinDowUI(BundleUtils.getLanguage("lbl.restaurant.introduce"));
            tfIntroduce.setWidth(100.0f, Unit.PERCENTAGE);
            this.addComponent(tfIntroduce);
        }

        public LocaleDTO getLocaleDTO() {
            return localeDTO;
        }

        public void setLocaleDTO(LocaleDTO localeDTO) {
            this.localeDTO = localeDTO;
        }

        public TextField getTfName() {
            return tfName;
        }

        public void setTfName(TextField tfName) {
            this.tfName = tfName;
        }

        public RichTextWinDowUI getTfIntroduce() {
            return tfIntroduce;
        }

        public void setTfIntroduce(RichTextWinDowUI tfIntroduce) {
            this.tfIntroduce = tfIntroduce;
        }

        public RestaurantLanguageDTO getRestaurantLanguageDTO() {
            return restaurantLangageDTO;
        }

        public void setRestaurantLanguageDTO(RestaurantLanguageDTO restaurantLangageDTO) {
            this.restaurantLangageDTO = restaurantLangageDTO;
        }

        public TextField getTfAddress() {
            return tfAddress;
        }

        public void setTfAddress(TextField tfAddress) {
            this.tfAddress = tfAddress;
        }

        public RestaurantLanguageDTO getRestaurantLangageDTO() {
            return restaurantLangageDTO;
        }

        public void setRestaurantLangageDTO(RestaurantLanguageDTO restaurantLangageDTO) {
            this.restaurantLangageDTO = restaurantLangageDTO;
        }

    }
}
