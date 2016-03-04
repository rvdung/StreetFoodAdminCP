/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.common.ui;

import com.dungnv.utils.BundleUtils;
import com.kbdunn.vaadin.addons.fontawesome.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.OptionGroup;

/**
 *
 * @author nvdung
 */
public class OptionGroupUI extends HorizontalLayout {

    OptionGroup og;
    Button btnClear;

    public OptionGroupUI() {
        init();
        setAction();
    }

    public OptionGroupUI(String caption) {
        this();
        setCaption(caption);
    }

    public OptionGroupUI(String caption, Object data) {
        this(caption);
        setData(data);
    }

    public OptionGroupUI(String caption, Object... item) {
        this(caption);
        setOptionGroupItem(item);
    }

    public OptionGroupUI(String caption, Object data, Object... item) {
        this(caption, data);
        setOptionGroupItem(item);
    }

    private void init() {
        og = new OptionGroup();
        og.addItem(BundleUtils.getLanguage("lbl.yes"));
        og.addItem(BundleUtils.getLanguage("lbl.no"));
        og.addStyleName("horizontal");
        addComponent(og);

        btnClear = new Button();
        btnClear.setCaption(FontAwesome.TIMES.getLabel().getCssHtml());
        btnClear.setCaptionAsHtml(true);
        btnClear.addStyleName("icon-button");
        btnClear.setWidth("16px");
        btnClear.setHeight("16px");
        addComponent(btnClear);
    }

    private void setOptionGroupCaption(String caption) {
        og.setCaption(caption);
    }

    private void setOptionGroupItem(Object... item) {
        og.removeAllItems();
        if (item != null) {
            for (Object obj : item) {
                og.addItem(obj);
            }
        }
    }

    private void setAction() {
        btnClear.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                og.setValue(null);
            }
        });
    }

    public Object getValue() {
        return og.getValue();
    }

    public OptionGroup getOg() {
        return og;
    }

    public Button getBtnClear() {
        return btnClear;
    }

}
