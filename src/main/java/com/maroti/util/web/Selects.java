package com.maroti.util.web;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


import java.util.List;

/*
 * @Author : Maroti Pawar
 * */
@FunctionalInterface
public interface Selects {

    public Selects select(WebElement element);
    public static List<WebElement> optionList(WebElement element) {
        return new Select(element).getOptions();
    }

    public static Select optionByText(WebElement element, String text) {
       Select select = new Select(element);
        select.selectByVisibleText(text);
        return select;
    }

    public static Select optionByValue(WebElement element, String value) {
       Select select = new Select(element);
        select.selectByValue(value);
        return select;
    }

    public static Select optionByIndex(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
        return select;
    }

    public static String optionText(Select select, String text) {
        return select.getFirstSelectedOption().getText();
    }


}
