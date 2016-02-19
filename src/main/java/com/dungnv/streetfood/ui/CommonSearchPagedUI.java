/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.ui;

import com.dungnv.utils.BundleUtils;
import com.kbdunn.vaadin.addons.fontawesome.FontAwesome;
import com.vaadin.data.Property;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author ODIN NGUYEN
 */
public abstract class CommonSearchPagedUI extends VerticalLayout {

    private Button btnFastBackward;
    private Button btnBackward;
    private ComboBox cbPaged;
    private ComboBox cbRecordPerPage;
    private Button btnFoward;
    private Button btnFastFoward;
    private int pageCount;

    public abstract void cbChanged();

    public CommonSearchPagedUI(int pageCount) {
        this.pageCount = pageCount;
        init();
        buildAction();
        cbChanged();
    }

    public void resetPageCount(int size) {
        int recPerPage = (Integer) cbRecordPerPage.getValue();
        pageCount = size / recPerPage;
        if (pageCount == 0) {
            pageCount = 1;
        } else if (size % recPerPage != 0) {
            pageCount++;
        }
        cbPaged.removeAllItems();
        for (int i = 1; i <= pageCount; i++) {
            cbPaged.addItem(i);
        }
        cbPaged.select(1);
    }

    private final void init() {

        setWidth("100%");
        setStyleName("item-search-box");
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        this.addComponent(layout);

        FormLayout form = new FormLayout();
//        form.setWidth("100%");
        form.setMargin(false);
        layout.addComponent(form);
        layout.setComponentAlignment(form, Alignment.MIDDLE_CENTER);

        cbRecordPerPage = new ComboBox();
        cbRecordPerPage.setWidth("65px");
        cbRecordPerPage.setStyleName(ValoTheme.COMBOBOX_TINY);
        cbRecordPerPage.setCaption(BundleUtils.getLanguage("lbl.recordsPerPage"));
        cbRecordPerPage.setTextInputAllowed(false);
        cbRecordPerPage.addItem(10);
        cbRecordPerPage.addItem(20);
        cbRecordPerPage.addItem(30);
        cbRecordPerPage.addItem(50);
        cbRecordPerPage.addItem(100);
        cbRecordPerPage.addItem(200);
        cbRecordPerPage.select(10);
        form.addComponent(cbRecordPerPage);
        cbRecordPerPage.setNullSelectionAllowed(false);

        btnFastBackward = new Button();
        btnFastBackward.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        btnFastBackward.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        btnFastBackward.setIcon(FontAwesome.FAST_BACKWARD);
        layout.addComponent(btnFastBackward);
        layout.setComponentAlignment(btnFastBackward, Alignment.MIDDLE_CENTER);

        btnBackward = new Button();
        btnBackward.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        btnBackward.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        btnBackward.setIcon(FontAwesome.BACKWARD);
        layout.addComponent(btnBackward);
        layout.setComponentAlignment(btnBackward, Alignment.MIDDLE_CENTER);

        cbPaged = new ComboBox();
        cbPaged.setTextInputAllowed(false);
        for (int i = 1; i <= pageCount; i++) {
            cbPaged.addItem(i);
        }

        cbPaged.setNullSelectionAllowed(false);
        cbPaged.select(1);
        cbPaged.addStyleName(ValoTheme.COMBOBOX_TINY);
        cbPaged.setWidth("60px");
        layout.addComponent(cbPaged);
        layout.setComponentAlignment(cbPaged, Alignment.MIDDLE_CENTER);

        btnFoward = new Button();
        btnFoward.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        btnFoward.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        btnFoward.setIcon(FontAwesome.FORWARD);
        layout.addComponent(btnFoward);
        layout.setComponentAlignment(btnFoward, Alignment.MIDDLE_CENTER);

        btnFastFoward = new Button();
        btnFastFoward.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        btnFastFoward.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
        btnFastFoward.setIcon(FontAwesome.FAST_FORWARD);
        layout.addComponent(btnFastFoward);
        layout.setComponentAlignment(btnFastFoward, Alignment.MIDDLE_CENTER);
        enableComponent();
    }

    private void buildAction() {
        btnFastBackward.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                cbPaged.setValue(1);
                enableComponent();
            }
        });
        btnBackward.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                int value = Integer.valueOf(cbPaged.getValue().toString()) - 1;
                if (value >= 1) {
                    cbPaged.setValue(value);
                    enableComponent();
                }
            }
        });

        btnFoward.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                int value = Integer.valueOf(cbPaged.getValue().toString()) + 1;
                if (value <= pageCount) {
                    cbPaged.setValue(value);
                    enableComponent();
                }

            }
        });

        btnFastFoward.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                cbPaged.setValue(pageCount);
                enableComponent();

            }
        });

        cbPaged.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                enableComponent();
            }
        });

    }

    private void enableComponent() {
        try {
            int value = Integer.valueOf(cbPaged.getValue().toString());
            if (value == 1) {
                btnFastBackward.setEnabled(false);
                btnBackward.setEnabled(false);
            } else {
                btnFastBackward.setEnabled(true);
                btnBackward.setEnabled(true);
            }
            if (value == pageCount) {
                btnFoward.setEnabled(false);
                btnFastFoward.setEnabled(false);
            } else {
                btnFoward.setEnabled(true);
                btnFastFoward.setEnabled(true);
            }
        } catch (Exception e) {
        }
    }

    public Button getBtnFastBackward() {
        return btnFastBackward;
    }

    public void setBtnFastBackward(Button btnFastBackward) {
        this.btnFastBackward = btnFastBackward;
    }

    public Button getBtnBackward() {
        return btnBackward;
    }

    public void setBtnBackward(Button btnBackward) {
        this.btnBackward = btnBackward;
    }

    public ComboBox getCbPaged() {
        return cbPaged;
    }

    public void setCbPaged(ComboBox cbPaged) {
        this.cbPaged = cbPaged;
    }

    public ComboBox getCbRecordPerPage() {
        return cbRecordPerPage;
    }

    public void setCbRecordPerPage(ComboBox cbRecordPerPage) {
        this.cbRecordPerPage = cbRecordPerPage;
    }

    public Button getBtnFoward() {
        return btnFoward;
    }

    public void setBtnFoward(Button btnFoward) {
        this.btnFoward = btnFoward;
    }

    public Button getBtnFastFoward() {
        return btnFastFoward;
    }

    public void setBtnFastFoward(Button btnFastFoward) {
        this.btnFastFoward = btnFastFoward;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}
