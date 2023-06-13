package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.base.Base;
import com.page.ProductSearchPage;


public class ProductSearch extends Base {

	WebDriver driver;
	ProductSearchPage productSearchPage;
	String searchByItem;
	String itemName;
	
	ArrayList<String> li = new ArrayList<>();
	
	@BeforeClass(groups = {"regression"})
	public void setup(ITestContext context) throws IOException
	{
		driver = (WebDriver) context.getAttribute("driver");
		productSearchPage = new ProductSearchPage(driver);
		
		li = getDatafromExcel("Product Search");
	}
	
	@Test(priority = 0, dependsOnMethods = {"customerLogin"}, groups = {"regression"})
	public List<WebElement> searchProduct()
	{
		searchByItem = li.get(1);
		List<WebElement> li = productSearchPage.searchProduct(searchByItem);
		return li;
	}
	
	@Test(priority = 1, groups = {"regression"})
	public void addToCart() throws InterruptedException
	{
		itemName = li.get(2);
		
		List<WebElement> products = searchProduct();
		
		productSearchPage.addToCart(products, itemName);
	}
}
