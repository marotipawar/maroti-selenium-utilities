package com.maroti.base;

import com.maroti.util.file.PropertiesFile;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import static com.maroti.util.file.PropertiesFile.load;

/*
 * @Author : Maroti Pawar
 * */
@FunctionalInterface
public interface WebDriverManager {


    public WebDriver webDriver() throws URISyntaxException, IOException;


    public static WebDriver initialize() throws URISyntaxException, IOException {
        Properties props = load("driver.properties");
        String driver = (System.getProperty("driver.name") != null) ? System.getProperty("driver.name") : props.getProperty("driver.name");
        String url = (System.getProperty("driver.url") != null) ? System.getProperty("driver.url") : props.getProperty("driver.url");
        String implicitTime = (System.getProperty("driver.implicit.time") != null) ? System.getProperty("driver.implicit.time") : props.getProperty("driver.implicit.time");
        String pageLoadTime = (System.getProperty("driver.pageLoad.time") != null) ? System.getProperty("driver.pageLoad.time") : props.getProperty("driver.pageLoad.time");
        String driverName = driver.toUpperCase();
        long impTime = 30;
        if (implicitTime != null) {
            impTime = Long.parseLong(implicitTime);
        }
        long pageTime = 30;

        if (pageLoadTime != null) {
            pageTime = Long.parseLong(pageLoadTime);
        }


        return switch (driverName) {
            case "CHROME" -> chrome(url, impTime, pageTime);
            case "FIREFOX" -> firefox(url, impTime, pageTime);
            case "EDGE" -> edge(url, impTime, pageTime);
            case "CHROMEHEADLESS" -> chromeHeadless(url, impTime, pageTime);
            case "FIREFOXHEADLESS" -> firefoxHeadless(url, impTime, pageTime);
            case "EDGEHEADLESS" -> edgeHeadless(url, impTime, pageTime);
            case "CHROMEINCOGNITO" -> chromeIncognito(url, impTime, pageTime);
            case "FOREFOXINCOGNITO" -> firefoxIncognito(url, impTime, pageTime);
            case "EDGEINCOGNITO" -> edgeIncognito(url, impTime, pageTime);
            default -> null;
        };
    }


    public static WebDriver chrome(String url, long implicitlyTime, long pageLoadTime) {
        WebDriver driver = new ChromeDriver();
        init(driver, url, implicitlyTime, pageLoadTime);
        return driver;
    }

    public static WebDriver chromeHeadless(String url, long implicitlyTime, long pageLoadTime) {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--headless");
        WebDriver driver = new ChromeDriver(opt);
        init(driver, url, implicitlyTime, pageLoadTime);
        return driver;
    }

    public static WebDriver chromeIncognito(String url, long implicitlyTime, long pageLoadTime) {
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("incognito");
        WebDriver driver = new ChromeDriver(opt);
        init(driver, url, implicitlyTime, pageLoadTime);
        return driver;
    }

    public static WebDriver firefox(String url, long implicitlyTime, long pageLoadTime) {

        WebDriver driver = new FirefoxDriver();
        init(driver, url, implicitlyTime, pageLoadTime);
        return driver;
    }

    public static WebDriver firefoxHeadless(String url, long implicitlyTime, long pageLoadTime) {
        FirefoxOptions opt = new FirefoxOptions();
        opt.addArguments("headless");
        WebDriver driver = new FirefoxDriver(opt);
        init(driver, url, implicitlyTime, pageLoadTime);
        return driver;
    }

    public static WebDriver firefoxIncognito(String url, long implicitlyTime, long pageLoadTime) {
        FirefoxOptions opt = new FirefoxOptions();
        opt.addArguments("incognito");
        WebDriver driver = new FirefoxDriver(opt);
        init(driver, url, implicitlyTime, pageLoadTime);
        return driver;
    }

    public static WebDriver edge(String url, long implicitlyTime, long pageLoadTime) {
        WebDriver driver = new EdgeDriver();
        init(driver, url, implicitlyTime, pageLoadTime);
        return driver;
    }

    public static WebDriver edgeHeadless(String url, long implicitlyTime, long pageLoadTime) {
        EdgeOptions opt = new EdgeOptions();
        opt.addArguments("--headless=chrome");
        WebDriver driver = new EdgeDriver();
        init(driver, url, implicitlyTime, pageLoadTime);
        return driver;
    }

    public static WebDriver edgeIncognito(String url, long implicitlyTime, long pageLoadTime) {
        EdgeOptions opt = new EdgeOptions();
        opt.addArguments("incognito");
        WebDriver driver = new EdgeDriver();
        init(driver, url, implicitlyTime, pageLoadTime);
        return driver;
    }

    public static void init(WebDriver driver, String url, long implicitlyTime, long pageLoadTime) {
        if (driver != null) {
            driver.get(url);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyTime));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTime));
            driver.manage().deleteAllCookies();
        }

    }


}
