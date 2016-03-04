/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.streetfood.view;

import com.dungnv.streetfood.dto.RestaurantDTO;
import com.dungnv.streetfood.dto.UserDTO;
import com.dungnv.streetfood.service.ClientServiceImpl;
import com.dungnv.streetfood.ui.CommonSearchPagedUI;
import com.dungnv.streetfood.ui.RestaurantItemUI;
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
import com.vaadin.ui.AbstractTextField;
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
public class RestaurantView extends Panel implements View {

    private final VerticalLayout root;
    private Label titleLabel;
    private List<RestaurantDTO> list;
    private List<RestaurantItemUI> listItem;
    private int itemCount;
    TextField txtSearch;
    Button lbAdvanced;
    VerticalLayout header;
    VerticalLayout searchLayout;
    RestaurantSearchDetail searchDetail;
    CommonSearchPagedUI uiSearchPaged;
    private RestaurantDTO dtoSearch;
    UserDTO user;

    Button btnAdd;

    public RestaurantView() {
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
        dtoSearch = new RestaurantDTO();
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
            List<RestaurantDTO> tempList = ClientServiceImpl.getInstance().getListRestaurantDTOLess(user.getUsername()//
                    , getLocale().getLanguage(), getLocale().getCountry(), null, dtoSearch, 0, 0, true, null, null);
            if (tempList != null && !tempList.isEmpty()) {
                uiSearchPaged.resetPageCount(Integer.valueOf(tempList.get(0).getId()));
            }
            return;
        }

        list = ClientServiceImpl.getInstance().getListRestaurantDTOLess(user.getUsername()//
                , getLocale().getLanguage(), getLocale().getCountry(), null, dtoSearch,
                (startRow - 1) * recPerPage, recPerPage, false, null, null);
        if (list != null && !list.isEmpty()) {
            uiSearchPaged.setVisible(true);
            for (int i = 0; i < list.size(); i++) {
                RestaurantItemUI ui = new RestaurantItemUI(list.get(i), this);
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

        titleLabel = new Label(BundleUtils.getLanguage("menu.RESTAURANT"));
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
        txtSearch.setTextChangeEventMode(AbstractTextField.TextChangeEventMode.TIMEOUT);
        txtSearch.setIcon(FontAwesome.SEARCH);
        txtSearch.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        txtSearch.setInputPrompt(BundleUtils.getLanguage("lbl.restaurant.name.input"));
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

        searchDetail = new RestaurantSearchDetail(this);
    }

    private void builAction() {

        txtSearch.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent event) {
                dtoSearch = new RestaurantDTO();
                dtoSearch.setName(event.getText());
                onSearch(Boolean.TRUE);
            }
        });

        lbAdvanced.addClickListener((Button.ClickEvent event) -> {
            searchDetail.setWidth("50%");
            searchDetail.setHeight("50%");
            searchDetail.setModal(true);
            FWUtils.reloadWindow(searchDetail);
            UI.getCurrent().addWindow(searchDetail);
        });

        btnAdd.addClickListener((Button.ClickEvent event) -> {
            RestaurantInsert RestaurantInsert = new RestaurantInsert(null, event.getButton().findAncestor(RestaurantView.class), Constants.ACTION.INSERT);
            RestaurantInsert.setWidth("80%");
            RestaurantInsert.setHeight("90%");
            RestaurantInsert.setModal(true);
            FWUtils.reloadWindow(RestaurantInsert);
            UI.getCurrent().addWindow(RestaurantInsert);
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

    public List<RestaurantDTO> getList() {
        return list;
    }

    public void setList(List<RestaurantDTO> list) {
        this.list = list;
    }

    public List<RestaurantItemUI> getListItem() {
        return listItem;
    }

    public void setListItem(List<RestaurantItemUI> listItem) {
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

    public RestaurantSearchDetail getSearchDetail() {
        return searchDetail;
    }

    public void setSearchDetail(RestaurantSearchDetail searchDetail) {
        this.searchDetail = searchDetail;
    }

    public CommonSearchPagedUI getUiSearchPaged() {
        return uiSearchPaged;
    }

    public void setUiSearchPaged(CommonSearchPagedUI uiSearchPaged) {
        this.uiSearchPaged = uiSearchPaged;
    }

    public RestaurantDTO getDtoSearch() {
        return dtoSearch;
    }

    public void setDtoSearch(RestaurantDTO dtoSearch) {
        this.dtoSearch = dtoSearch;
    }

}
