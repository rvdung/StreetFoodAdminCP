/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.view;

import com.dungnv.streetfood.dto.CategoryDTO;
import com.dungnv.streetfood.dto.UserDTO;
import com.dungnv.streetfood.service.ClientServiceImpl;
import com.dungnv.streetfood.ui.CategoryItemUI;
import com.dungnv.streetfood.ui.CommonSearchPagedUI;
import com.dungnv.utils.BundleUtils;
import com.dungnv.utils.Constants;
import com.dungnv.utils.FWUtils;
import static com.dungnv.view.dashboard.DashboardView.TITLE_ID;
import com.kbdunn.vaadin.addons.fontawesome.FontAwesome;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ODIN NGUYEN
 */
@SuppressWarnings("serial")
public final class CategoryView extends Panel implements View {

    private final VerticalLayout root;
    private Label titleLabel;
    private List<CategoryDTO> list;
    private List<CategoryItemUI> listItem;
    private int itemCount;
    TextField txtSearch;
    Button lbAdvanced;
    VerticalLayout header;
    VerticalLayout searchLayout;
    CategorySearchDetail searchDetail;
    CommonSearchPagedUI uiSearchPaged;
    private CategoryDTO dtoSearch;
    UserDTO user;

    Button btnAdd;

    public CategoryView() {
        user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());
        setLocale(VaadinSession.getCurrent().getLocale());
        setSizeFull();
        root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(true);
        root.addStyleName("dashboard-view");
        setContent(root);
        Responsive.makeResponsive(root);
        init();
        root.addComponent(header);

        builAction();
        dtoSearch = new CategoryDTO();
        onSearch(true);

    }

    public void onSearch(Boolean isResetPageCount) {
        if (listItem != null && !listItem.isEmpty()) {
            searchLayout.removeAllComponents();
            listItem.clear();
        } else {
            listItem = new ArrayList<>();
        }
        itemCount = 0;

        int recPerPage = Integer.valueOf(uiSearchPaged.getCbRecordPerPage().getValue().toString());
        int startRow = Integer.valueOf(uiSearchPaged.getCbPaged().getValue().toString());

        if (isResetPageCount) {
            List<CategoryDTO> tempList = ClientServiceImpl.getInstance().getListCategoryDTOLess(user.getUsername()//
                    , getLocale().getLanguage(), getLocale().getCountry(), null, dtoSearch, 0, 0, true, null, null);
            if (tempList != null && !tempList.isEmpty()) {
                uiSearchPaged.resetPageCount(Integer.valueOf(tempList.get(0).getId()));
            }
            return;
        }

        list = ClientServiceImpl.getInstance().getListCategoryDTOLess(user.getUsername()//
                , getLocale().getLanguage(), getLocale().getCountry(), null, dtoSearch,
                (startRow - 1) * recPerPage, recPerPage, false, null, null);
        if (list != null && !list.isEmpty()) {
            uiSearchPaged.setVisible(true);
            for (int i = 0; i < list.size(); i++) {
                CategoryItemUI ui = new CategoryItemUI(list.get(i), this);
                if (i == list.size() - 1) {
                    ui.addStyleName("item-interator-last");
                }
                listItem.add(ui);
                searchLayout.addComponent(ui);
            }
        } else {
            uiSearchPaged.setVisible(false);
        }

    }

    private void init() {
        header = new VerticalLayout();
        header.addStyleName("viewheader");
        header.setSpacing(true);
        header.setId("hehehe");

        titleLabel = new Label(BundleUtils.getLanguage("menu.CATEGORY"));
        titleLabel.setId(TITLE_ID);
        titleLabel.setWidth("100%");
        titleLabel.addStyleName(ValoTheme.LABEL_BOLD);
        header.addComponent(titleLabel);

        btnAdd = new Button();
        btnAdd.setCaption(FontAwesome.PLUS_CIRCLE.getLabel().setSize3x().getCssHtml());
        btnAdd.setCaptionAsHtml(true);
        btnAdd.addStyleName("float-button");
//        add.addStyleName(ValoTheme.BUTTON_BORDERLESS);
//        add.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
//        add.addStyleName(ValoTheme.BUTTON_LARGE);
        header.addComponent(btnAdd);

        HorizontalLayout item = new HorizontalLayout();

//        item.setSpacing(true);
        item.setWidth("100%");
        header.addComponent(item);

        txtSearch = new TextField();
        txtSearch.setImmediate(true);
        txtSearch.setTextChangeEventMode(TextChangeEventMode.TIMEOUT);
        txtSearch.setIcon(FontAwesome.SEARCH);
        txtSearch.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        txtSearch.setInputPrompt(BundleUtils.getLanguage("lbl.category.name.input"));
        txtSearch.setWidth("100%");

        item.addComponent(txtSearch);
        item.setComponentAlignment(txtSearch, Alignment.TOP_RIGHT);

        lbAdvanced = new Button();
        lbAdvanced.setCaption(BundleUtils.getLanguage("lbl.search.advanced"));
        lbAdvanced.addStyleName(ValoTheme.BUTTON_LINK);
        lbAdvanced.addStyleName(ValoTheme.BUTTON_LARGE);
        item.addComponent(lbAdvanced);
        item.setComponentAlignment(lbAdvanced, Alignment.BOTTOM_LEFT);

        uiSearchPaged = new CommonSearchPagedUI(1) {
            @Override
            public void cbChanged() {
                this.getCbPaged().addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            onSearch(false);
                        }
                    }
                });
                this.getCbRecordPerPage().addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        if (event.getProperty().getValue() != null) {
                            onSearch(true);
                        }

                    }
                });
            }
        };

        header.addComponent(uiSearchPaged);

        searchLayout = new VerticalLayout();
