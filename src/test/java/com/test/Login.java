package com.test;

import org.testng.annotations.Test;
import com.base.Base;
import com.page.LoginPage;
import org.testng.annotations.BeforeTest;
import java.io.IOException;
import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;

public class Login extends Base {

	WebDriver driver;
	LoginPage loginPage;
	String userName;
	String passWord;

	ArrayList<String> arr = new ArrayList<>();
	
	@BeforeTest(groups = {"smoke", "regression"})
	public void setup(ITestContext context) throws IOException
	{
		driver = init_browser();
		driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
		loginPage = new LoginPage(driver);
		context.setAttribute("driver", driver);
		arr = getDatafromExcel("Login");
	}

	@Test(groups = {"smoke", "regression"})
	public void customerLogin() 
	{
		userName = arr.get(1);
		passWord = arr.get(2);

		loginPage.enterCredential(userName, passWord);
		
		Assert.assertEquals(driver.getTitle().trim(), "My Account");
	}


	@AfterTest
	public void afterTest() 
	{

	}

}
