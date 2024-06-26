package com.qa.opencart.driverfactory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Listeners;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

@Listeners(com.qa.opencart.listeners.TestAllureListener.class)
public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			co.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito")))
			co.addArguments("--incognito");
		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setPlatformName("linux");
			co.setCapability("goog:chromeOptions", ImmutableMap.of("enableVNC", true));
			//co.setCapability("enableVNC", true);
			//co.setCapability("goog:chromeOptions", ImmutableMap.of("args", ImmutableList.of("--remote-debugging-port=8080")));
			co.setBrowserVersion(prop.getProperty("browserVersion"));
		}
		return co;
	}

	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			fo.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("--incognito")))
			fo.addArguments("--incognito");
		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			fo.setPlatformName("linux");
			fo.setCapability("enableVNC", true);
			fo.setBrowserVersion(prop.getProperty("browserVersion"));
		}
		return fo;
	}
}
