/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.common.ui;

import com.vaadin.data.Property;
import com.vaadin.data.Property.ReadOnlyException;
import com.vaadin.data.util.PropertyFormatter;
import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.ui.TextField;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 *
 * @author ODIN NGUYEN
 */
public class CurrencyTextField extends TextField {

    PropertyFormatter formatter;
    Property property;

    public CurrencyTextField() {
        property = new Property() {
            private BigDecimal value;

            @Override
            public Object getValue() {
                return value;
            }

            @Override
            public void setValue(Object newValue) throws ReadOnlyException,
                    ConversionException {
                if (newValue == null) {
                    value = null;
                } else if (newValue instanceof BigDecimal) {
                    value = (BigDecimal) newValue;
                } else {
                    throw new ConversionException();
                }
            }

            public Class<?> getType() {
                return BigDecimal.class;
            }

            public boolean isReadOnly() {
                return false;
            }

            public void setReadOnly(boolean newStatus) {
                // ignore
            }
        };

        formatter = new PropertyFormatter(property) {

            private final DecimalFormat df = new DecimalFormat("#,##0.00",
                    new DecimalFormatSymbols(new Locale("en", "UK")));

            {
                df.setParseBigDecimal(true);
            }

            @Override
            public String format(Object value) {

                final String retVal;
                if (value == null) {
                    retVal = "";
                } else {
                    retVal = df.format(value);
                }
                return retVal;
            }

            @Override
            public Object parse(String formattedValue) throws Exception {
                if (formattedValue != null
                        && formattedValue.trim().length() != 0) {
                    BigDecimal value = (BigDecimal) df.parse(formattedValue);
                    value = value.setScale(2, BigDecimal.ROUND_HALF_UP);
                    return value;
                }
                return null;
            }
        };
        setPropertyDataSource(formatter);
    }
}
