/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.view;

import com.dungnv.streetfood.dto.ArticleDTO;
import com.dungnv.streetfood.dto.ArticleLanguageDTO;
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
public class ArticleInsert extends Window implements WizardProgressListener {

    private final ArticleDTO dto;
    VerticalLayout layout;
    ArticleView view;
    private Wizard wizard;
    Constants.ACTION action;

    public ArticleInsert(ArticleDTO dto, ArticleView view, Constants.ACTION action) {

        setLocale(VaadinSession.getCurrent().getLocale());
        if (dto != null && !StringUtils.isNullOrEmpty(dto.getId())) {
            UserDTO user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());
            dto = ClientServiceImpl.getInstance().getArticleDetail(user.getUsername()//
                    , getLocale().getLanguage(), getLocale().getCountry(), null, dto.getId());
        } else {
            dto = null;
        }

        this.dto = dto == null ? new ArticleDTO() : dto;
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
            wizard.addStep(new IntroStep(), BundleUtils.getLanguage("lbl.article.insert"));
        } else if (Constants.ACTION.UPDATE.equals(action)) {
            wizard.addStep(new IntroStep(), BundleUtils.getLanguage("lbl.article.update"));
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
                    result = ClientServiceImpl.getInstance().insertArticle(user.getUsername()//
                            , getLocale().getLanguage(), getLocale().getCountry(), null, dto);
                    break;
                case UPDATE:
                    result = ClientServiceImpl.getInstance().updateArticle(user.getUsername()//
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
        TextField tfTitle;
        TextArea tfShortContent;
        RichTextWinDowUI tfContent;
        InsertImageUI tfImageUrl;

        TagSuggestFieldUI tagSuggestFieldUI;
        FormLayout formLayout;
        Label lbInfo;

        @Override
        public String getCaption() {
            if (StringUtils.isNullOrEmpty(dto.getId())) {
                return BundleUtils.getLanguage("lbl.article.insert");
            } else {
                return BundleUtils.getLanguage("lbl.article.update");
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

            tfTitle = new TextField(BundleUtils.getLanguage("lbl.article.title"));
            tfTitle.setWidth(100.0f, Unit.PERCENTAGE);
            tfTitle.setRequired(true);
            formLayout.addComponent(tfTitle);

            tfShortContent = new TextArea(BundleUtils.getLanguage("lbl.article.shortContent"));
            tfShortContent.setWidth(100.0f, Unit.PERCENTAGE);
            formLayout.addComponent(tfShortContent);

            tfImageUrl = new InsertImageUI(BundleUtils.getLanguage("lbl.article.imageUrl"), true);

            tfImageUrl.setWidth(100.0f, Unit.PERCENTAGE);
            formLayout.addComponent(tfImageUrl);

            tfContent = new RichTextWinDowUI(BundleUtils.getLanguage("lbl.article.content"));

            tfContent.setWidth(100.0f, Unit.PERCENTAGE);
            formLayout.addComponent(tfContent);

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

            if (!StringUtils.isNullOrEmpty(dto.getViewCount())) {
                info += "&nbsp&nbsp|&nbsp&nbsp" + FontAwesome.EYE.getHtml() + "&nbsp&nbsp:&nbsp&nbsp" + dto.getViewCount();
            }

            if (!StringUtils.isNullOrEmpty(dto.getUpdateTime())) {
                info += "</br>" + FontAwesome.CALENDAR_CHECK_O.getHtml() + " " + dto.getUpdateTime();
                if (!StringUtils.isNullOrEmpty(dto.getUpdateTimeGmt())) {
                    info += " - " + dto.getUpdateTimeGmt() + " GMT";
                }
            }

            lbInfo.setCaption(info);

            tfTitle.setValue(dto.getTitle() == null ? "" : dto.getTitle());
            tfShortContent.setValue(dto.getShortContent() == null ? "" : dto.getShortContent());
            tfContent.setValue(StringUtils.decodeBase64(dto.getContent()));

            if (dto.getListImgUrl() != null && !dto.getListImgUrl().isEmpty()) {
                tfImageUrl.setValue(dto.getListImgUrl());
            }

            if (dto.getListTag() != null && !dto.getListTag().isEmpty()) {
                List<ResultDTO> list = new ArrayList<>();
                dto.getListTag().stream().forEach((tag) -> {
                    list.add(new ResultDTO(null, tag));
                });
                tagSuggestFieldUI.setValue(list);
            }
        }

        private boolean setValue() {
            dto.setTitle(tfTitle.getValue());
            dto.setShortContent(tfShortContent.getValue());
            dto.setContent(StringUtils.encodeBase64(tfContent.getValue()));

            try {
                dto.setListImgUrl(tfImageUrl.getValue());
            } catch (FWException e) {
                Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
                return false;
            }
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

                    Tab tab = tsLocale.addTab(new ArticleLanguageUI(localeDTO, null), localeDTO.getLocale());
                    mapTabLocale.put(localeDTO.getId(), tab);
                }
            }

            getValue();

            return layout;
        }

        private void setValue() {
            if (!mapTabLocale.isEmpty()) {
                List<Tab> listTab = new ArrayList<>(mapTabLocale.values());
                List<ArticleLanguageDTO> list = new ArrayList<>();
                for (Tab tab : listTab) {
                    ArticleLanguageUI ui = (ArticleLanguageUI) tab.getComponent();
                    CheckBox cb = mapCheckBox.get(ui.getLocaleDTO().getId());
                    if (cb != null && cb.getValue()) {
                        ArticleLanguageDTO langDTO = ui.getArticleLanguageDTO() == null //
                                ? new ArticleLanguageDTO() //
                                : ui.getArticleLanguageDTO();
                        langDTO.setTitle(ui.getTfTitle().getValue());
                        langDTO.setShortContent(ui.getTfShortContent().getValue());
                        langDTO.setContent(StringUtils.encodeBase64(ui.getTfContent().getValue()));
                        langDTO.setLanguageCode(ui.getLocaleDTO().getId());
                        list.add(langDTO);
                    }
                }
                dto.setListLanguage(list);
            }
        }

        private void getValue() {
            if (dto.getListLanguage() != null && !dto.getListLanguage().isEmpty()) {
                for (ArticleLanguageDTO langDTO : dto.getListLanguage()) {
                    CheckBox cb = mapCheckBox.get(langDTO.getLanguageCode());
                    if (cb != null) {
                        cb.setValue(true);
                    }
                    Tab tab = mapTabLocale.get(langDTO.getLanguageCode());
                    if (tab != null) {
                        ArticleLanguageUI ui = (ArticleLanguageUI) tab.getComponent();
                        ui.setArticleLanguageDTO(langDTO);
                        ui.getTfTitle().setValue(langDTO.getTitle() == null ? "" : langDTO.getTitle());
                        ui.getTfShortContent().setValue(langDTO.getShortContent() == null ? "" : langDTO.getShortContent());
                        ui.getTfContent().setValue(langDTO.getContent() == null ? "" : StringUtils.decodeBase64(langDTO.getContent()));
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

    class ArticleLanguageUI extends FormLayout {

        LocaleDTO localeDTO;
        TextField tfTitle;
        TextArea tfShortContent;
        RichTextWinDowUI tfContent;
        ArticleLanguageDTO articleLangageDTO;

        public ArticleLanguageUI(LocaleDTO localeDTO, ArticleLanguageDTO articleLangageDTO) {
            this.localeDTO = localeDTO;
            this.articleLangageDTO = articleLangageDTO;
            init();
        }

        public void init() {
            this.addStyleName("light");
            this.setSizeFull();
            this.setWidth("100%");
            this.setMargin(true);
            this.setSpacing(true);
//            layout.addComponent(formLayout);

            tfTitle = new TextField(BundleUtils.getLanguage("lbl.article.title"));
            tfTitle.setWidth(100.0f, Unit.PERCENTAGE);
            tfTitle.setRequired(true);
            this.addComponent(tfTitle);

            tfShortContent = new TextArea(BundleUtils.getLanguage("lbl.article.shortContent"));
            tfShortContent.setWidth(100.0f, Unit.PERCENTAGE);
            tfShortContent.setRequired(true);
            this.addComponent(tfShortContent);

            tfContent = new RichTextWinDowUI(BundleUtils.getLanguage("lbl.article.content"));
            tfContent.setWidth(100.0f, Unit.PERCENTAGE);
            this.addComponent(tfContent);
        }

        public LocaleDTO getLocaleDTO() {
            return localeDTO;
        }

        public void setLocaleDTO(LocaleDTO localeDTO) {
            this.localeDTO = localeDTO;
        }

        public TextField getTfTitle() {
            return tfTitle;
        }

        public void setTfTitle(TextField tfTitle) {
            this.tfTitle = tfTitle;
        }

        public TextArea getTfShortContent() {
            return tfShortContent;
        }

        public void setTfShortContent(TextArea tfShortContent) {
            this.tfShortContent = tfShortContent;
        }

        public RichTextWinDowUI getTfContent() {
            return tfContent;
        }

        public void setTfContent(RichTextWinDowUI tfContent) {
            this.tfContent = tfContent;
        }

        public ArticleLanguageDTO getArticleLanguageDTO() {
            return articleLangageDTO;
        }

        public void setArticleLanguageDTO(ArticleLanguageDTO articleLangageDTO) {
            this.articleLangageDTO = articleLangageDTO;
        }

        public ArticleLanguageDTO getArticleLangageDTO() {
            return articleLangageDTO;
        }

        public void setArticleLangageDTO(ArticleLanguageDTO articleLangageDTO) {
            this.articleLangageDTO = articleLangageDTO;
        }

    }
}
