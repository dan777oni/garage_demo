package utils;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverFactory {

	public static WebDriver driver = null;
	public static WebDriverWait waitVar = null;
//	public static String baseURL = "http://store.demoqa.com/";
	public static String baseURL = "http://uat.garageclothing.com/ca/";
	/**
	* This function is to invoke Selenium Webdriver
	*
	* @throws MalformedURLException
	* @throws InterruptedException
	*/
	public void createDriver() throws MalformedURLException,
	InterruptedException {
		
		System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Java\\selenium-java-3.0.1\\geckodriver.exe");
		
		//driver = new FirefoxDriver();	
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        
        WebDriver driver = new FirefoxDriver(capabilities);

	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	driver.get(baseURL);
	waitVar = new WebDriverWait(driver, 15);
	}
	/**
	* This function is to close driver instance
	*/
	public void teardown() {
	driver.quit();
	}
}


