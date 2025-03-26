package tnp.tutorialNingaProject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageElement{
	WebDriver driver;

	//Objects
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropdown;
	
	@FindBy(linkText = "Login")
	private WebElement loginLink;
	
	@FindBy(name = "search")
	private WebElement searchTextFeild;


	public HomePageElement(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public void clickMyAccount() {
		myAccountDropdown.click();
	}
	
	public void selectLogin() {
		loginLink.click();
	}
	

}
