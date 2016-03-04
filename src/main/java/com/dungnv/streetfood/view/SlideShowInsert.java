/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.view;

import com.dungnv.streetfood.dto.SlideShowDTO;
import com.dungnv.streetfood.dto.SlideShowLanguageDTO;
import com.dungnv.streetfood.dto.LocaleDTO;
import com.dungnv.streetfood.dto.ResultDTO;
import com.dungnv.streetfood.dto.UserDTO;
import com.dungnv.streetfood.service.ClientServiceImpl;
import com.dungnv.streetfood.ui.InsertImageUI;
import com.dungnv.utils.BundleUtils;
import com.dungnv.utils.Constants;
import com.dungnv.utils.DateTimeUtils;
import com.dungnv.utils.FWException;
import com.dungnv.utils.StringUtils;
import com.kbdunn.vaadin.addons.fontawesome.FontAwesome;
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
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.TextArea;
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
import org.vaadin.teemu.wizards.Wizard;
import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.teemu.wizards.event.WizardCancelledEvent;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardProgressListener;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;

/**
 *
 * @author ODIN NGUYEN
 */
public class SlideShowInsert extends Window implements WizardProgressListener {

    private final SlideShowDTO dto;
    VerticalLayout layout;
    SlideShowView view;
    private Wizard wizard;
    Constants.ACTION action;

    public SlideShowInsert(SlideShowDTO dto, SlideShowView view, Constants.ACTION action) {

        setLocale(VaadinSession.getCurrent().getLocale());
        if (dto != null && !StringUtils.isNullOrEmpty(dto.getId())) {
            UserDTO user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());
            dto = ClientServiceImpl.getInstance().getSlideShowDetail(user.getUsername()//
                    , getLocale().getLanguage(), getLocale().getCountry(), null, dto.getId());
        } else {
            dto = null;
        }

