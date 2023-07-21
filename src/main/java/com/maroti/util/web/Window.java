package com.maroti.util.web;

import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface Window {
    public static void switchTab(WebDriver driver, String parentWindow) {
        Set<String> list = driver.getWindowHandles();
        for (String tab : list) {
            if (tab.equals(parentWindow)) {
                driver.switchTo().window(tab);
            }

        }

    }

    public static void switchPreviousTab(WebDriver driver) {
        List<String> list = new ArrayList<>(driver.getWindowHandles());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(driver.getWindowHandle())) {
                driver.switchTo().window(list.get(i - 1));
                break;

            }
        }
    }

    public static void switchNextTab(WebDriver driver) {
        List<String> list = new ArrayList<>(driver.getWindowHandles());
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(driver.getWindowHandle())) {
                driver.switchTo().window(list.get(i + 1));
                break;

            }
        }
    }

    /**
     * @Author : Maroti Pawar
     */

    interface ScreenShot {


        public String getScreenShot(Object ...arg);

        public static String screenShot(WebDriver driver, String fileName) throws IOException {

            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);
            String destFile = fileName + "_" + generateDate() + ".png";
            return storeScreenShot(srcFile, destFile);
        }


        public static String storeScreenShot(File srcFile, String fileName) throws IOException {
            String path = System.getProperty("user.dir") + "/screenshots/";
            File dir = new File(path);
            if (!dir.isDirectory()) {
                dir.mkdir();
            }
            if (dir != null) {
                FileUtils.copyFile(srcFile, new File(dir, fileName));
                return dir.getAbsolutePath();
            }

            return null;
        }
        default String getScreenShotForReport(WebDriver driver, Status status, String fileName) throws IOException {
            return forReports(driver, status, fileName);
        }

        public static String forReports(WebDriver driver, Status status, String fileName) throws IOException {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);
            String destFileName = fileName + "_" + generateDate() + ".png";
            return store(srcFile, status, destFileName);
        }


        public static String store(File srcFile, Status status, String fileName) throws IOException {
            String pathPassScreenShot = System.getProperty("user.dir") + "/app/build/passScreenShot/";
            File createDirPassScreenShot = new File(pathPassScreenShot);

            boolean checkPassDir = createDirPassScreenShot.isDirectory();
            if (!checkPassDir) {
                createDirPassScreenShot.mkdir();
            }

            String pathFailedScreenShot = System.getProperty("user.dir") + "/app/build/failedScreenShot/";
            File createDirFailedScreenShot = new File(pathFailedScreenShot);

            boolean checkFailedDir = createDirFailedScreenShot.isDirectory();
            if (!checkFailedDir) {
                createDirFailedScreenShot.mkdir();
            }

            if (status.equals(Status.PASS)) {
                FileUtils.copyFile(srcFile, new File(createDirPassScreenShot, fileName));
                return "/app/build/passScreenShot/" + fileName;
            } else if (status.equals(Status.FAIL)) {
                FileUtils.copyFile(srcFile, new File(createDirFailedScreenShot, fileName));
                return "/app/build/failedScreenShot/"+ fileName;
            }
            return null;
        }

        public static String generateDate() {
            return new SimpleDateFormat("ddMMMyyyy_HHmm").format(new Date());
        }

    }
}
