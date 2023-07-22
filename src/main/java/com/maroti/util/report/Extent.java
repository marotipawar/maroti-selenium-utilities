package com.maroti.util.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.maroti.base.BaseClass;
import com.maroti.util.file.PropertiesFile;
import com.maroti.util.web.Screenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/*
 * @Author : Maroti Pawar
 * */
public interface Extent {
    public static ExtentSparkReporter getSparkInstance(String documentTitle, String reportName, String output, String dateFormat) throws IOException {
        ExtentSparkReporter reporter = new ExtentSparkReporter(store(reportName, output, dateFormat));
        reporter.config().setDocumentTitle(documentTitle);
        reporter.config().setReportName(reportName);
        return reporter;
    }

    public static ExtentReports getExtentReport() throws IOException, URISyntaxException {
        Properties props = PropertiesFile.load("extent.properties");
        String pageTitle = null;
        String repName = null;
        String output = null;
        String dateFormat=null;
        String applicationName = null;
        String environment = null;
        String platform = null;
        String author = null;

        if (props != null) {
            pageTitle = props.getProperty("extent.document-title");
            if (pageTitle == null) {
                pageTitle = "Test Application";

            }
            repName = props.getProperty("extent.report.name");
            if (repName == null) {
                repName = "My Report";
            }
            output = props.getProperty("extent.report.output");
            if (output == null) {
                output = "/target/extent-report/";
            }
            dateFormat=props.getProperty("date.format");
            if(dateFormat==null){
                dateFormat="ddMMMyyyy";
            }
            applicationName = props.getProperty("application");
            if (applicationName == null) {
                applicationName = "Application";
            }
            platform = props.getProperty("platform");
            if (platform == null) {
                platform = "MAC_OS";
            }
            author = props.getProperty("author");
            if (author == null) {
                author = "Maroti Pawar";
            }
            environment = props.getProperty("environment");
            if (environment == null) {
                environment = "QA";
            }
        } else {
            props = new Properties();
            props.setProperty("extent.document-title", "Test Application");
            props.setProperty("extent.report.name", "My Report");
            props.setProperty("extent.report.output", "/target/extent-report/");
            props.setProperty("application", "Application");
            props.setProperty("platform", "MAC_OS");
            props.setProperty("environment", "QA");
            props.setProperty("author", "Maroti Pawar");
            props.setProperty("date.format","ddMMMyyyy_HHmm");

            pageTitle = props.getProperty("extent.document-title");
            repName = props.getProperty("extent.report.name");
            output = props.getProperty("extent.report.output");
            dateFormat=props.getProperty("date.format");
            applicationName = props.getProperty("application");
            platform = props.getProperty("platform");
            author = props.getProperty("author");
            environment = props.getProperty("environment");
        }

        ExtentSparkReporter reporter = getSparkInstance(pageTitle, repName, output, dateFormat);
        ExtentReports reports = new ExtentReports();
        reports.setSystemInfo("Application Name", applicationName);
        reports.setSystemInfo("Platform", platform);
        reports.setSystemInfo("Environment", environment);
        reports.setSystemInfo("Test Engineer", author);
        reports.attachReporter(reporter);
        return reports;
    }


    public static File store(String fileName, String output, String dateFormat) throws IOException {

        String path = System.getProperty("user.dir") + output;
        File file = new File(path);
        if (file.isDirectory()) {
            file.mkdir();
        }
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        String date = sf.format(new Date());
        return new File(file + "\\" + fileName + "_" + date + ".html");
    }


    public static void main(String[] args) throws IOException, URISyntaxException {

        WebDriver driver = BaseClass.initialize();

        ExtentReports report = getExtentReport();
        ExtentTest test = report.createTest("Pass Test");
        test.log(Status.PASS, "Test Pass");
        test.addScreenCaptureFromPath(Screenshot.forReports(driver, Status.PASS,"testPass"));
        ExtentTest test1 = report.createTest("Failed Test");
        test1.log(Status.FAIL, "Test Fail");
        test1.addScreenCaptureFromPath(Screenshot.forReports(driver, Status.FAIL,"testFail"));
        System.out.println(Screenshot.forReports(driver, Status.PASS,"test"));
        System.out.println(Screenshot.forReports(driver, Status.FAIL,"test"));
        report.flush();
    }

}
