package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Listeners(com.qa.opencart.listeners.TestAllureListener.class)

@Epic("Epic 100-Design Login Page For Open Cart Application")
@Story("US: 101 -Design login page features.")

public class LoginPageTest extends BaseTest {

	@Test
	@Description("Login Page Title Test")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageTitleTest() {
		String actTitle = loginpage.getLoginPageTitle();
		System.out.println("Actual title of the page " + actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE,Errors.LOGIN_PAGE_TITLE_MISMATCH);
	}

	@Test
	@Description("Login Page URL Test")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageUrlTest() {
		String actUrl = loginpage.getLoginPageUrl();
		System.out.println("Loginpage url is " + actUrl);
		Assert.assertTrue(actUrl.contains(Constants.LOGIN_PAGE_FRACTION_URL),Errors.LOGIN_PAGE_URL_MISMATCH);
	}

	@Test
	@Description("Login Page Forgot Password Link Test")
	@Severity(SeverityLevel.CRITICAL)
	public void loginPageForgetPwdTest() {
		boolean flag = loginpage.isForgotPwdLinkExist();
		Assert.assertTrue(flag,Errors.LOGIN_PAGE_FORGET_PASSWORD_LINK_NOT_EXIST);

	}

	@Test
	@Description("Register Link Exist Test")
	@Severity(SeverityLevel.CRITICAL)
	public void registerLinkExistTest() {
		boolean flag = loginpage.isRegisterLinkExist();
		Assert.assertTrue(flag,Errors.LOGIN_PAGE_REGISTER_LINK_NOT_EXIST);
	}

	@Test
	@Description("Login Title Test with correct username and correct password password...")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		accountsPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		String actAccPageTitle = accountsPage.accountPageTitle();
		Assert.assertEquals(actAccPageTitle, Constants.ACCOUNT_PAGE_TITLE,Errors.LOGIN_PAGE_DO_LOGIN_ERROR);
	}

}
