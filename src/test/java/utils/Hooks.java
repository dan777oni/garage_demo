package utils;

import static org.junit.Assert.fail;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.Before;
import cucumber.api.java.After;

public class Hooks {
	
	public static WebDriver driver = null;
	private StringBuffer verificationErrors = new StringBuffer();
	
//	@Before
	public void beforeScenario() throws Throwable {
		System.out.println("Inside Before");
		System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Java\\selenium-java-3.0.1\\geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("marionette", true);
        driver = new FirefoxDriver(capabilities);
        driver.get("http://uat.garageclothing.com/ca/");	
        driver.manage().window().maximize();

	}
	
//	@After
		  public void tearDown() throws Exception {
		     System.out.println("Inside After");
		     driver.quit();
			//  driver.close();
		    String verificationErrorString = verificationErrors.toString();
		    if (!"".equals(verificationErrorString)) {
		      fail(verificationErrorString);
		    }
		  }


}
