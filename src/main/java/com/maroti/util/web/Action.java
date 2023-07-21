package com.maroti.util.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/*
 * @Author : Maroti Pawar
 * */
@FunctionalInterface
public interface Action {

    public void actions(Object... arg);

    public static Actions getInstance(WebDriver driver) {
        return new Actions(driver);
    }

    public static void click(WebDriver driver, WebElement element) {
        getInstance(driver).click(element).build().perform();
    }

    public static void sendKeys(WebDriver driver, WebElement element, String value) {
        getInstance(driver).sendKeys(element, value).build().perform();
    }

    public static void moveToElement(WebDriver driver, WebElement element) {
        getInstance(driver).moveToElement(element).build().perform();
    }

    public static void clickAndHold(WebDriver driver, WebElement element) {
        getInstance(driver).clickAndHold(element).build().perform();
    }

    public static void contextClick(WebDriver driver, WebElement element) {
        getInstance(driver).contextClick(element).build().perform();
    }

    public static void doubleClick(WebDriver driver, WebElement element) {
        getInstance(driver).doubleClick(element).build().perform();
    }

    public static void dragANdDrop(WebDriver driver, WebElement source, WebElement target) {
        getInstance(driver).dragAndDrop(source, target).build().perform();
    }

    public static void keyUp(WebDriver driver, WebElement element, String value) {
        getInstance(driver).keyUp(element, value).build().perform();
    }

    public static void keyUp(WebDriver driver, String value) {
        getInstance(driver).keyUp(value).build().perform();
    }

    public static void keyDown(WebDriver driver, WebElement element, String value) {
        getInstance(driver).keyDown(element, value).build().perform();
    }

    public static void keyDown(WebDriver driver, String value) {
        getInstance(driver).keyDown(value).build().perform();
    }

    public static void release(WebDriver driver, WebElement element) {
        getInstance(driver).release(element).build().perform();
    }

    public static void pause(WebDriver driver, int second) {
        getInstance(driver).pause(second).build().perform();
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        getInstance(driver).scrollToElement(element).build().perform();
    }
}
