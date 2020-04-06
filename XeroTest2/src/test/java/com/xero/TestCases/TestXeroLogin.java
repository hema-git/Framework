package com.xero.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.xero.Pages.XeroHomePage;
import com.xero.Pages.XeroLogin;
import com.xero.base.TestXeroBase;
import com.xero.utilitites.ExcelData;
import com.xero.utilitites.Helper;

public class TestXeroLogin extends TestXeroBase {
	XeroHomePage xeroHome;
	XeroLogin xeroLoginObj;
	ExcelData getExcelData=new ExcelData();
	
	public TestXeroLogin() {
		super();
	}
	
//	@BeforeSuite
//	public void setupReportLoginPage() {
//		createReport();
//		logger=report.createTest("Login to Xero");
//	}
	
//	@BeforeClass
//	public void setupReportLoginPage() {
//		createReport();
//		logger=report.createTest("login to xero");
//	}
	@BeforeMethod
	public void setup() {
		//call from TestBase class
		initialization();
		xeroHome=new XeroHomePage();
		xeroLoginObj=xeroHome.clickLoginBtn();
		
	}
	
	//removing priorities because order of tests in both the classes are getting mixed up
	@Test(description="this tests verifies the welcome message")
	public void TC_verifyLoginPageWelcomeMessageTest() {
		String welcomeMsg=xeroLoginObj.validateLoginPageWelcomeMessage();
		Assert.assertEquals(welcomeMsg, "Welcome to zero");
		logger.log(Status.PASS, "Xero application login page welcome message is displayed");
	}
	
	@Test(description="this test verifies the presence of the xero logo")
	public void TC_verifyLoginPageLogoTest() {
		Assert.assertTrue(xeroLoginObj.validateLoginPageLogo());
		logger.log(Status.PASS, "Xero application login page logo is displayed");
	}
	
	@Test(description="check if username and password from excel file has been entered")
	public void TC_verifyLoginCredentialsTest() {
		boolean rightCredentials;
		//prop.getProperty("username"));prop.getProperty("password"));
		
		rightCredentials=xeroLoginObj.login(getExcelData.getUserName("Login", 0, 0), getExcelData.getUserName("Login", 0, 1));
		if(rightCredentials) {
			System.out.println("yes right credentials");
			logger.log(Status.PASS,"Right credentials were entered");
		}else {
			System.out.println("wrong credentials");
			logger.log(Status.PASS, "Incorrect credentials entered");
		}
	}
	
	@AfterMethod
	public void getScreenshot(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE) {
			Helper.captureScreenshot(driver);
			logger.fail("Test Failed",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
//		else if(result.getStatus()==ITestResult.SUCCESS) {
//			logger.pass("Test passed",MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
//		}
		report.flush();
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
	}

}
