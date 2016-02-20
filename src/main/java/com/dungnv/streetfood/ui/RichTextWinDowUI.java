/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.ui;

import com.kbdunn.vaadin.addons.fontawesome.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 *
 * @author ODIN NGUYEN
 */
public class RichTextWinDowUI extends VerticalLayout {

    CssLayout layoutWindow;
    RichTextArea richTextArea;
    Button btnEdit;
    Button btnCloseWindow;

    public RichTextWinDowUI() {
        init();
    }

    public RichTextWinDowUI(String caption) {
        this.setCaption(caption);
        init();
        addAction();
    }

    public String getValue() {
        return richTextArea.getValue();
    }

    public void setValue(String value) {
        richTextArea.setValue(value);
    }

    public void init() {
        layoutWindow = new CssLayout();
        layoutWindow.setSizeFull();
        richTextArea = new RichTextArea();
        richTextArea.setSizeFull();
        layoutWindow.addComponent(richTextArea);

        btnCloseWindow = new Button();
        btnCloseWindow.setCaption(FontAwesome.SAVE.getLabel().setSize3x().spin().getCssHtml());
        btnCloseWindow.setCaptionAsHtml(true);
        btnCloseWindow.addStyleName("float-button");
        layoutWindow.addComponent(btnCloseWindow);

        btnEdit = new Button(FontAwesome.EDIT.getLabel().flipHorizontal().getCssHtml());
        btnEdit.setCaptionAsHtml(true);
        this.addComponent(btnEdit);
    }

    public void addAction() {
        btnEdit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Window window = new Window();
                window.setSizeFull();
                window.setResizable(false);
                window.setClosable(false);
                window.setContent(layoutWindow);
                UI.getCurrent().addWindow(window);

            }
        });

        btnCloseWindow.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                UI.getCurrent().removeWindow(event.getButton().findAncestor(Window.class));
            }
        });
    }

}
