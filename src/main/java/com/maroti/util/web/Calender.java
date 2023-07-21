package com.maroti.util.web;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface Calender {
    public static void handle(List<WebElement> monthElement, List<WebElement> days, WebElement next, String year, String month, String day) throws InterruptedException {
        for (WebElement months : monthElement) {
            System.out.println(months.getText());
            if (months.getText().equals(month + " " + year)) {

                Thread.sleep(2000);
                for (WebElement dayWb : days) {
                    System.out.println("Days :"+dayWb.getText());
                    if (dayWb.getText().equals(day)) {
                        dayWb.click();
                        break;
                    }
                }

                break;
            }
            next.click();
        }
    }
}
