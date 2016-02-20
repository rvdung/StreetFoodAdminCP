/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.view;

import com.dungnv.streetfood.dto.DishDTO;
import com.dungnv.streetfood.dto.DishLanguageDTO;
import com.dungnv.streetfood.dto.LocaleDTO;
import com.dungnv.streetfood.dto.ResultDTO;
import com.dungnv.streetfood.dto.UserDTO;
import com.dungnv.streetfood.service.ClientServiceImpl;
import com.dungnv.streetfood.ui.InsertImageUI;
import com.dungnv.streetfood.ui.RichTextWinDowUI;
import com.dungnv.streetfood.ui.TagSuggestFieldUI;
import com.dungnv.utils.BundleUtils;
import com.dungnv.utils.Constants;
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
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.vaadin.maddon.label.RichText;
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
public class DishInsert extends Window implements WizardProgressListener {

    private final DishDTO dto;
    VerticalLayout layout;
    DishView view;
    private Wizard wizard;
    Constants.ACTION action;

    public DishInsert(DishDTO dto, DishView view, Constants.ACTION action) {

        setLocale(VaadinSession.getCurrent().getLocale());
        if (dto != null && !StringUtils.isNullOrEmpty(dto.getId())) {
            UserDTO user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());
            dto = ClientServiceImpl.getInstance().getDishDetail(user.getUsername()//
                    , getLocale().getLanguage(), getLocale().getCountry(), null, dto.getId());
        } else {
            dto = null;
        }

