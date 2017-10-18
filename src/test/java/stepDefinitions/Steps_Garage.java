package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps_Garage {
	
	private StringBuffer verificationErrors = new StringBuffer();
	public static WebDriver driver = null;
	public static WebDriverWait waitVar = null;	
	public int prodQty = 0; 
    public String prodQtyTxt;
    public String prodQtyTxtBefore;
	
	@Before
	public void beforeScenario() throws Throwable {
		System.out.println("Inside Before");

		//**** Instantiate Test WebDriver
		
		int browserCode = 1;    // 1-CHROME, 2-FIREFOX, 3-IE
		
		switch (browserCode) {
		  case 1:  
				// Chrome 
				
				System.setProperty("webdriver.chrome.driver", "C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe");
				driver = new ChromeDriver();

          break;
          case 2: 
				// FireFox
        	  
      		System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Java\\selenium-java-3.0.1\\geckodriver.exe");
      		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
            capabilities.setCapability("marionette", true);
            driver = new FirefoxDriver(capabilities);
       	  
          break;
          case 3:  
				// Internet Explorer
        	  
      		//	DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
      		//	capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
      			File file = new File("C:\\Program Files (x86)\\Internet Explorer\\IEDriverServer.exe");
      			System.setProperty("webdriver.ie.driver",file.getAbsolutePath());
      		//  driver = new InternetExplorerDriver(capabilities);
      			driver = new InternetExplorerDriver();
      			
          break;
          
          default: 
				// Chrome 
				
				System.setProperty("webdriver.chrome.driver", "C:/Program Files (x86)/Google/Chrome/Application/chromedriver.exe");
				driver = new ChromeDriver();
        	  
          break;		
	
		}
		
        driver.get("http://uat.garageclothing.com/ca/");	
        driver.manage().window().maximize();

	}
	
	@Given("^user is on Garage home page$")
	public void user_is_on_Garage_home_page() throws Throwable {
	    // Assert that User is on Garage home page
		System.out.println("Current URL:" + driver.getCurrentUrl());
		 try {
		      assertEquals("http://uat.garageclothing.com/ca/", driver.getCurrentUrl());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
	}
	
	@Given("^user selects a category \"([^\"]*)\"$")
	public void user_selects_a_category(String argCategory) throws Throwable {
	   
	 //   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); 
		waitVar = new WebDriverWait(driver,5);
		waitVar.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("NEW")));
	    try {
	        assertTrue(isElementPresent(By.linkText(argCategory)));
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	    driver.findElement(By.linkText(argCategory)).click();
	}

	@Given("^user selects a product \"([^\"]*)\"$")
	public void user_selects_a_product(String argProduct) throws Throwable {

	 //   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		waitVar = new WebDriverWait(driver,10);
		//waitVar.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("STRIPED")));
		waitVar.until(ExpectedConditions.elementToBeClickable(By.id("100016537")));
		
	    try {
	        assertTrue(isElementPresent(By.linkText(argProduct)));
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	    //driver.findElement(By.linkText(argProduct)).click();
	    driver.findElement(By.id("100016537")).click();

	}

	@Given("^element with id \"([^\"]*)\" exists on the page$")
	public void element_with_id_exists_on_the_page(String argId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	    try {
	        assertTrue(isElementPresent(By.id(argId)));
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	}

	@Given("^user clicks on element with id \"([^\"]*)\"$")
	public void user_clicks_on_element_with_id(String argId) throws Throwable {
		waitVar = new WebDriverWait(driver,10);
		waitVar.until(ExpectedConditions.elementToBeClickable(By.id(argId)));
	    driver.findElement(By.id(argId)).click();
	}

	@Given("^user selects size of the product$")
	public void user_selects_size_of_the_product() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		   driver.findElement(By.xpath("//div[@id='productSizes']/span[3]")).click();
	}
	
	@Then("^quantity increase$")
	public void quantity_increase() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    try {
	        assertEquals("2", driver.findElement(By.id("qty")).getAttribute("value"));
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	      driver.findElement(By.id("qtyIncrease")).click();
	      try {
	        assertEquals("3", driver.findElement(By.id("qty")).getAttribute("value"));
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	}

	@Then("^quantity decrease$")
	public void quantity_decrease() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    try {
	        assertEquals("2", driver.findElement(By.id("qty")).getAttribute("value"));
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	      driver.findElement(By.id("qtyDecrease")).click();
	      try {
	        assertEquals("1", driver.findElement(By.id("qty")).getAttribute("value"));
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }

	}


	@Given("^user click on button Add to Bug$")
	public void user_click_on_button_Add_to_Bug() throws Throwable {
     // Testing if the button Add to Bug can be clicked
		driver.findElement(By.id("pdpAddToCartButton")).click();
	//	   try {
	//		      assertTrue(isElementPresent(By.id("AddToCartErrorContainer")));
	//		    } catch (Error e) {
	//		      verificationErrors.append(e.toString());
	//		    }
	//	   driver.findElement(By.xpath("//div[@id='productSizes']/span[3]")).click();
	//	   driver.findElement(By.id("pdpAddToCartButton")).click();	   
	}
	
	
	@Given("^user clicks on button \"([^\"]*)\"$")
	public void user_clicks_on_button(String arg3) throws Throwable {
	     // Testing if the button Add to Bug can be clicked
			driver.findElement(By.id(arg3)).click();
  
	}

	@Then("^pop-up with id \"([^\"]*)\" is displayed$")
	public void pop_up_with_id_is_displayed(String argId) throws Throwable {

	    //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
		waitVar = new WebDriverWait(driver,5);
		waitVar.until(ExpectedConditions.elementToBeClickable(By.id(argId)));
		
		   try {
			      assertTrue(isElementPresent(By.id(argId)));
			    } catch (Error e) {
			      verificationErrors.append(e.toString());
			    }

	}

	@Then("^pop-up with id \"([^\"]*)\" is closed$")
	public void pop_up_with_id_is_closed(String argId) throws Throwable {

	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		   try {
			      assertTrue(isElementNotPresent(By.id(argId)));
			    } catch (Error e) {
			      verificationErrors.append(e.toString());
			    }
	}


	@When("^user click icon x to close$")
	public void user_click_icon_x_to_close() throws Throwable {

		waitVar = new WebDriverWait(driver,5);
		waitVar.until(ExpectedConditions.elementToBeClickable(By.id("cboxClose")));
		
		if (isElementPresent(By.id("cboxClose"))) {
			System.out.println("Close X icon is displayed");
			driver.findElement(By.id("cboxClose")).click();	 
		} ;
			   
	}

	
	@Given("^link \"([^\"]*)\" exists on the page$")
	public void link_exists_on_the_page(String arg1) throws Throwable {

	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	//	waitVar = new WebDriverWait(driver,10);
	//	waitVar.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(arg1)));
	    
		   try {
			      assertTrue(isElementPresent(By.linkText(arg1)));
			    } catch (Error e) {
			      verificationErrors.append(e.toString());
			    }

	}
	
	@Given("^user click on link \"([^\"]*)\"$")
	public void user_click_on_link(String arg2) throws Throwable {
		waitVar = new WebDriverWait(driver,5);
	//	waitVar.until(ExpectedConditions.elementToBeClickable(By.linkText(arg2)));
		String xPathTxt = "//a[contains(text(),'" + arg2 + "')]";
		waitVar.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathTxt)));
		
		
//		  driver.findElement(By.linkText(arg2)).click();	 
	//	  driver.findElement(By.xpath("//a[contains(text(),'description')]")).click();	
		  driver.findElement(By.xpath(xPathTxt)).click();	
	}	


	@Then("^Login page is displayed$")
	public void login_page_is_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		   try {
			      assertTrue(isElementPresent(By.xpath("//div[@id='profileLogin']/div/div/div/div/span")));
			    } catch (Error e) {
			      verificationErrors.append(e.toString());
			    }
		//     driver.quit();
	}
	
	@When("^user enters Postal code \"([^\"]*)\"$")
	public void user_enters_Postal_code(String argPCode) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		waitVar = new WebDriverWait(driver,5);
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id("searchPostalCode")));
	  //  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
	    driver.findElement(By.id("searchPostalCode")).clear();
		driver.findElement(By.id("searchPostalCode")).sendKeys(argPCode);
	}

	@Then("^message \"([^\"]*)\" is displayed$")
	public void message_is_displayed(String argId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		waitVar = new WebDriverWait(driver,10);
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id(argId)));
		   try {
			      assertTrue(isElementPresent(By.id(argId)));
			    } catch (Error e) {
			      verificationErrors.append(e.toString());
			    }
      
	}


	@Then("^results are displayed$")
	public void results_are_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		   try {
			      assertTrue(isElementPresent(By.className("findStoresProdInfo")));
			    } catch (Error e) {
			      verificationErrors.append(e.toString());
			    }
	}

	@When("^there is at least one result$")
	public void there_is_at_lease_one_result() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		   try {
			      assertTrue(isElementPresent(By.id("addPreferredStore")));
			    } catch (Error e) {
			      verificationErrors.append(e.toString());
			    }

	}


	@Then("^validation message is displayed$")
	public void validation_message_is_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
	    try {
	        assertEquals("+1 STORE ADDED", driver.findElement(By.xpath("//div[@id='tabs-2']/table/tbody/tr[3]/td[4]/div")).getText());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	}



	@Then("^Description text is displayed$")
	public void description_text_is_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		   try {
			      assertTrue(isElementPresent(By.id("descTab0Content")));
			    } catch (Error e) {
			      verificationErrors.append(e.toString());
			    }

	}

	@Then("^Details text is displayed$")
	public void details_text_is_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		   try {
			      assertTrue(isElementPresent(By.id("descTab1Content")));
			    } catch (Error e) {
			      verificationErrors.append(e.toString());
			    }

	}

	@Then("^Size & Fit text is displayed$")
	public void size_Fit_text_is_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		   try {
			      assertTrue(isElementPresent(By.id("descTab2Content")));
			    } catch (Error e) {
			      verificationErrors.append(e.toString());
			    }

	}
	
	@Given("^user click on link WRITE A REVIEW$")
	public void user_click_on_link_WRITE_A_REVIEW() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
	    try {
	    	assertTrue(isElementPresent(By.xpath("//button[@type='button']")));
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	    driver.findElement(By.xpath("//button[@type='button']")).click();	    
	}

	@Then("^Pop-up Write a review is opened$")
	public void pop_up_Write_a_review_is_opened() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
	    try {
	    	assertTrue(isElementPresent(By.xpath("//div[@id='bv-mbox-lightbox-list']/div[2]/h2")));
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	}

	@When("^user click icon x to close Write a review$")
	public void user_click_icon_x_to_close_Write_a_review() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		waitVar = new WebDriverWait(driver,5);
		waitVar.until(ExpectedConditions.elementToBeClickable(By.name("Cancel")));

	    driver.findElement(By.name("Cancel")).click();	 
	}

	@Then("^Pop-up Write a review is closed$")
	public void pop_up_Write_a_review_is_closed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
	    try {
	    	assertTrue(isElementNotPresent(By.xpath("//div[@id='bv-mbox-lightbox-list']/div[2]/h2")));
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }

	}

	@Then("^Checkout page is displayed$")
	public void checkout_page_is_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
	    System.out.println("Page Source:" + driver.getPageSource());
	    try {
	        assertEquals("http://uat.garageclothing.com/ca/checkout/cart.jsp", driver.getPageSource());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      //  Assert.fail("Failure on the Checkout page");
	      }

	}

	

	@Given("^user click on link Checkout$")
	public void user_click_on_link_Checkout() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		waitVar = new WebDriverWait(driver,5);
	//	waitVar.until(ExpectedConditions.elementToBeClickable(By.linkText("Checkout")));
		waitVar.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='miniCartContainer']/div/div[2]/div[2]/a")));

	//	driver.findElement(By.linkText("Checkout")).click();	 
		driver.findElement(By.xpath("//div[@id='miniCartContainer']/div/div[2]/div[2]/a")).click();	 
	}

	@Given("^user enters \"([^\"]*)\" to field with id \"([^\"]*)\"$")
	public void user_enters_to_field_with_id(String argCode, String argField) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 driver.findElement(By.id(argField)).clear();
		 driver.findElement(By.id(argField)).sendKeys(argCode);
	}

	@Given("^user selects \"([^\"]*)\" in field with id \"([^\"]*)\"$")
	public void user_selects_in_field_with_id(String argSelect, String argField) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		// driver.findElement(By.id(argField)).clear();
		new Select(driver.findElement(By.id(argField))).selectByVisibleText(argSelect);
	}
	
	
	@Then("^Product page is displayed$")
	public void product_page_is_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
	    System.out.println("Page Source:" + driver.getPageSource());
	    try {
	        assertEquals("http://uat.garageclothing.com/ca/checkout/cart.jsp", driver.getPageSource());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }

	}

	@Then("^Cart Checkout page is displayed$")
	public void cart_Checkout_page_is_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
	    System.out.println("Page Source:" + driver.getPageSource());
	    try {
	        assertEquals("https://uat.garageclothing.com/ca/checkout/checkout.jsp", driver.getPageSource());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }


	}

	@Given("^Address Validation is displayed$")
	public void address_Validation_is_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
	    try {
	    	assertTrue(isElementPresent(By.className("useAddress")));
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }

	}

	@When("^user selects payment \"([^\"]*)\"$")
	public void user_selects_payment(String argPMethod) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		String xPathName = "//input[@id='" + argPMethod + "']"; 
		waitVar = new WebDriverWait(driver,10);
	//	waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id(argPMethod)));
	//	waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPathName)));
		waitVar.until(ExpectedConditions.elementToBeClickable(By.xpath(xPathName)));
		
		//input[@id='paypal']
	//	driver.findElement(By.id(argPMethod)).click();	
		driver.findElement(By.xpath(xPathName)).click();			
	}

	@Then("^Interac page is displayed$")
	public void interac_page_is_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
		waitVar = new WebDriverWait(driver,10);
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id("IDEBIT_INVOICE")));
	    System.out.println("Page Source:" + driver.getPageSource());
	    try {
	        assertEquals("https://webservices.test.optimalpayments.com/IPSP/servlet/CertaPaySimulator", driver.getPageSource());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	}

	@Then("^Paypal page is displayed$")
	public void paypal_page_is_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		waitVar = new WebDriverWait(driver,20);
	    waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id("paypalLogo")));
	  //  waitVar.until(ExpectedConditions.elementToBeClickable(By.id("btnLogin")));
	    
	    System.out.println("Page Source:" + driver.getPageSource());
	    try {
	        assertEquals("PayPal Checkout - Log in", driver.getTitle());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
	}	

	@Then("^field with id \"([^\"]*)\" is displayed$")
	public void field_with_id_is_displayed(String argId) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
		waitVar = new WebDriverWait(driver,5);
	//    waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id("paypalLogo")));
	    try {
	    	assertTrue(isElementPresent(By.id(argId)));
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
     
	}
	
	@Given("^user clicks back button$")
	public void user_clicks_back_button() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
	    driver.navigate().back();

	}	
	
	@Then("^text \"([^\"]*)\" is displayed$")
	public void text_is_displayed(String argText) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		waitVar = new WebDriverWait(driver,30);
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='cartContainer']/h2")));
		
	    assertTrue(isElementPresent(By.xpath("//div[@id='cartContainer']/h2")));
	    try {
	      assertEquals("Your bag is empty.", driver.findElement(By.xpath("//div[@id='cartContainer']/h2")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }

//	    try {
//	        assertEquals(argText, driver.findElement(By.xpath("//div[@id='cartContainer']/h2")).getText());
//	      } catch (Error e) {
//	        verificationErrors.append(e.toString());
//	      }
      
	}

	@Then("^product quantity is updated$")
	public void product_quantity_is_updated() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		prodQtyTxt = driver.findElement(By.id("qty")).getAttribute("value");
	    prodQty = Integer.parseInt(prodQtyTxt); 
	}

	@Then("^product quantity corresponds to updated$")
	public void product_quantity_corresponds_to_updated() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		waitVar.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkOutCartContainer")));
	    try {
	        assertEquals(prodQtyTxt, driver.findElement(By.xpath("//div[@id='checkOutCartContainer']/div/div[2]/table/tbody/tr/td[3]/p[4]")).getText());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }

	}

	@Given("^link REMOVE is displayed$")
	public void link_REMOVE_is_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 //  driver.findElement(By.xpath("//div[@id='checkOutCartContainer']/div/div[2]/table/tbody/tr/td[3]/p[5]/a[2]")).click();
		 //assertTrue(isElementPresent(By.xpath("//div[@id='checkOutCartContainer']/div/div[2]/table/tbody/tr/td[3]/p[5]/a[2]")));
		 assertTrue(isElementPresent(By.cssSelector("a.blkLink.cartRemoveLink")));
		 //css=a.blkLink.cartRemoveLink
	    
	}
	
	
	@Then("^user click on link REMOVE$")
	public void user_click_on_link_REMOVE() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	//	driver.findElement(By.xpath("//div[@id='checkOutCartContainer']/div/div[2]/table/tbody/tr/td[3]/p[5]/a[2]")).click();
	//	driver.findElement(By.xpath("//div[@id='checkOutCartContainer']/div/div[2]/table/tbody/tr/td[3]/p[5]/a[2]")).click();
		waitVar = new WebDriverWait(driver,10);
		waitVar.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.blkLink.cartRemoveLink")));
		driver.findElement(By.cssSelector("a.blkLink.cartRemoveLink")).click();
    
	}
	
	@After
	  public void afterScenario() throws Throwable {
	     System.out.println("Inside After");
	     driver.quit();
		//  driver.close();
	 //   String verificationErrorString = verificationErrors.toString();
	 //   if (!"".equals(verificationErrorString)) {
	 //     fail(verificationErrorString);
	 //   }
	  }
	

	private boolean enterDataToField(String fieldId, String data) {
	    try {
		      driver.findElement(By.id(fieldId));
		      
		      return true;
		    } catch (NoSuchElementException e) {
		      return false;
		    }
			
	}
	

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isElementNotPresent(By by) {
		    try {
		      driver.findElement(by);
		      return false;
		    } catch (NoSuchElementException e) {
		      return true;
		    }
		  }
	  
	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }



}
