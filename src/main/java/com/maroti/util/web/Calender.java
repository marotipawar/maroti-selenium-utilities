package com.maroti.util.web;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface Calender {
    public static void handle(WebElement monthElement, List<WebElement> days, WebElement next, String year, String month, String day) throws InterruptedException {
       while(true) {
           if (monthElement.getText().contains(month + " " + year)) {
               for (WebElement dayWb : days) {
                   System.out.println("Days :" + dayWb.getText());
                   if (dayWb.getText().contains(day)) {
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
