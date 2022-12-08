package com.celtic.automation.cmcs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.celtic.automation.cmcs.util.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	
	@FindBy(xpath="//input[@id='UserName']") WebElement loginUserId;  
	@FindBy(xpath="//input[@id='Password']") WebElement loginPassword;
	@FindBy(xpath="//input[@id='btnlogin']") WebElement loginbtnLogin;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	public void enterUsername(String username) throws Exception {
		ElementUtil.webEditTxtChange(loginUserId,username);
	}
	public void enterPassword(String password) {
		ElementUtil.webEditTxtChange(loginPassword,password);
	}
	public void clickLoginBtn() throws InterruptedException {
		ElementUtil.clickElementUsingActions(loginbtnLogin);
	}
}
