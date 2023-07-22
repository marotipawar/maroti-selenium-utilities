package com.maroti.base;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.URISyntaxException;

public class BaseClass {

    public static WebDriver driver;

    public static WebDriver initialize() throws URISyntaxException, IOException {
        return WebDriverManager.initialize();
    }

}
