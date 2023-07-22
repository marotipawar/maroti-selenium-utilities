package com.maroti.base;

import com.maroti.util.web.Element;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.List;

public class BaseClass implements Element {

    public static WebDriver driver;
    private static WebDriverWait wait;
    public static WebDriver initialize() throws URISyntaxException, IOException {
        driver=WebDriverManager.initialize();
        wait= new WebDriverWait(driver, Duration.ofSeconds(40));
        return driver;
    }

    @Override
    public WebElement waitVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    @Override
    public WebElement waitElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    @Override
    public boolean isTitle(String title) {
        return wait.until(ExpectedConditions.titleIs(title));
    }

    @Override
    public Alert isPresent() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    @Override
    public List<WebElement> waitVisibilityList(List<WebElement> list) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(list));
    }

}
