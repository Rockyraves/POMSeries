package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.driverfactory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductDescriptionPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultsPage;

@Listeners(com.qa.opencart.listeners.TestAllureListener.class)
public class BaseTest {

	public DriverFactory df;
	public WebDriver driver;
	public LoginPage loginpage;
	public Properties prop;
	public AccountsPage accountsPage;
	public SearchResultsPage searchResultsPage;
	public ProductDescriptionPage productDesPage;
	public SoftAssert SoftAssert;
	public RegistrationPage registrationPage;

	@Parameters({ "browser", "browserVersion"})
	@BeforeTest
	public void setup(String browser,String browserVersion) {
		df = new DriverFactory();
		prop = df.initProp();
		if (browser != null) {
			prop.setProperty("browser", browser);
			prop.setProperty("browserVersion", browserVersion);
		}
		driver = df.init_driver(prop);
		loginpage = new LoginPage(driver);
		SoftAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
