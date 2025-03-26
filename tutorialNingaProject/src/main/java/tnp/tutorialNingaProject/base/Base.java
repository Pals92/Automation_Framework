package tnp.tutorialNingaProject.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import tnp.tutorialNingaProject.utils.Utilities;

public class Base{

	WebDriver driver;
	public static Properties prop;
	public static Properties dataProp;
	
	public Base() {
		prop=new Properties();
		dataProp=new Properties();
		
		File dataPropFile= new File(System.getProperty("user.dir")+"//src//main//java//tnp//tutorialNingaProject//testdata//testdata.properties");
		
		try{
			FileInputStream fis2= new FileInputStream(dataPropFile);
			dataProp.load(fis2);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		File propFile= new File(System.getProperty("user.dir")+"//src//main//java//tnp//tutorialNingaProject//config//config.properties");
		try {
			FileInputStream fis=new FileInputStream(propFile);
			prop.load(fis);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver initializeAndLaunchApplication(String browserName) {
		
		if(browserName.equalsIgnoreCase(browserName)){		
			driver= new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase(browserName)){		
			driver= new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase(browserName)){		
			driver= new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase(browserName)){		
			driver= new SafariDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGELOAD_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
}
