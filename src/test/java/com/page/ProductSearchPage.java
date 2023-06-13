package com.page;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductSearchPage {

	WebDriver driver;
	
	@FindBy(xpath="//input[@name='search']")
	WebElement productName;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	WebElement searchButton;

	@FindBy(xpath="//div[contains(@class, 'product-layout')]//h4/a")
	List<WebElement> productList;
	
	@FindBy(xpath="//*[@class='button-group']//*[text()='Add to Cart']")
	List<WebElement> addToCartButton;
	
	@FindBy(xpath="//div[contains(@class, 'alert')]")
	WebElement sucessMessage;
	
	public ProductSearchPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public List<WebElement> searchProduct(String product)
	{
		productName.sendKeys(product);
		searchButton.click();
		
		return productList;
	}
	
	public void addToCart(List<WebElement> products, String itemName)
	{
		for(int i=0; i<products.size(); i++)
		{
			if((products.get(i).getText()).trim().equalsIgnoreCase(itemName))
			{
				addToCartButton.get(i).click();
				break;
			}
			
		}
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		String message = wait.until(ExpectedConditions.visibilityOf(sucessMessage)).getText();

		Assert.assertTrue(message.contains("Success"));
	}
	
}
