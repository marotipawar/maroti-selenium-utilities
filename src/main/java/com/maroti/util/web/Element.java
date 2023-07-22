package com.maroti.util.web;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface Element {

    public WebElement waitVisibility(WebElement element);
    public List<WebElement> waitVisibilityList(List<WebElement> list);
    public WebElement waitElementToBeClickable(WebElement element);
    public Alert isPresent();
    public boolean isTitle(String title);

    default void click(WebElement element) {
        waitVisibility(element).click();
    }

    default void sendText(WebElement element, String value){
        waitVisibility(element).sendKeys(value);
    }

    default String readText(WebElement element){
       return waitVisibility(element).getText();
    }
}
