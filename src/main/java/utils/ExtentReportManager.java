package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    private static ExtentReports extent;
    public static ExtentReports getReport() {
        if (extent == null) {
        ExtentSparkReporter spark =new ExtentSparkReporter("reports/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Project", "Selenium Automation Framework");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Rohan");
        }
        return extent;
    }
}