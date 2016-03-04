/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.ui;

import com.dungnv.utils.BundleUtils;
import com.kbdunn.vaadin.addons.fontawesome.FontAwesome;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author ODIN NGUYEN
 */
public class TwinColumnUI extends VerticalLayout {

    MultiSelectUI msLeft;
    MultiSelectUI msRight;
    Button btnLeft;
    Button btnRight;
    Button btnLeftAll;
    Button btnRightAll;
    Button btnSave;
    Button btnCancel;

    String searchField;

    public TwinColumnUI(String searchField) {
        this.searchField = searchField;
        init();
        setAction();
    }

    public final void init() {
        setMargin(true);
        setSpacing(true);
        setWidth(100f, Unit.PERCENTAGE);

        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.setSpacing(true);
        hLayout.setWidth(100f, Unit.PERCENTAGE);
        addComponent(hLayout);

        msLeft = new MultiSelectUI(searchField);
        msLeft.setWidth(100.0f, Unit.PERCENTAGE);
        hLayout.addComponent(msLeft);
        hLayout.setExpandRatio(msLeft, .45f);

        VerticalLayout vButtonLayout = new VerticalLayout();
        vButtonLayout.setSpacing(true);
        hLayout.addComponent(vButtonLayout);
        hLayout.setExpandRatio(vButtonLayout, .1f);
        hLayout.setComponentAlignment(vButtonLayout, Alignment.BOTTOM_CENTER);

        btnLeftAll = new Button();
        btnLeftAll.setWidth(100f, Unit.PERCENTAGE);
        btnLeftAll.setIcon(FontAwesome.ANGLE_DOUBLE_LEFT);
        vButtonLayout.addComponent(btnLeftAll);

        btnLeft = new Button();
        btnLeft.setWidth(100f, Unit.PERCENTAGE);
        btnLeft.setIcon(FontAwesome.ANGLE_LEFT);
        vButtonLayout.addComponent(btnLeft);

        btnRight = new Button();
        btnRight.setWidth(100f, Unit.PERCENTAGE);
        btnRight.setIcon(FontAwesome.ANGLE_RIGHT);
        vButtonLayout.addComponent(btnRight);

        btnRightAll = new Button();
        btnRightAll.setWidth(100f, Unit.PERCENTAGE);
        btnRightAll.setIcon(FontAwesome.ANGLE_DOUBLE_RIGHT);
        vButtonLayout.addComponent(btnRightAll);

        msRight = new MultiSelectUI(searchField);
        msRight.setWidth(100.0f, Unit.PERCENTAGE);
        hLayout.addComponent(msRight);
        hLayout.setExpandRatio(msRight, .45f);

        HorizontalLayout hlButtonFooter = new HorizontalLayout();
        hlButtonFooter.setSpacing(true);
        addComponent(hlButtonFooter);
        setComponentAlignment(hlButtonFooter, Alignment.BOTTOM_RIGHT);

        btnSave = new Button(BundleUtils.getLanguage("lbl.save"), FontAwesome.SAVE);
        hlButtonFooter.addComponent(btnSave);
        
        btnCancel = new Button(BundleUtils.getLanguage("lbl.cancel"), FontAwesome.BAN);
        hlButtonFooter.addComponent(btnCancel);
    }

    private void setAction() {

        btnLeftAll.addClickListener((Button.ClickEvent event) -> {
            BeanItemContainer leftContainer = (BeanItemContainer) msLeft.getLsItem().getContainerDataSource();
            BeanItemContainer rightContainer = (BeanItemContainer) msRight.getLsItem().getContainerDataSource();
            List rightValue = rightContainer.getItemIds();
            if (rightValue != null && !rightValue.isEmpty()) {
                leftContainer.addAll(rightValue);
                for (int i = rightValue.size() - 1; i >= 0; i--) {
                    rightContainer.removeItem(rightValue.get(i));
                }

            }
        });
        btnLeft.addClickListener((Button.ClickEvent event) -> {
            BeanItemContainer leftContainer = (BeanItemContainer) msLeft.getLsItem().getContainerDataSource();
            BeanItemContainer rightContainer = (BeanItemContainer) msRight.getLsItem().getContainerDataSource();
            List rightValue = new ArrayList<>((Collection) msRight.getLsItem().getValue());
            if (rightValue != null && !rightValue.isEmpty()) {
                leftContainer.addAll(rightValue);
                for (int i = rightValue.size() - 1; i >= 0; i--) {
                    rightContainer.removeItem(rightValue.get(i));
                }
            }
        });
        btnRight.addClickListener((Button.ClickEvent event) -> {
            BeanItemContainer leftContainer = (BeanItemContainer) msLeft.getLsItem().getContainerDataSource();
            BeanItemContainer rightContainer = (BeanItemContainer) msRight.getLsItem().getContainerDataSource();
            List leftValue = new ArrayList<>((Collection) msLeft.getLsItem().getValue());
            if (leftValue != null && !leftValue.isEmpty()) {
                rightContainer.addAll(leftValue);
                for (int i = leftValue.size() - 1; i >= 0; i--) {
                    leftContainer.removeItem(leftValue.get(i));
                }
            }
        });

        btnRightAll.addClickListener((Button.ClickEvent event) -> {
            BeanItemContainer leftContainer = (BeanItemContainer) msLeft.getLsItem().getContainerDataSource();
            BeanItemContainer rightContainer = (BeanItemContainer) msRight.getLsItem().getContainerDataSource();
            List leftValue = leftContainer.getItemIds();
            if (leftValue != null && !leftValue.isEmpty()) {
                rightContainer.addAll(leftValue);
                for (int i = leftValue.size() - 1; i >= 0; i--) {
                    leftContainer.removeItem(leftValue.get(i));
                }

            }
        });
        
        btnCancel.addClickListener((Button.ClickEvent event) -> {
            UI.getCurrent().removeWindow(event.getButton().findAncestor(Window.class));
        });
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(Button btnSave) {
        this.btnSave = btnSave;
    }

    public Button getBtnLeftAll() {
        return btnLeftAll;
    }

    public void setBtnLeftAll(Button btnLeftAll) {
        this.btnLeftAll = btnLeftAll;
    }

    public Button getBtnRightAll() {
        return btnRightAll;
    }

    public void setBtnRightAll(Button btnRightAll) {
        this.btnRightAll = btnRightAll;
    }

    public void setLeftLabelCaption(String caption) {
        msLeft.setLabelCaption(caption);
    }

    public void setRightLabelCaption(String caption) {
        msRight.setLabelCaption(caption);
    }

    public MultiSelectUI getMsLeft() {
        return msLeft;
    }

    public void setMsLeft(MultiSelectUI msLeft) {
        this.msLeft = msLeft;
    }

    public MultiSelectUI getMsRight() {
        return msRight;
    }

    public void setMsRight(MultiSelectUI msRight) {
        this.msRight = msRight;
    }

    public Button getBtnLeft() {
        return btnLeft;
    }

    public void setBtnLeft(Button btnLeft) {
        this.btnLeft = btnLeft;
    }

    public Button getBtnRight() {
        return btnRight;
    }

    public void setBtnRight(Button btnRight) {
        this.btnRight = btnRight;
    }

}
