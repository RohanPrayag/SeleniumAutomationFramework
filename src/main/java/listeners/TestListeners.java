package listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;
import utils.ExtentReportManager;
import utils.ScreenshotUtils;

public class TestListeners implements ITestListener {

    private ExtentReports extent = ExtentReportManager.getReport();

    private ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {

        test = extent.createTest(result.getName());

        System.out.println("TEST STARTED: " + result.getName());
    }
    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("Test passed");
        System.out.println("TEST PASSED: " + result.getName());
        extent.flush();
    }
    @Override
    public void onTestFailure(ITestResult result) {
        test.fail(result.getThrowable());
        System.out.println("TEST FAILED: "+ result.getName());
        BaseClass base = (BaseClass)result.getInstance();
        String path = ScreenshotUtils.takeScreenshot(base.driver,result.getName());
        try {
            test.addScreenCaptureFromPath(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        extent.flush();
    }
}