        this.dto = dto == null ? new SlideShowDTO() : dto;
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
            wizard.addStep(new IntroStep(), BundleUtils.getLanguage("lbl.slideShow.insert"));
        } else if (Constants.ACTION.UPDATE.equals(action)) {
            wizard.addStep(new IntroStep(), BundleUtils.getLanguage("lbl.slideShow.update"));
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
                    result = ClientServiceImpl.getInstance().insertSlideShow(user.getUsername()//
                            , getLocale().getLanguage(), getLocale().getCountry(), null, dto);
                    break;
                case UPDATE:
                    result = ClientServiceImpl.getInstance().updateSlideShow(user.getUsername()//
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
        TextField tfOrder;
        PopupDateField pdfValidFromGmt;
        PopupDateField pdfValidToGmt;
        TextArea tfDesription;

        FormLayout formLayout;
        Label lbInfo;

        @Override
        public String getCaption() {
            if (Constants.ACTION.INSERT.equals(action)) {
                return BundleUtils.getLanguage("lbl.slideShow.insert");
            } else if (Constants.ACTION.UPDATE.equals(action)) {
                return BundleUtils.getLanguage("lbl.slideShow.update");
            }
            return "1";
        }

        @Override
        public Component getContent() {
            vLayout = new VerticalLayout();
            vLayout.setWidth(100.0f, Unit.PERCENTAGE);
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

            tfName = new TextField(BundleUtils.getLanguage("lbl.slideShow.name"));
            tfName.setWidth(100.0f, Unit.PERCENTAGE);
            tfName.setRequired(true);
            formLayout.addComponent(tfName);

            tfOrder = new TextField(BundleUtils.getLanguage("lbl.slideShow.order"));
            tfOrder.setWidth(100.0f, Unit.PERCENTAGE);
            tfOrder.setRequired(true);
            formLayout.addComponent(tfOrder);

            pdfValidFromGmt = new PopupDateField(BundleUtils.getLanguage("lbl.slideShow.validFromGmt"));
            pdfValidFromGmt.setWidth(100.0f, Unit.PERCENTAGE);
            pdfValidFromGmt.setRequired(true);
            formLayout.addComponent(pdfValidFromGmt);

            pdfValidToGmt = new PopupDateField(BundleUtils.getLanguage("lbl.slideShow.validToGmt"));
            pdfValidToGmt.setWidth(100.0f, Unit.PERCENTAGE);
            pdfValidToGmt.setRequired(true);
            formLayout.addComponent(pdfValidToGmt);

            tfImageUrl = new InsertImageUI(BundleUtils.getLanguage("lbl.slideShow.imageUrl"), true);
            tfImageUrl.setWidth(100.0f, Unit.PERCENTAGE);
            formLayout.addComponent(tfImageUrl);

            tfDesription = new TextArea(BundleUtils.getLanguage("lbl.slideShow.desription"));
            tfDesription.setWidth(100.0f, Unit.PERCENTAGE);

            formLayout.addComponent(tfDesription);

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

            lbInfo.setCaption(info);
            tfName.setValue(dto.getName() == null ? "" : dto.getName());
            tfDesription.setValue(dto.getDescription() == null ? "" : dto.getDescription());
            tfOrder.setValue(dto.getOrder() == null ? "" : dto.getOrder());

            pdfValidFromGmt.setValue(dto.getValidFromGmt() == null ? null
                    : DateTimeUtils.convertStringToDateTimeNotException(dto.getValidFromGmt()));

            pdfValidToGmt.setValue(dto.getValidToGmt() == null ? null
                    : DateTimeUtils.convertStringToDateTimeNotException(dto.getValidToGmt()));

            if (dto.getListImgUrl() != null && !dto.getListImgUrl().isEmpty()) {
                tfImageUrl.setValue(dto.getListImgUrl());
            }
        }

        private boolean setValue() {
            dto.setName(tfName.getValue());
            try {
                dto.setListImgUrl(tfImageUrl.getValue());
            } catch (FWException e) {
                Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
                return false;
            }
            dto.setDescription(tfDesription.getValue());
            dto.setOrder(tfOrder.getValue());
            dto.setValidFromGmt(pdfValidFromGmt.getValue() == null ? null //
                    : DateTimeUtils.convertDateTimeStampToString(pdfValidFromGmt.getValue()));
            dto.setValidToGmt(pdfValidToGmt.getValue() == null ? null //
                    : DateTimeUtils.convertDateTimeStampToString(pdfValidToGmt.getValue()));
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
                List<SlideShowLanguageDTO> list = new ArrayList<>();
                for (Tab tab : listTab) {
                    CatgoryLanguageUI ui = (CatgoryLanguageUI) tab.getComponent();
                    CheckBox cb = mapCheckBox.get(ui.getLocaleDTO().getId());
                    if (cb != null && cb.getValue()) {
                        SlideShowLanguageDTO langDTO = ui.getSlideShowLanguageDTO() == null //
                                ? new SlideShowLanguageDTO() //
                                : ui.getSlideShowLanguageDTO();
                        langDTO.setName(ui.getTfName().getValue());
                        langDTO.setDescription(ui.getTfDescription().getValue());
                        langDTO.setLanguageCode(ui.getLocaleDTO().getId());
                        list.add(langDTO);
                    }
                }
                dto.setListLanguage(list);
            }
        }

        private void getValue() {
            if (dto.getListLanguage() != null && !dto.getListLanguage().isEmpty()) {
                for (SlideShowLanguageDTO langDTO : dto.getListLanguage()) {
                    CheckBox cb = mapCheckBox.get(langDTO.getLanguageCode());
                    if (cb != null) {
                        cb.setValue(true);
                    }
                    Tab tab = mapTabLocale.get(langDTO.getLanguageCode());
                    if (tab != null) {
                        CatgoryLanguageUI ui = (CatgoryLanguageUI) tab.getComponent();
                        ui.setSlideShowLanguageDTO(langDTO);
                        ui.getTfName().setValue(langDTO.getName() == null ? null : langDTO.getName());
                        ui.getTfDescription().setValue(langDTO.getDescription() == null ? null : langDTO.getDescription());
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
        TextArea tfDescription;
        SlideShowLanguageDTO slideShowLangageDTO;

        public CatgoryLanguageUI(LocaleDTO localeDTO, SlideShowLanguageDTO slideShowLangageDTO) {
            this.localeDTO = localeDTO;
            this.slideShowLangageDTO = slideShowLangageDTO;
            init();
        }

        public void init() {
            this.addStyleName("light");
            this.setSizeFull();
            this.setWidth("100%");
            this.setMargin(true);
            this.setSpacing(true);

            tfName = new TextField(BundleUtils.getLanguage("lbl.slideShow.name"));
            tfName.setWidth(100.0f, Unit.PERCENTAGE);
            tfName.setRequired(true);
            this.addComponent(tfName);

            tfDescription = new TextArea(BundleUtils.getLanguage("lbl.slideShow.description"));
            tfDescription.setWidth(100.0f, Unit.PERCENTAGE);
            tfDescription.setRequired(true);
            this.addComponent(tfDescription);

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

        public TextArea getTfDescription() {
            return tfDescription;
        }

        public void setTfDescription(TextArea tfDescription) {
            this.tfDescription = tfDescription;
        }

        public SlideShowLanguageDTO getSlideShowLanguageDTO() {
            return slideShowLangageDTO;
        }

        public void setSlideShowLanguageDTO(SlideShowLanguageDTO slideShowLangageDTO) {
            this.slideShowLangageDTO = slideShowLangageDTO;
        }

        public SlideShowLanguageDTO getSlideShowLangageDTO() {
            return slideShowLangageDTO;
        }

        public void setSlideShowLangageDTO(SlideShowLanguageDTO slideShowLangageDTO) {
            this.slideShowLangageDTO = slideShowLangageDTO;
        }

    }
}
