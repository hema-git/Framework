package com.xero.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.xero.utilitites.Helper;
import com.xero.utilitites.TestUtil;

import java.util.concurrent.TimeUnit;

//import edu.emory.mathcs.backport.java.util.concurrent.TimeUnit;

public class TestXeroBase {
	
	public static WebDriver driver;
	public static Properties prop;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static ExtentTest parentLogger;
	public static ExtentTest childLogger;
	
	public TestXeroBase() {
		
		try {
			
			prop=new Properties();
			//FileInputStream ip=new FileInputStream("C:\\hema\\Selenium\\MavenProject\\Xero\\XeroTest2\\src\\main\\java\\com\\xero\\config\\config.properties");
			FileInputStream ip=new FileInputStream("C:\\HemaGit\\GitRepos\\XeroFramework\\Framework\\XeroTest2\\src\\main\\java\\com\\xero\\config\\config.properties");
			prop.load(ip);
			
		}catch(FileNotFoundException fnf) {
			fnf.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}	
	}
	
	
	public static void initialization() {
		String browserName=prop.getProperty("browser");
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\srinic\\.m2\\repository\\webdriver\\chromedriver\\win32\\80.0.3987.106\\chromedriver.exe");
			driver=new ChromeDriver();
		}else if(browserName.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\srinic\\.m2\\repository\\webdriver\\geckodriver\\win64\\0.26.0\\geckodriver.exe");
			driver=new FirefoxDriver();
			
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT,TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
		
		
	}
	
	@BeforeSuite
	public static void createReport() {
		ExtentHtmlReporter extent=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"\\Reports\\Xero_"+Helper.getCurrentDateTime()+".html"));
		report=new ExtentReports();
		extent.config().setDocumentTitle("TestDocument");
		extent.config().setReportName("HomepageLoginSuite");
		report.attachReporter(extent);
		
	}
	
	@BeforeClass
	public void newTest() {
		
		logger=report.createTest(getClass().getSimpleName());
	}

}
