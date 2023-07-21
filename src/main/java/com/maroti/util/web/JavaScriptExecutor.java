package com.maroti.util.web;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/*
 * @Author : Maroti Pawar
 *
 * */
@FunctionalInterface
public interface JavaScriptExecutor {
    public void javascript(Object obj);
    public static JavascriptExecutor getInstance(WebDriver driver) {
        return ((JavascriptExecutor) driver);
    }

    public static void writeScript(WebDriver driver, String script, WebElement element) {
        getInstance(driver).executeScript(script, element);
    }

    public static void click(WebDriver driver, WebElement element) {
        getInstance(driver).executeScript("arguments[0].click();", element);
    }


    public static void sendKeys(WebDriver driver, WebElement element, String value) {
        getInstance(driver).executeScript("arguments[0].value='" + value + "'", element);
    }

    public static void alert(WebDriver driver, String alertText) {
        getInstance(driver).executeScript("alert(" + alertText + ");");
    }

    public static String getText(WebDriver driver, WebElement element) {
        return getInstance(driver).executeScript("arguments[0].textContent;", element).toString();
    }

    public static String getTitle(WebDriver driver) {
        return getInstance(driver).executeScript("return document.title;").toString();
    }

    public static String getUrl(WebDriver driver) {
        return getInstance(driver).executeScript("return document.URL;").toString();
    }


    public static void scrollBy(WebDriver driver, int h, int v) {
        getInstance(driver).executeScript("window.scrollBy(" + h + "," + v + ");");
    }

    public static void scrollToBottom(WebDriver driver) {
        getInstance(driver).executeScript("window.scrollBy(0, document.body.scrollHeight;");
    }

    public static void navigateTo(WebDriver driver, String url) {
        getInstance(driver).executeScript("window.location='" + url + ";");
    }

    public static void navigateBack(WebDriver driver) {
        getInstance(driver).executeScript("window.history.back();");
    }

    public static void navigateForward(WebDriver driver) {
        getInstance(driver).executeScript("window.history.forward();");
    }

    public static void navigateRefresh(WebDriver driver) {
        getInstance(driver).executeScript("window.location.reload();");
    }

}
