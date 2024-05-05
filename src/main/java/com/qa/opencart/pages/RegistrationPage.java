package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
@Listeners(com.qa.opencart.listeners.TestAllureListener.class)
public class RegistrationPage {

//	private WebDriver driver;
	private ElementUtil eleUtil;
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']//input[@value='0']");
	private By subscribeYes = By.xpath("//label[@class='radio-inline']//input[@value='1']");
	private By registerSuccessHeader = By.cssSelector("div#content h1");
	private By logoutLink = By.xpath("//a[text()='Logout' and @class='list-group-item']");
	private By registerLink = By.xpath("//a[text()='Register' and @class='list-group-item']");
	private By privatePolicy = By.name("agree");
	private By continueBtn = By.xpath("//input[@value='Continue']");

	public RegistrationPage(WebDriver driver) {
//		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public boolean accRegistration(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {
		eleUtil.waitForElementToBeVisible(this.firstName, Constants.DEFAULT_TIMEOUT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		} else
			eleUtil.doClick(subscribeNo);
		eleUtil.doClick(privatePolicy);
		eleUtil.doClick(continueBtn);
		if (getAccountRegisterSuccessMessage().contains(Constants.REGISTER_SUCCESS_MESSAGE)) {
			goToRegisterPage();
			return true;
		}
		return false;
	}

	public String getAccountRegisterSuccessMessage() {
		return eleUtil.waitForElementToBeVisible(registerSuccessHeader, Constants.DEFAULT_TIMEOUT).getText();
	}

	private void goToRegisterPage() {
		eleUtil.doClick(logoutLink);
		eleUtil.doClick(registerLink);
	}

}
