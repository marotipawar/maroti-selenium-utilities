package com.maroti.util.listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.maroti.util.report.Extent;
import com.maroti.util.web.Screenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.net.URISyntaxException;

public class TestListener implements ITestListener {
    private WebDriver driver;
    private ExtentReports report;
    private ExtentTest test;


    @Override
    public void onStart(ITestContext context) {
        try {
            report = Extent.getExtentReport();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
         test= report.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, result.getMethod().getMethodName());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            String path = Screenshot.forReports(driver, Status.PASS, result.getMethod().getMethodName());
            test.addScreenCaptureFromPath(path);
        } catch (IllegalAccessException | NoSuchFieldException | URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, result.getThrowable());
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
            String path = Screenshot.forReports(driver, Status.FAIL, result.getMethod().getMethodName());
            test.addScreenCaptureFromPath(path);
        } catch (IllegalAccessException | NoSuchFieldException | URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, result.getMethod().getMethodName());
    }

    @Override
    public void onFinish(ITestContext context) {
        report.flush();
    }
}