        this.dto = dto == null ? new DishDTO() : dto;
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
            wizard.addStep(new IntroStep(), "Thêm mới nhóm món ăn");
        } else if (Constants.ACTION.UPDATE.equals(action)) {
            wizard.addStep(new IntroStep(), "Cập nhật thông tin nhóm món ăn");
        }
        wizard.addStep(new SecondStep(), "intro");

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
                    result = ClientServiceImpl.getInstance().insertDish(user.getUsername()//
                            , getLocale().getLanguage(), getLocale().getCountry(), null, dto);
                    break;
                case UPDATE:
                    result = ClientServiceImpl.getInstance().updateDish(user.getUsername()//
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
            Notification.show(result.getMessage(), Notification.Type.ERROR_MESSAGE);
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
        TextArea tfShortDescription;
        RichTextWinDowUI tfLongDescription;
        OptionGroup ogStatus;
        TagSuggestFieldUI tagSuggestFieldUI;
        FormLayout formLayout;
        Label lbInfo;

        @Override
        public String getCaption() {
            if (StringUtils.isNullOrEmpty(dto.getId())) {
                return "Thêm mới nhóm món ăn";
            } else {
                return "Cập nhật thông tin nhóm món ăn";
            }
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

            tfName = new TextField(BundleUtils.getLanguage("lbl.category.name"));
            tfName.setWidth(100.0f, Unit.PERCENTAGE);
            tfName.setRequired(true);
            formLayout.addComponent(tfName);

            tfImageUrl = new InsertImageUI(BundleUtils.getLanguage("lbl.category.imageUrl"), true);
            tfImageUrl.setWidth(100.0f, Unit.PERCENTAGE);
//            tfImageUrl.setRequired(true);
            formLayout.addComponent(tfImageUrl);

            tfShortDescription = new TextArea(BundleUtils.getLanguage("lbl.shortDescription"));
            tfShortDescription.setWidth(100.0f, Unit.PERCENTAGE);
            formLayout.addComponent(tfShortDescription);

            tfLongDescription = new RichTextWinDowUI(BundleUtils.getLanguage("lbl.longDescription"));
            tfLongDescription.setWidth(100.0f, Unit.PERCENTAGE);
//            tfLongDescription.setHeight("300px");
            formLayout.addComponent(tfLongDescription);

            ogStatus = new OptionGroup("lbl.status");
            ogStatus.addItem(BundleUtils.getLanguage("lbl.active"));
            ogStatus.addItem(BundleUtils.getLanguage("lbl.inActive"));
            ogStatus.addStyleName("horizontal");
            formLayout.addComponent(ogStatus);

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
                info += "<b>" + FontAwesome.BARCODE.getHtml() + " " + dto.getId() + "</b>";
            }
            if (!StringUtils.isNullOrEmpty(dto.getDishCreateTime())) {
                info += "</br>" + FontAwesome.CALENDAR.getHtml() + " " + dto.getDishCreateTime();
                if (!StringUtils.isNullOrEmpty(dto.getDishCreateTimeGmt())) {
                    info += " - " + dto.getDishCreateTimeGmt() + " GMT";
                }
            }

            if (!StringUtils.isNullOrEmpty(dto.getDishUpdateTime())) {
                info += "</br>" + FontAwesome.CALENDAR_CHECK_O.getHtml() + " " + dto.getDishUpdateTime();
                if (!StringUtils.isNullOrEmpty(dto.getDishUpdateTimeGmt())) {
                    info += " - " + dto.getDishUpdateTimeGmt() + " GMT";
                }
            }
            lbInfo.setCaption(info);

            tfName.setValue(dto.getName() == null ? "" : dto.getName());
            if (dto.getListImgUrl() != null && !dto.getListImgUrl().isEmpty()) {
                tfImageUrl.setValue(dto.getListImgUrl());
            }
            tfShortDescription.setValue(dto.getShortDescription() == null ? "" : dto.getShortDescription());
            tfLongDescription.setValue(StringUtils.decodeBase64(dto.getLongDescription()));
            ogStatus.setValue(StringUtils.isNullOrEmpty(dto.getDishStatus()) || dto.getDishStatus().equals("1")
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
            try {
                dto.setListImgUrl(tfImageUrl.getValue());
            } catch (FWException e) {
                Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
                return false;
            }
            dto.setShortDescription(tfShortDescription.getValue());
            dto.setLongDescription(StringUtils.encodeBase64(tfLongDescription.getValue()));
            dto.setDishStatus(ogStatus.getValue().equals(BundleUtils.getLanguage("lbl.active"))
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
            return "Nhập thông tin ngôn ngữ";
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

//                    cbLanguage.addValueChangeListener(new Property.ValueChangeListener() {
//                        @Override
//                        public void valueChange(Property.ValueChangeEvent event) {
//                            Boolean value = (Boolean) event.getProperty().getValue();
//                        }
//                    });
                }
            }

            getValue();

            return layout;
        }

        private void setValue() {
            if (!mapTabLocale.isEmpty()) {
                List<Tab> listTab = new ArrayList<>(mapTabLocale.values());
                List<DishLanguageDTO> list = new ArrayList<>();
                for (Tab tab : listTab) {
                    CatgoryLanguageUI ui = (CatgoryLanguageUI) tab.getComponent();
                    CheckBox cb = mapCheckBox.get(ui.getLocaleDTO().getId());
                    if (cb != null && cb.getValue()) {
                        DishLanguageDTO langDTO = ui.getDishLanguageDTO() == null //
                                ? new DishLanguageDTO() //
                                : ui.getDishLanguageDTO();
                        langDTO.setName(ui.getTfName().getValue());
                        langDTO.setShortDescription(ui.getTfShortDescription().getValue());
                        langDTO.setLongDescription(StringUtils.encodeBase64(ui.getTfLongDescription().getValue()));
                        langDTO.setLanguageCode(ui.getLocaleDTO().getId());
                        list.add(langDTO);
                    }
                }
                dto.setListLanguage(list);
            }
        }

        private void getValue() {
            if (dto.getListLanguage() != null && !dto.getListLanguage().isEmpty()) {
                for (DishLanguageDTO langDTO : dto.getListLanguage()) {
                    CheckBox cb = mapCheckBox.get(langDTO.getLanguageCode());
                    if (cb != null) {
                        cb.setValue(true);
                    }
                    Tab tab = mapTabLocale.get(langDTO.getLanguageCode());
                    if (tab != null) {
                        CatgoryLanguageUI ui = (CatgoryLanguageUI) tab.getComponent();
                        ui.setDishLanguageDTO(langDTO);
                        ui.getTfName().setValue(langDTO.getName() == null ? null : langDTO.getName());
                        ui.getTfShortDescription().setValue(langDTO.getShortDescription() == null ? null : langDTO.getShortDescription());
                        ui.getTfLongDescription().setValue(StringUtils.decodeBase64(langDTO.getLongDescription()));
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
        TextArea tfShortDescription;
        RichTextWinDowUI tfLongDescription;
        DishLanguageDTO dishGroupLangageDTO;

        public CatgoryLanguageUI(LocaleDTO localeDTO, DishLanguageDTO dishGroupLangageDTO) {
            this.localeDTO = localeDTO;
            this.dishGroupLangageDTO = dishGroupLangageDTO;
            init();
        }

        public void init() {
            this.addStyleName("light");
            this.setSizeFull();
            this.setWidth("100%");
            this.setMargin(true);
            this.setSpacing(true);
//            layout.addComponent(formLayout);

            tfName = new TextField(BundleUtils.getLanguage("lbl.category.name"));
            tfName.setWidth(100.0f, Unit.PERCENTAGE);
            tfName.setRequired(true);
            this.addComponent(tfName);

            tfShortDescription = new TextArea(BundleUtils.getLanguage("lbl.shortDescription"));
            tfShortDescription.setWidth(100.0f, Unit.PERCENTAGE);
            this.addComponent(tfShortDescription);

            tfLongDescription = new RichTextWinDowUI(BundleUtils.getLanguage("lbl.longDescription"));
            tfLongDescription.setWidth(100.0f, Unit.PERCENTAGE);
            this.addComponent(tfLongDescription);
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

        public TextArea getTfShortDescription() {
            return tfShortDescription;
        }

        public void setTfShortDescription(TextArea tfShortDescription) {
            this.tfShortDescription = tfShortDescription;
        }

        public RichTextWinDowUI getTfLongDescription() {
            return tfLongDescription;
        }

        public void setTfLongDescription(RichTextWinDowUI tfLongDescription) {
            this.tfLongDescription = tfLongDescription;
        }

        public DishLanguageDTO getDishLanguageDTO() {
            return dishGroupLangageDTO;
        }

        public void setDishLanguageDTO(DishLanguageDTO dishGroupLangageDTO) {
            this.dishGroupLangageDTO = dishGroupLangageDTO;
        }
    }
}
