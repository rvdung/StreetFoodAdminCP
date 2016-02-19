/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.utils;

import com.dungnv.streetfood.dto.ResultDTO;
import org.vaadin.suggestfield.BeanSuggestionConverter;
import org.vaadin.suggestfield.client.SuggestFieldSuggestion;

/**
 *
 * @author pc
 */
public class DataSuggestionConverter extends BeanSuggestionConverter {

    public DataSuggestionConverter() {
        super(ResultDTO.class, "id", "message", "key");
    }

    @Override
    public Object toItem(SuggestFieldSuggestion suggestion) {
        ResultDTO result = null;
        result = new ResultDTO();
        result.setId(suggestion.getId());
        result.setMessage(suggestion.getDisplayString());
        result.setKey(suggestion.getReplacementString());
        assert result != null : "This should not be happening";
        return result;
    }
}
