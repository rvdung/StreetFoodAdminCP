/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dungnv.utils;

import com.vaadin.server.VaadinSession;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author ODIN NGUYEN
 */
public class BundleUtils {

    private static ResourceBundle rsConfig = null;

    public static String getResourceConfig(String key, Locale... locale) {
        Locale mlocale = (Locale) VaadinSession.getCurrent().getSession().getAttribute("locale");
        if (mlocale == null) {
            Locale viVN = new Locale("vi-VN");
            VaadinSession.getCurrent().getSession().setAttribute("locale", viVN);
            mlocale = viVN;
        }
        try {
            if (locale != null) {
                if (locale.length == 0) {
                    rsConfig = ResourceBundle.getBundle("resourceConfig", mlocale);
                } else {
                    rsConfig = ResourceBundle.getBundle("resourceConfig", locale[0]);
                }
            } else {
                rsConfig = ResourceBundle.getBundle("resourceConfig", mlocale);
            }

            return rsConfig.getString(key);
        } catch (Exception e) {
            return key;
        }
    }

    public static String getResourceConfig(String key, String defaultKey, Locale... locale) {
        Locale mlocale = (Locale) VaadinSession.getCurrent().getSession().getAttribute("locale");
        if (mlocale == null) {
            Locale viVN = new Locale("vi-VN");
            VaadinSession.getCurrent().getSession().setAttribute("locale", viVN);
            mlocale = viVN;
        }
        try {
            if (locale != null) {
                if (locale.length == 0) {
                    rsConfig = ResourceBundle.getBundle("resourceConfig", mlocale);
                } else {
                    rsConfig = ResourceBundle.getBundle("resourceConfig", locale[0]);
                }
            } else {
                rsConfig = ResourceBundle.getBundle("resourceConfig", mlocale);
            }

            return rsConfig.getString(key);
        } catch (Exception e) {
            return defaultKey == null ? null : defaultKey;
        }
    }

}
