package com.maroti.util.web;

import com.aventstack.extentreports.Status;
import com.maroti.util.file.PropertiesFile;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * @Author : Maroti Pawar
 */

@FunctionalInterface
public interface Screenshot {
    public String getScreenShot(Object... arg);

    public static void screenShot(WebDriver driver, String fileName) throws IOException, URISyntaxException {
        Properties props = PropertiesFile.load("screenshot.properties");
        String output = null;
        String fileFormat = null;
        String dateFormat = null;
        if (props != null) {
            output = props.getProperty("screenshot.output");
            if (output == null) {
                output = "screenshot";
            }
            fileFormat = props.getProperty("screenshot.file.format");
            if (fileFormat == null) {
                fileFormat = "jpg";
            }
            dateFormat = props.getProperty("screenshot.date.format");
            if (dateFormat == null) {
                dateFormat = "ddMMMyyy_HHmm";
            }
        } else {
            props = new Properties();
            props.setProperty("screenshot.output", "screenshot");
            props.setProperty("screenshot.file.format", ".png");
            props.setProperty("screenshot.date.format", "ddMMMyyyy_HHmm");
            output = props.getProperty("screenshot.output");
            fileFormat = props.getProperty("screenshot.file.format");
            dateFormat = props.getProperty("screenshot.date.format");
        }
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        String destFile = fileName + "_" + generateDate(dateFormat) + "." + fileFormat;
        storeScreenShot(srcFile, destFile, output);
    }


    public static void storeScreenShot(File srcFile, String fileName, String output) throws IOException {
        String path = System.getProperty("user.dir") + "\\" + output + "\\";
        File dir = new File(path);
        if (!dir.isDirectory()) {
            dir.mkdir();
        }
        FileUtils.copyFile(srcFile, new File(dir, fileName));
    }


    public static String forReports(WebDriver driver, Status status, String fileName) throws IOException, URISyntaxException {
        Properties props = PropertiesFile.load("screenshot.properties");
        String output = null;
        String pass = null;
        String fail = null;
        String fileFormat = null;
        String dateFormat = null;
        if (props != null) {
            output = props.getProperty("screenshot.output");
            if (output == null) {
                output = "screenshot";
            }
            pass = props.getProperty("screenshot.output.pass");
            if (pass == null) {
                pass = "pass";
            }
            fail = props.getProperty("screenshot.output.fail");
            if (fail == null) {
                fail = "fail";
            }
            fileFormat = props.getProperty("screenshot.file.format");
            if (fileFormat == null) {
                fileFormat = "jpg";
            }
            dateFormat = props.getProperty("screenshot.date.format");
            if (dateFormat == null) {
                dateFormat = "ddMMMyyy_HHmm";
            }
        } else {
            props = new Properties();
            props.setProperty("screenshot.output", "screenshot");
            props.setProperty("screenshot.output.pass", "pass");
            props.setProperty("screenshot.output.fail", "fail");
            props.setProperty("screenshot.file.format", "png");
            props.setProperty("screenshot.date.format", "ddMMMyyyy_HHmm");
            output = props.getProperty("screenshot.output");
            pass = props.getProperty("screenshot.output.pass");
            fail = props.getProperty("screenshot.output.fail");
            fileFormat = props.getProperty("screenshot.file.format");
            dateFormat = props.getProperty("screenshot.date.format");
        }
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);
        String destFileName = fileName + "_" + generateDate(dateFormat) + "." + fileFormat;
        return store(srcFile, status, destFileName, output, pass, fail);
    }


    public static String store(File srcFile, Status status, String fileName, String mainDir, String passDir, String failDir) throws IOException {
        String pathPassScreenShot = System.getProperty("user.dir") + "\\" + mainDir + "\\" + passDir + "\\";
        File createDirPassScreenShot = new File(pathPassScreenShot);

        boolean checkPassDir = createDirPassScreenShot.isDirectory();
        if (!checkPassDir) {
            createDirPassScreenShot.mkdir();
        }

        String pathFailedScreenShot = System.getProperty("user.dir") + "\\" + mainDir + "\\" + failDir + "\\";
        File createDirFailedScreenShot = new File(pathFailedScreenShot);

        boolean checkFailedDir = createDirFailedScreenShot.isDirectory();
        if (!checkFailedDir) {
            createDirFailedScreenShot.mkdir();
        }

        if (status.equals(Status.PASS)) {
            FileUtils.copyFile(srcFile, new File(createDirPassScreenShot, fileName));
            return "/" + mainDir + "/" + passDir + "/" + fileName;
        } else if (status.equals(Status.FAIL)) {
            FileUtils.copyFile(srcFile, new File(createDirFailedScreenShot, fileName));
            return "/" + mainDir + "/" + failDir + "/" + fileName;
        }
        return null;
    }

    public static String generateDate(String dateFormat) {
        return new SimpleDateFormat(dateFormat).format(new Date());
    }


}
