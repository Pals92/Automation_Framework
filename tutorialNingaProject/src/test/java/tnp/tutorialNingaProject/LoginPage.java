package tnp.tutorialNingaProject;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import tnp.tutorialNingaProject.base.Base;
import tnp.tutorialNingaProject.pages.HomePageElement;
import tnp.tutorialNingaProject.utils.Utilities;

public class LoginPage extends Base{
	
	WebDriver driver;
	
	public LoginPage() {
		super();
	}
	@BeforeMethod
	public void openBrowser() {
		
		driver = initializeAndLaunchApplication(prop.getProperty("browserName"));
		HomePageElement homePageElement=new HomePageElement(driver);
		homePageElement.clickMyAccount();
		homePageElement.selectLogin();
	}
	
	@AfterMethod
	public void quitBrowser() {
		driver.quit();
	}
	
	@Test(priority = 1, dataProvider = "fetchData")
	public void verifyLoginWithValidCredentials(String email, String password) {
		
		driver.findElement(By.id("input-email")).sendKeys(email);
		driver.findElement(By.id("input-password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Login']")).submit();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),"Edit your account information is not displayed");
		
	}
	
	@DataProvider(name="fetchData")
	public Object[][] supplyData() {
		Object[][] data= Utilities.getDataFromExcel("Sheet1");;
		return  data;
	}
	@Test(priority = 2)
	public void verifyLoginWithInValidCredentials() {
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).submit();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class , 'alert-dismissible')]")).getText();
		String expectedWarningMessage= "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected message is not displayed");
		
	}

	@Test(priority = 3)
	public void verifyLoginWithValidEmailAndInvalidPassword() {
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).submit();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class , 'alert-dismissible')]")).getText();
		String expectedWarningMessage= dataProp.getProperty("emailNoMatchWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected message is not displayed");
		
	}
	
	@Test(priority = 4)
	public void verifyLoginWithOutCredentials() {
		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.xpath("//input[@value='Login']")).submit();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class , 'alert-dismissible')]")).getText();
		String expectedWarningMessage= dataProp.getProperty("emailNoMatchWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected message is not displayed");
		
		
	}
	
	@Test(priority = 5)
	public void verifyWithInvalidEmailAndValidPassword() {
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).submit();
		String actualWarningMessage = driver.findElement(By.xpath("//div[contains(@class , 'alert-dismissible')]")).getText();
		String expectedWarningMessage= dataProp.getProperty("emailNoMatchWarningMessage");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected message is not displayed");
		
	}
	
	
	
}
