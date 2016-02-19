package com.dungnv.streetfood.tb.pageobjects;

import org.openqa.selenium.WebDriver;

import com.dungnv.view.reports.ReportsView;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.WindowElement;
import org.junit.Test;

public class TBConfirmDialog extends TestBenchTestCase {

    private final WindowElement scope;

    public TBConfirmDialog(WebDriver driver) {
        setDriver(driver);
        scope = $(WindowElement.class).id(ReportsView.CONFIRM_DIALOG_ID);
    }
    
    @Test
    public void discard() {
        $(ButtonElement.class).caption("Discard Changes").first().click();
    }

}
