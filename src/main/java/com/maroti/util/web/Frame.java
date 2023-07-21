package com.maroti.util.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/*
 * @Author : Maroti Pawar
 * */
@FunctionalInterface
public interface Frame {

    public void to(Object ...obj);
    public static void to(WebDriver driver, int index) {
        driver.switchTo().frame(index);
    }

    public static void to(WebDriver driver, String nameOrId) {
        driver.switchTo().frame(nameOrId);
    }

    public static void to(WebDriver driver, WebElement element) {
        driver.switchTo().frame(element);
    }

    public static int size(WebDriver driver) {
        return driver.findElements(By.tagName("iframe")).size();
    }

    public static List<WebElement> getFrameList(WebDriver driver) {
        return driver.findElements(By.tagName("iframe"));
    }

}
