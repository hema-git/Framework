package com.xero.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.xero.base.TestXeroBase;

public class XeroLogin extends TestXeroBase {

	@FindBy(xpath="//h2[@class='x-boxed noBorder']")
	WebElement welcomeText;
	
	@FindBy(className="logo")
	WebElement loginPageLogo;
	
	@FindBy(id="email")
	WebElement userEmail;
	
	@FindBy(id="password")
	WebElement password;
	
	//initialization for the xerologin page
	public XeroLogin() {
		PageFactory.initElements(driver,this);
	}
	
	//Actions for the xerologin page
	
	public String validateLoginPageWelcomeMessage() {
		return welcomeText.getText();
	}
	
	public boolean validateLoginPageLogo() {
		return loginPageLogo.isDisplayed();
	}
	
	public boolean login(String uEmail,String pwd) {
		userEmail.sendKeys(uEmail);
		password.sendKeys(pwd);
		boolean flag;
		if(userEmail.getAttribute("value").equals("hello") && password.getAttribute("value").equals("pwd123")){
			flag=true;
		}else {
			flag=false;
		}
		return flag;
	}
	
	
	
	
	

}
