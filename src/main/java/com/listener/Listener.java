package com.listener;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.base.Base;


public class Listener extends Base implements ITestListener  {

	ExtentReports reports;
	ExtentTest test;

	public void onTestStart(ITestResult result) {
		test = reports.createTest(result.getName());
	}


	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, MarkupHelper.createLabel("Test Case Executed Successfully", ExtentColor.GREEN));
	}


	public void onTestFailure(ITestResult result) {
		try 
		{
			String screenshotpath = screenshot();
			test.log(Status.FAIL, MarkupHelper.createLabel("Test Case Execution Failed", ExtentColor.RED));
			test.log(Status.FAIL, result.getThrowable());
			test.fail("Error Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotpath).build());
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	public void onTestSkipped(ITestResult result) {
		test.skip(MarkupHelper.createLabel("Test Case Skipped from Execution", ExtentColor.ORANGE));
	}


	public void onStart(ITestContext context) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("D:\\MyAutomation\\TestAutomation\\DemoECommerce\\test-output\\ExtentReport\\Report-"+System.currentTimeMillis()+".html");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setReportName("Automation Test Report");
		htmlReporter.config().setEncoding("UTF-8");
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		reports.setSystemInfo("OS", "Windows 10");
		reports.setSystemInfo("Author", "Sayan Dasgupta");
		reports.setSystemInfo("Application", "Demo ECommerce");
		
	}


	public void onFinish(ITestContext context) {
		reports.flush();
	}
}
