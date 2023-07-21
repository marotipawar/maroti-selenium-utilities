package com.maroti.model;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

/*
*
* @Author : Maroti Pawar
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {
    private File path;
    private String reportName;
    private String documentTitle;
    private String testName;
    private Status status;
    private ExtentReports reports;

}
