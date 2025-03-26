package tnp.tutorialNingaProject;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import tnp.tutorialNingaProject.base.Base;

public class SearchPage extends Base{
	WebDriver driver;
	
	public SearchPage() {
		super();
	}
	@BeforeMethod
	public void openBrowser() {
		driver = initializeAndLaunchApplication(prop.getProperty("validEmail"));
	}
	
	@AfterMethod
	public void quitBrowser() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void searchWithValidProduct() {
		
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProductName"));
		driver.findElement(By.xpath("//button[contains(@class,'btn-lg')]/i[contains(@class,'search')]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::h2")).isDisplayed(), "Products list not displayed");
	}
	
	@Test(priority = 2)
	public void searchWithInValidProduct() {
		
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProductName"));
		driver.findElement(By.xpath("//button[contains(@class,'btn-lg')]/i[contains(@class,'search')]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p")).isDisplayed(), "Products list not displayed");
	}
	
	@Test(priority = 3)
	public void searchWithoutProduct() {
		
		driver.findElement(By.xpath("//button[contains(@class,'btn-lg')]/i[contains(@class,'search')]")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='button-search']/following-sibling::p")).isDisplayed(), "Products list not displayed");
	}

}
