/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.ui;

import com.dungnv.utils.BundleUtils;
import com.dungnv.utils.FWException;
import com.dungnv.utils.FWUtils;
import com.dungnv.utils.StringUtils;
import com.kbdunn.vaadin.addons.fontawesome.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ODIN NGUYEN
 */
public class InsertImageUI extends CssLayout {

    VerticalLayout layout;
    Button btnAdd;

    public InsertImageUI(String label, Boolean isRequired) {
        super();
        if (isRequired) {
            setCaption(label + "<div class=\"v-required-field-indicator\" aria-hidden=\"true\">*</div>");
            setCaptionAsHtml(true);
        } else {
            setCaption(label);
        }

        init();
        addAction();
    }

    public final void init() {
        setWidth("100%");
        addStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);

        layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setSpacing(true);
        this.addComponent(layout);

        btnAdd = new Button();
        btnAdd.setIcon(FontAwesome.PLUS_CIRCLE);
        btnAdd.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        btnAdd.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        btnAdd.setWidth("25px");
        btnAdd.setHeight("25px");
        this.addComponent(btnAdd);
    }

    public final void addAction() {
        btnAdd.addClickListener((Button.ClickEvent event) -> {
            addValue(null);
        });
    }

    public void setValue(List<String> values) {
        clearValue();
        if (values != null) {
            for (String value : values) {
                addValue(value);
            }
        }
    }

    public List<String> getValue() throws FWException {
        int count = layout.getComponentCount();
        if (count == 0) {
            return null;
        }
        List<String> listValue = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            TfInsertImageUI item = (TfInsertImageUI) layout.getComponent(i);
            if(StringUtils.isNullOrEmpty(item.getValue())
                    || !FWUtils.urlExists(item.getValue())){
                throw new FWException(BundleUtils.getLanguage("lbl.url.invalid"));
            }
            listValue.add(item.getValue());
        }
        return listValue;
    }

    public void clearValue() {
        layout.removeAllComponents();
    }

    public void addValue(String value) {
        addValue(layout.getComponentCount(), value);
    }

    public void addValue(int index, String value) {
        TfInsertImageUI imageUI = new TfInsertImageUI(this, value);
        layout.addComponent(imageUI);
    }

    class TfInsertImageUI extends CssLayout {

        InsertImageUI ui;
        TextField tfImageUrl;
        Button btnUp;
        Button btnDown;
        Button btnDelete;

        public TfInsertImageUI(InsertImageUI ui, String value) {
            this.ui = ui;
            init();
            tfImageUrl.setValue(value == null ? "" : value);
            addAction();
        }

        public final void init() {
            setSizeFull();
            addStyleName("display-table");
            tfImageUrl = new TextField();
            tfImageUrl.setInputPrompt("http://");
            tfImageUrl.setWidth(80.0f, Unit.PERCENTAGE);
            this.addComponent(tfImageUrl);

            btnUp = new Button();
            btnUp.setCaption(FontAwesome.CHEVRON_UP.getLabel().getCssHtml());
            btnUp.setCaptionAsHtml(true);
            btnUp.addStyleName("icon-button");
            btnUp.setWidth("16px");
            btnUp.setHeight("16px");
            this.addComponent(btnUp);

            btnDown = new Button();
            btnDown.setCaption(FontAwesome.CHEVRON_DOWN.getLabel().getCssHtml());
            btnDown.setCaptionAsHtml(true);
            btnDown.addStyleName("icon-button");
            btnDown.setWidth("16px");
            btnDown.setHeight("16px");
            this.addComponent(btnDown);

            btnDelete = new Button();
            btnDelete.setCaption(FontAwesome.TIMES.getLabel().getCssHtml());
            btnDelete.setCaptionAsHtml(true);
            btnDelete.addStyleName("icon-button");
            btnDelete.setWidth("16px");
            btnDelete.setHeight("16px");
            this.addComponent(btnDelete);
        }

        public final void addAction() {
            btnDelete.addClickListener((Button.ClickEvent event) -> {
                TfInsertImageUI item = event.getButton().findAncestor(TfInsertImageUI.class);
                InsertImageUI insertImageUI = item.findAncestor(InsertImageUI.class);
                insertImageUI.getLayout().removeComponent(item);
            });
            btnUp.addClickListener((Button.ClickEvent event) -> {
                TfInsertImageUI item = event.getButton().findAncestor(TfInsertImageUI.class);
                InsertImageUI insertImageUI = item.findAncestor(InsertImageUI.class);
                int currentIndex = insertImageUI.getLayout().getComponentIndex(item);
                if (currentIndex > 0) {
                    insertImageUI.getLayout().addComponent(item, currentIndex - 1);
                }
            });
            btnDown.addClickListener((Button.ClickEvent event) -> {
                TfInsertImageUI item = event.getButton().findAncestor(TfInsertImageUI.class);
                InsertImageUI insertImageUI = item.findAncestor(InsertImageUI.class);
                int currentIndex = insertImageUI.getLayout().getComponentIndex(item);
                if (currentIndex < insertImageUI.getLayout().getComponentCount() - 1) {
                    insertImageUI.getLayout().addComponent(item, currentIndex + 2);
                }
            });

//            tfImageUrl.addTextChangeListener(new FieldEvents.TextChangeListener() {
//                @Override
//                public void textChange(FieldEvents.TextChangeEvent event) {
//                    if (!StringUtils.isNullOrEmpty(event.getText())) {
//                        if (!FWUtils.urlExists(event.getText())) {
//                           
//                        } 
//                    }
//                }
//            });

        }

        public String getValue() {
            return tfImageUrl.getValue();
        }
    }

    public VerticalLayout getLayout() {
        return layout;
    }

    public void setLayout(VerticalLayout layout) {
        this.layout = layout;
    }

}
