package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks_wo_Factory {

	public static WebDriver driver = null;
	public static String browser = "firefox";
	public static String baseURL = "http://uat.garageclothing.com/ca/";
	
//	@Before
	

	public static void createDriver() {
	System.out.println("Inside Before");
	createDriver(browser);
	OpenURL(baseURL);
	}
	
	public static void createDriver(final String browserId) {
	if (browserId.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", "C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe");
		driver = new ChromeDriver();
	} else {
		System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Java\\selenium-java-3.0.1\\geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
   
        driver = new FirefoxDriver(capabilities);	}
		 
		
	}
	
	public static void OpenURL(String baseURL1) {
	//Maximize window
	//driver.manage().window().maximize();
	// Open URL on window
	driver.get(baseURL);
	}
	
	@After
	public void tearDown() {
	System.out.println("Inside After");
	driver.quit();
	}
	

}

