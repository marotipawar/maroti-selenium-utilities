package com.maroti.util.web;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface DropDown {

    public static void handle(List<WebElement> list, String value) {
        for (WebElement wb : list) {
            if (wb.getText().contains(value)) {
                wb.click();
                break;
            }
        }
    }

}
