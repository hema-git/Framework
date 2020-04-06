package com.xero.Pages;

//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.xero.base.TestXeroBase;

public class XeroHomePage extends TestXeroBase {
	
	//Page factory objects of the main page or XeroHomePage Object repository
	
	@FindBy(linkText="Login")
	WebElement LoginBtn;
	
	@FindBy(xpath="//img[@class='global-ceiling-bar-logo']")
	WebElement xeroLogo;
	
	//Initializing the page objects
	//checking git push to see if this comment gets added
	public XeroHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	//Actions for the main page
	public String validateHomePageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateXeroImage() {
		return xeroLogo.isDisplayed();
	}
	
	public XeroLogin clickLoginBtn() {
		LoginBtn.click();
		return new XeroLogin();
	}
	
}
