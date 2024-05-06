package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Step;

@Listeners(com.qa.opencart.listeners.TestAllureListener.class)
public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1.private By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By forgotPwd = By.linkText("Forgotten Password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By loginBtn1 = By.xpath("//input[@value='Login']");
	private By register = By.xpath("//div[@class='list-group']/a[contains(text(),'Register')]");
	private By errorMessage = By.cssSelector("div.alert.alert-danger.alert-dismissible");

	// 2.public page class constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	// 3.public page actions
	@Step("Getting Login Page Title...")
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIs(Constants.DEFAULT_TIMEOUT, Constants.LOGIN_PAGE_TITLE);
	}

	@Step("Getting Login Page URL...")
	public String getLoginPageUrl() {
		return eleUtil.waitForUrl(Constants.DEFAULT_TIMEOUT, Constants.LOGIN_PAGE_FRACTION_URL);
	}

	@Step("Checking the Forgot password link Exist or not...")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwd);
	}

	@Step("Login to application with username {0} and password {1}")
	public AccountsPage doLogin(String un, String pwd) {
		eleUtil.waitForElementToBeVisible(emailId, Constants.DEFAULT_TIMEOUT).sendKeys(un);
		eleUtil.waitForElementToBeVisible(password, Constants.DEFAULT_TIMEOUT).sendKeys(pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}

	@Step("Login to application with username {0} and password {1}")
	public boolean doInvalidLogin(String un, String pwd) {
		WebElement ele = eleUtil.waitForElementToBeVisible(emailId, Constants.DEFAULT_TIMEOUT);
		ele.clear();
		ele.sendKeys(un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		String actualmessage = eleUtil.doElementGetText(errorMessage);
		if (actualmessage.contains(Errors.LOGIN_PAGE_WARNING_MESSAGE)) {
			System.out.println(actualmessage);
			return true;
		}
		return false;
	}

	@Step("Checking the Register link Exist or not...")
	public boolean isRegisterLinkExist() {
		return eleUtil.waitForElementToBeVisible(register, Constants.DEFAULT_TIMEOUT).isDisplayed();
	}

	@Step("Navigating to register page...")
	public RegistrationPage navigateToRegisterPage() {
		if (isRegisterLinkExist()) {
			eleUtil.waitForElementToBeVisible(register, Constants.DEFAULT_TIMEOUT).click();
			return new RegistrationPage(driver);
		}
		return null;
	}

}
