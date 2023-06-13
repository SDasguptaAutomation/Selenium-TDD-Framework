package com.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="input-email")
	private WebElement email;
	
	@FindBy(id="input-password")
	private WebElement password;
	
	@FindBy(xpath="//*[@type='submit']")
	private WebElement submitButton;
	
	public void enterCredential(String emailInput, String passwordInput)
	{
		email.sendKeys(emailInput);
		password.sendKeys(passwordInput);
		submitButton.click();
	}
	
}
