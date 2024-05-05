package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(com.qa.opencart.listeners.TestAllureListener.class)

@Epic("Epic 100-Design Login Page For Open Cart Application")
@Story("US: 101 -Design login page features.")

public class LoginPageNegativeTest extends BaseTest {

	@DataProvider
	public Object[][] loginPageData() {
		return new Object[][] { { "test@gmail.com", "Test@123" }, { "tes123t@gmail.com", "Test@12" } };
	}

	@Test(dataProvider = "loginPageData")
	@Description("Login to the application using invalid username and invalid password")
	@Severity(SeverityLevel.NORMAL)
	public void doInvalidLoginTest(String username, String pwd) {

		Assert.assertTrue(loginpage.doInvalidLogin(username, pwd), Errors.LOGIN_PAGE_ERROR_PAGE_MESSAGE_NOT_CORRECT);

	}

}
