package com.maroti;

import com.maroti.base.BaseClass;
import com.maroti.util.web.Calender;
import com.maroti.util.web.DropDown;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class App {
    @FindBy(xpath = "//div[@class='input-icons']/i")
    private WebElement clickDate;
    @FindBy(xpath = "//span[@id='spanMonth']")
    private WebElement clickMonth;
    @FindBy(xpath = "//div[@id='selectMonth']//td")
    private List<WebElement> selectMonth;

    @FindBy(xpath = "//span[@id='spanYear']")
    private WebElement clickYear;
    @FindBy(xpath = "//div[@id='selectYear']//td")
    private List<WebElement> selectYear;

    @FindBy(xpath = "//span[@id='CalContent']//tr//td/a")
    private List<WebElement> selectDay;


    App() throws URISyntaxException, IOException {
        WebDriver driver = BaseClass.initialize();
        PageFactory.initElements(driver, this);
    }

    public void book() throws InterruptedException {
        clickDate.click();
        clickMonth.click();
        DropDown.handle(selectMonth, "March");
        clickYear.click();
        DropDown.handle(selectYear, "2022");
        DropDown.handle(selectDay, "5");


    }


    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        App app = new App();
        app.book();

    }
}









  /*  @FindBy(xpath = "//span[@class='sc-12foipm-38 fXmuXi']/following::input")
    private WebElement enterCityName;
    @FindBy(xpath = "//div[@class='sc-12foipm-36 fWVTLG']/ul/li//p[1]/span[1]")
    private List<WebElement> cityList;

    @FindBy(xpath = "//div[@class='DayPicker-Caption']/div")
    private List<WebElement> monthList;

    @FindBy(xpath = "//div[@class='DayPicker-Body']//div/p[1]")
    private List<WebElement> days;

    @FindBy(xpath = "//div[@class='DayPicker-NavBar']/span[2]")
    private WebElement next;*/