//        searchLayout.setSpacing(true);
        searchLayout.setSizeFull();
        header.addComponent(searchLayout);

        searchDetail = new CategorySearchDetail(this);
    }

    private void builAction() {

        txtSearch.addTextChangeListener((FieldEvents.TextChangeEvent event) -> {
            dtoSearch = new CategoryDTO();
            dtoSearch.setName(event.getText());
            onSearch(Boolean.TRUE);
        });

        lbAdvanced.addClickListener((Button.ClickEvent event) -> {
            searchDetail.setWidth("80%");
            searchDetail.setHeight("70%");
            searchDetail.setModal(true);
            FWUtils.reloadWindow(searchDetail);
            UI.getCurrent().addWindow(searchDetail);
        });

        btnAdd.addClickListener((Button.ClickEvent event) -> {
            CategoryInsert CategoryInsert = new CategoryInsert(null, event.getButton().findAncestor(CategoryView.class), Constants.ACTION.INSERT);
            CategoryInsert.setWidth("80%");
            CategoryInsert.setHeight("90%");
            CategoryInsert.setModal(true);
            FWUtils.reloadWindow(CategoryInsert);
            UI.getCurrent().addWindow(CategoryInsert);
        });

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    public Label getTitleLabel() {
        return titleLabel;
    }

    public void setTitleLabel(Label titleLabel) {
        this.titleLabel = titleLabel;
    }

    public List<CategoryDTO> getList() {
        return list;
    }

    public void setList(List<CategoryDTO> list) {
        this.list = list;
    }

    public List<CategoryItemUI> getListItem() {
        return listItem;
    }

    public void setListItem(List<CategoryItemUI> listItem) {
        this.listItem = listItem;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public TextField getTxtSearch() {
        return txtSearch;
    }

    public void setTxtSearch(TextField txtSearch) {
        this.txtSearch = txtSearch;
    }

    public Button getLbAdvanced() {
        return lbAdvanced;
    }

    public void setLbAdvanced(Button lbAdvanced) {
        this.lbAdvanced = lbAdvanced;
    }

    public VerticalLayout getHeader() {
        return header;
    }

    public void setHeader(VerticalLayout header) {
        this.header = header;
    }

    public VerticalLayout getSearchLayout() {
        return searchLayout;
    }

    public void setSearchLayout(VerticalLayout searchLayout) {
        this.searchLayout = searchLayout;
    }

    public CategorySearchDetail getSearchDetail() {
        return searchDetail;
    }

    public void setSearchDetail(CategorySearchDetail searchDetail) {
        this.searchDetail = searchDetail;
    }

    public CommonSearchPagedUI getUiSearchPaged() {
        return uiSearchPaged;
    }

    public void setUiSearchPaged(CommonSearchPagedUI uiSearchPaged) {
        this.uiSearchPaged = uiSearchPaged;
    }

    public CategoryDTO getDtoSearch() {
        return dtoSearch;
    }

    public void setDtoSearch(CategoryDTO dtoSearch) {
        this.dtoSearch = dtoSearch;
    }

}
