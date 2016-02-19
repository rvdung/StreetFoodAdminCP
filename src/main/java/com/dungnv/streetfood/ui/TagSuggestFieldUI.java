package com.dungnv.streetfood.ui;

import com.dungnv.streetfood.dto.ResultDTO;
import com.dungnv.streetfood.dto.TagsDTO;
import com.dungnv.streetfood.dto.UserDTO;
import com.dungnv.streetfood.service.ClientServiceImpl;
import com.dungnv.utils.DataSuggestionConverter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.vaadin.suggestfield.SuggestField;
import org.vaadin.suggestfield.SuggestField.NewItemsHandler;
import org.vaadin.suggestfield.SuggestField.SuggestionHandler;
import org.vaadin.suggestfield.SuggestField.TokenHandler;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.Locale;

@SuppressWarnings("serial")
public class TagSuggestFieldUI extends CssLayout implements NewItemsHandler,
        SuggestionHandler, LayoutClickListener, TokenHandler {

    private final SuggestField suggestField;

    public TagSuggestFieldUI(Boolean isNewItemsAllowed) {
        super();
        setWidth("100%");
        addStyleName(ValoTheme.LAYOUT_HORIZONTAL_WRAPPING);
        addStyleName("token-input");
        setCaption(com.kbdunn.vaadin.addons.fontawesome.FontAwesome.TAGS.getHtml() + " Tags");
        setCaptionAsHtml(true);

        suggestField = new SuggestField();
        suggestField.setNewItemsAllowed(isNewItemsAllowed);
        suggestField.setNewItemsHandler(this);
        suggestField.setImmediate(true);
        suggestField.setTokenMode(true);
        suggestField.setSuggestionHandler(this);
        suggestField.setSuggestionConverter(new DataSuggestionConverter());
        suggestField.setTokenHandler(this);
        suggestField.setWidth("100%");
        suggestField.setPopupWidth(400);
        addComponent(suggestField);

        addLayoutClickListener(this);

    }

    @Override
    public void layoutClick(LayoutClickEvent event) {
        if (event.getClickedComponent() == null) {
            suggestField.focus();
        }

    }

    @Override
    public Object addNewItem(String newItemText) {
        return new ResultDTO(null, newItemText, newItemText);
    }

    @Override
    public List<Object> searchItems(String query) {

        if ("".equals(query) || query == null) {
            return Collections.emptyList();
        }
        TagsDTO tagsDTO = new TagsDTO(null, query, null, null, null, null);
        List<ResultDTO> result = new ArrayList<ResultDTO>();
        try {
            UserDTO user = (UserDTO) VaadinSession.getCurrent().getAttribute(UserDTO.class.getName());
            Locale locale = VaadinSession.getCurrent().getLocale();
            List<TagsDTO> lstUserBean = ClientServiceImpl.getInstance().getListTagsDTO(user.getUsername()//
                    , locale.getLanguage(), locale.getCountry(), null, tagsDTO, 0, 0, "ASC", "name");

            if (lstUserBean != null && !lstUserBean.isEmpty()) {
                for (int i = 0; i < lstUserBean.size(); i++) {
                    TagsDTO tmp = lstUserBean.get(i);

//                String str = "<div style=\"overflow-x: hidden; width:100%;white-space: normal; padding-top:5px; padding-bottom:5px; border-bottom:1px solid gray; font-size:11px;line-height:16px;\">"
//                        + "<span><b>@fullName</b></span>"
//                        + "</br>@email - @mobile"
//                        + "</br><span style=\"color:#478FCC\">@unitName</span>"
//                        + "</div>";
//                str = str.replace("@fullName", (tmp.getFullname() == null ? "" : tmp.getFullname()) + " (" + tmp.getUsername() + ")")
//                        .replace("@email", tmp.getEmail() == null ? "" : tmp.getEmail())
//                        .replace("@unitName", tmp.getUnitName() == null ? "" : tmp.getUnitName())
//                        .replace("@mobile", tmp.getMobile() == null ? "" : tmp.getMobile());
                    result.add(new ResultDTO(tmp.getId(), tmp.getName(), tmp.getName()));

                }
            }
        } catch (Exception e) {
        }

        System.out.println("Total: " + result.size());
        return new ArrayList<Object>(result);
    }

    private ClickListener addressRemoveClick = new ClickListener() {

        @Override
        public void buttonClick(ClickEvent event) {
            TagSuggestFieldUI.this.removeComponent(event.getButton());
            event.getButton().removeClickListener(addressRemoveClick);
        }
    };

    @Override
    public void handleToken(Object token) {
        if (token != null) {
            final ResultDTO address = (ResultDTO) token;
            // Skip duplicates 
            if (!getValue().contains(address)) {
                addToken(generateToken(address));
            }
        }
    }

    private void clearAddresses() {
        while (getComponentCount() > 1) {
            if (getComponent(0) instanceof Button) {
                final Button btn = (Button) getComponent(0);
                btn.removeClickListener(addressRemoveClick);
                removeComponent(btn);
            }
        }
    }

    public List<ResultDTO> getValue() {
        List<ResultDTO> values = new LinkedList<ResultDTO>();
        for (int i = 0; i < getComponentCount(); i++) {
            if (getComponent(i) instanceof Button) {
                final Button btn = (Button) getComponent(i);
                values.add((ResultDTO) btn.getData());
            }
        }
        return values;
    }

    public void setValue(List<ResultDTO> value) {
        clearAddresses();
        if (value != null) {
            for (ResultDTO address : value) {
                addToken(generateToken(address));
            }
        }
    }

    private void addToken(Button button) {
        int index = getComponentIndex(suggestField);
        addComponent(button, index);
    }

    private Button generateToken(ResultDTO token) {
        final Button btn = new Button(token.getMessage(), FontAwesome.TIMES);
        btn.setData(token);
        btn.addStyleName(ValoTheme.BUTTON_SMALL);
        btn.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
        btn.addClickListener(addressRemoveClick);

        btn.setDescription("Click to remove");
        if (ClientServiceImpl.getAllTags().containsKey(token.getMessage().toLowerCase())) {
            btn.setDescription("Click to remove");
        } else {
            btn.addStyleName(ValoTheme.BUTTON_DANGER);
        }
        return btn;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        suggestField.setEnabled(enabled);
    }
}
