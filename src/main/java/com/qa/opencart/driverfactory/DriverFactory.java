package com.qa.opencart.driverfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Listeners;

import com.qa.opencart.utils.Browser;

@Listeners(com.qa.opencart.listeners.TestAllureListener.class)

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager om;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static final Logger log=LogManager.getLogger(DriverFactory.class);

	/**
	 * This method is used to intialize the webdriver based on the given
	 * browsername. This method will take care of both local and remote execution.
	 * 
	 * @param browserName
	 */
	public WebDriver init_driver(Properties prop) {
		highlight = prop.getProperty("highlight").trim();
		String browserName = prop.getProperty("browser").trim();
		log.info("Browser Name is " + browserName);
		om = new OptionsManager(prop);
		if (browserName.equalsIgnoreCase(Browser.CHROME_BROWSER_VALUE)) {
			log.info("Running the test on chrome browser!!!");
			tlDriver.set(new ChromeDriver(om.getChromeOptions()));
		} else if (browserName.equalsIgnoreCase(Browser.FIREFOX_BROWSER_VALUE)) {
			log.info("Running the test on chrome browser!!!");
			tlDriver.set(new FirefoxDriver(om.getFirefoxOptions()));
		} else {
			log.info("Please pass the right browser name!");
		}
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/*
	 * This method is used to initialize the properties based on the environment.
	 * QA/PROD/DEV/STG
	 */
	public Properties initProp() {
		prop = new Properties();
		FileInputStream fp = null;

		String envName = System.getProperty("env");
		log.info("Running test on the environment : " + envName);

		if (envName == null) {
			log.info("Running the tests in the default environment");
			try {
				fp = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
			try {
				switch (envName.toLowerCase()) {
				case "qa":
					fp = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					fp = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "stage":
					fp = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "uat":
					fp = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "prod":
					fp = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
					log.error("Please pass the right environment!!!");
					log.warn("environment is not found");
					break;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		try {
			prop.load(fp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;

	}

	/**
	 * Take Screeshot
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return path;
	}
}
