package tnp.tutorialNingaProject;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import tnp.tutorialNingaProject.base.Base;
import tnp.tutorialNingaProject.utils.Utilities;

public class RegisterPage extends Base {

	WebDriver driver;
	public RegisterPage() {
		super();
	}
	
	@BeforeMethod
	public void openBrowser() {
		
		driver = initializeAndLaunchApplication(prop.getProperty("browserName"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
		
	}
	
	@AfterMethod 
	public void quitBrowser() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifyRegisterWithMandatoryFeilds() {
		
	
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephone"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).submit();
		String actualMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		System.out.println(actualMessage);
		Assert.assertEquals(actualMessage,dataProp.getProperty("accountSuccessMessage"),"sorry! Failed to create your account!");
		
	}
	
	@Test(priority = 2)
	public void verifyRegisterWithAllFeilds(){
		
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephone"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'] [@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).submit();
		String actualMessage = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualMessage,dataProp.getProperty("accountSuccessMessage"),"sorry! Failed to create your account!");
		
	}
	@Test(priority = 3)
	public void verifyRegisterWithExistingEmailAddress(){
		
		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephone"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@name='newsletter'] [@value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).submit();
		String warningMessage = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		System.out.println(warningMessage);
		Assert.assertEquals(warningMessage,dataProp.getProperty("duplicateWarningMessage"),"sorry! Failed to create your account!");
		
	}
	
	@Test(priority = 4)
	public void verifyRegisterWithoutEnteringAnyData(){
		
		
		driver.findElement(By.xpath("//input[@value='Continue']")).submit();
		
		String privacyPolicyWarningMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		Assert.assertTrue(privacyPolicyWarningMessage.contains(dataProp.getProperty("privacyPolicyWarningMessage")),"Privacy policy is not checked");
		
		String actualFirstNameWarningMessage =driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertEquals(actualFirstNameWarningMessage, dataProp.getProperty("firstNameWarningMeassage"),"First Name warning message is not displayed");
		
		String actualLastNameWarningMessage =driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertEquals(actualLastNameWarningMessage, dataProp.getProperty("lastNameWarningMeassage"),"Last Name warning message is not displayed");
		
		String actualEmailAddressWarningMessage =driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		Assert.assertEquals(actualEmailAddressWarningMessage, dataProp.getProperty("emailWarningMeassage"),"Email address warning message is not displayed");
		
		String actualPhoneNumberWarningMessage =driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		Assert.assertEquals(actualPhoneNumberWarningMessage, dataProp.getProperty("telephoneWarningMeassage"),"Telephone warning message is not displayed");
		
		String actualPasswordWarningMessage =driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertEquals(actualPasswordWarningMessage, dataProp.getProperty("passwordWarningMessage"),"Password warning message is not displayed");
		
		
	}

}
