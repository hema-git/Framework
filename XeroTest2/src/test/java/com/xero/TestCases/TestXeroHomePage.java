package com.xero.TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.xero.Pages.XeroHomePage;
import com.xero.Pages.XeroLogin;
import com.xero.base.TestXeroBase;

public class TestXeroHomePage extends TestXeroBase {
	XeroHomePage homePage;
	XeroLogin loginPage;
	
	public TestXeroHomePage() {
		super();
	}
	
//	@BeforeSuite
//	
//	public void setupReportHomePage() {
//		createReport();
//		logger=report.createTest("HomePage Login Test");
//		
//	}
	
//	@BeforeClass
//	public void setupReportHomePage() {
//		createReport();
//		logger=report.createTest("HomePage Test");
//	}
	
	@BeforeMethod
	public void setup() {
		//call from TestBase class
		initialization();
		homePage=new XeroHomePage();
		
	}
	
	@Test(description="this test verifies that the main page has been loaded")
	public void TC_verifyHomePageTitleTest() {
		logger.info("this test verifies that the main page has been loaded");
		String actualTitle=homePage.validateHomePageTitle();
		String expectedTitle="Accounting Software - Do Beautiful Business | Xero US";
		Assert.assertEquals(actualTitle, expectedTitle);
		logger.log(Status.PASS, "Mainpage title has been verified");
		
	}
	
	@Test(description="this test is going to verify the logo on the main page")
	public void TC_verifyHomePageImgTest() {
		logger.info("this test is going to verify the logo on the main page");
		boolean flag=homePage.validateXeroImage();
		Assert.assertTrue(flag);
		logger.log(Status.PASS, "Main page logo verified");
	}
	
	@Test(description="this test is going to check if the login btn lands on the login page")
	public void TC_verifyHomePageToLoginPageLandingTest() {
		logger.info("this test is going to check if the login btn lands on the login page");
		loginPage=homePage.clickLoginBtn();
		Assert.assertEquals(loginPage.validateLoginPageWelcomeMessage(),"Welcome to Xero" );
		logger.log(Status.PASS, "on the login page");
		
	}
	
	@AfterMethod
	public void tearDown() {
		report.flush();
		driver.quit();
	}
	
	

}
