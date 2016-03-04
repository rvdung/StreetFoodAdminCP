/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.ui;

import com.dungnv.utils.StringUtils;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 * @author ODIN NGUYEN
 */
public class MultiSelectUI extends VerticalLayout {

    TextField txtSearch;
    ListSelect lsItem;
    String searchField;
    BeanItemContainer container;

    public MultiSelectUI(String searchField) {
        this.searchField = searchField;
        init();
        setAction();
    }

    private void init() {

        txtSearch = new TextField();
        txtSearch.setWidth(100.0f, Unit.PERCENTAGE);
        txtSearch.addStyleName(ValoTheme.TEXTFIELD_BORDERLESS);
        txtSearch.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        txtSearch.setIcon(FontAwesome.SEARCH);
        addComponent(txtSearch);

        lsItem = new ListSelect();
        lsItem.setSizeFull();
        lsItem.addStyleName(ValoTheme.COMBOBOX_BORDERLESS);
        lsItem.setItemCaptionPropertyId(searchField);
        lsItem.setRows(10);
        lsItem.setMultiSelect(true);
        lsItem.setImmediate(true);
        lsItem.setItemCaptionMode(ItemCaptionMode.PROPERTY);
        addComponent(lsItem);
    }

    private void setAction() {
        txtSearch.addTextChangeListener((FieldEvents.TextChangeEvent event) -> {
            BeanItemContainer container1 = (BeanItemContainer) lsItem.getContainerDataSource();
            container1.removeAllContainerFilters();
            lsItem.setValue(null);
            String value = event.getText();
            if (!StringUtils.isNullOrEmpty(value)) {
                container1.addContainerFilter(searchField, value, true, false);
            }
        });
    }

    public BeanItemContainer getContainer() {
        return container;
    }

    public void setContainer(BeanItemContainer container) {
        this.container = container;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setItemCaptionPropertyId(String propertiesId) {
        lsItem.setItemCaptionPropertyId(propertiesId);
    }

    public void setLabelCaption(String caption) {
        txtSearch.setCaption(caption);
    }

    public TextField getTxtSearch() {
        return txtSearch;
    }

    public void setTxtSearch(TextField txtSearch) {
        this.txtSearch = txtSearch;
    }

    public ListSelect getLsItem() {
        return lsItem;
    }

    public void setLsItem(ListSelect lsItem) {
        this.lsItem = lsItem;
    }

}
