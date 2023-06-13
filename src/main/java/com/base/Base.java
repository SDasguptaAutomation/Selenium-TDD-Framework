package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	static WebDriver driver;
	
	public WebDriver init_browser()
	{
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--start-maximized");
		option.addArguments("--disable-notification");
		option.setAcceptInsecureCerts(true);
		
		driver = new ChromeDriver(option);
		
		return driver;
	}
	
	public String screenshot() throws IOException
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath = "D:\\MyAutomation\\TestAutomation\\DemoECommerce\\test-output\\Screenshot\\Screenshot-"+System.currentTimeMillis()+".png";
		FileUtils.copyFile(src, new File(screenshotPath));
		return screenshotPath;
	}
	
	@SuppressWarnings("resource")
	public ArrayList<String> getDatafromExcel(String tCaseName) throws IOException
	{
		FileInputStream fis = new FileInputStream(new File("D:\\MyAutomation\\TestAutomation\\DemoECommerce\\test-output\\Test_Data\\TestData.xlsx"));
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sh = wb.getSheetAt(0);
		
		ArrayList<String> arr = new ArrayList<>();
		
		int rowNumber = -1;
		
		for(Row row : sh)
		{
			 Cell cell = row.getCell(0);
			 if(cell.getStringCellValue().equalsIgnoreCase(tCaseName))
			 {
				 rowNumber = row.getRowNum();
			 }
		}
		
		Row r = sh.getRow(rowNumber);
		
		for(Cell cell : r)
		{
			if(cell.getCellType()==CellType.STRING)
			{
				arr.add(cell.getStringCellValue());
			}
			else if(cell.getCellType()==CellType.NUMERIC)
			{
				arr.add(String.valueOf(cell.getNumericCellValue()));
			}
		}
		
		return arr;
	}
}
