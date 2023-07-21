package com.maroti.util.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.maroti.model.Report;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * @Author : Maroti Pawar
 * */
public interface Extent {
    public static ExtentSparkReporter getSparkInstance(String documentTitle, String reportName) throws IOException {
        ExtentSparkReporter reporter = new ExtentSparkReporter(store(reportName));
        reporter.getConf().setReportName(reportName);
        reporter.getConf().setDocumentTitle(documentTitle);
        return reporter;
    }

    public static ExtentReports getExtentInstance(String documentTitle, String reportName) throws IOException {
        ExtentSparkReporter reporter = getSparkInstance(documentTitle, reportName);
        ExtentReports reports = new ExtentReports();
        reports.attachReporter(reporter);
        return reports;
    }


    public static File store(String fileName) throws IOException {

        String path = System.getProperty("user.dir") + "/app/build/";
        File file = new File(path + "/extentReport/");
        if (file.isDirectory()) {
            file.mkdir();
        }
        SimpleDateFormat sf = new SimpleDateFormat("ddMMMyyy_HHmm");
        String date = sf.format(new Date());
        return new File(file + "\\" + fileName + "_" + date + ".html");
    }


   /* public static void main(String[] args) throws IOException {

        WebDriver driver = BaseWebDriver.chrome("https://www.facebook.com");
        Report report1 = Report.builder()
                .documentTitle("Google")
                .reportName("Google Report")
                .testName("Search box")
                .status(Status.PASS)
                .path(store("google"))
                .build();
        createTest(report1).addScreenCaptureFromPath(ScreenShot.forReports(driver,Status.PASS,"google"));
        report1.getReports().flush();
    }*/

}
