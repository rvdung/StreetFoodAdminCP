package com.dungnv.view;

import com.dungnv.streetfood.view.ArticleView;
import com.dungnv.streetfood.view.CategoryView;
import com.dungnv.streetfood.view.DishView;
import com.dungnv.streetfood.view.RestaurantView;
import com.dungnv.streetfood.view.SlideShowView;
import com.dungnv.streetfood.view.UserView;
import com.dungnv.utils.BundleUtils;
import com.dungnv.view.dashboard.DashboardView;
import com.dungnv.view.reports.ReportsView;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;

public enum DashboardViewType {
    DASHBOARD("dashboard", DashboardView.class, FontAwesome.HOME, true)//
    , SLIDESHOW(BundleUtils.getLanguage("menu.SLIDESHOW"), SlideShowView.class, FontAwesome.TABLE, false)//
    , CATEGORY(BundleUtils.getLanguage("menu.CATEGORY"), CategoryView.class, FontAwesome.BAR_CHART_O, true)//
    , DISH(BundleUtils.getLanguage("menu.DISH"), DishView.class, FontAwesome.FILE_TEXT_O, true)//
    , RESTAURANT(BundleUtils.getLanguage("menu.RESTAURANT"), RestaurantView.class, FontAwesome.FILE_TEXT_O, true)//
    , ARTICLE(BundleUtils.getLanguage("menu.ARTICLE"), ArticleView.class, FontAwesome.CALENDAR_O, false)//
    , USERS(BundleUtils.getLanguage("menu.USERS"), UserView.class, FontAwesome.CALENDAR_O, false)//
     , REPORTS(BundleUtils.getLanguage("menu.REPORTS"), ReportsView.class, FontAwesome.FILE_TEXT_O, true);
//    DASHBOARD("dashboard", DashboardView.class, FontAwesome.HOME, true)
//    , SALES("sales", SalesView.class, FontAwesome.BAR_CHART_O, false)
//    , TRANSACTIONS("transactions", TransactionsView.class, FontAwesome.TABLE, false)
//    , REPORTS("reports", ReportsView.class, FontAwesome.FILE_TEXT_O, true)
//    , REPORTS2("reports_2", ReportsView.class, FontAwesome.FILE_TEXT_O, true)
//    , SCHEDULE("schedule", ScheduleView.class, FontAwesome.CALENDAR_O, false);

    private final String viewName;
    private final Class<? extends View> viewClass;
    private final Resource icon;
    private final boolean stateful;

    private DashboardViewType(final String viewName,
            final Class<? extends View> viewClass, final Resource icon,
            final boolean stateful) {
        this.viewName = viewName;
        this.viewClass = viewClass;
        this.icon = icon;
        this.stateful = stateful;
    }

    public boolean isStateful() {
        return stateful;
    }

    public String getViewName() {
        return viewName;
    }

    public Class<? extends View> getViewClass() {
        return viewClass;
    }

    public Resource getIcon() {
        return icon;
    }

    public static DashboardViewType getByViewName(final String viewName) {
        DashboardViewType result = null;
        for (DashboardViewType viewType : values()) {
            if (viewType.getViewName().equals(viewName)) {
                result = viewType;
                break;
            }
        }
        return result;
    }

}
