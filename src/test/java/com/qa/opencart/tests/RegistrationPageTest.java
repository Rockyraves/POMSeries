package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.qa.opencart.utils.*;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;
@Listeners(com.qa.opencart.listeners.TestAllureListener.class)
public class RegistrationPageTest extends BaseTest {

	@BeforeClass
	public void RegPageSetUp() {
		registrationPage = loginpage.navigateToRegisterPage();
	}

	public String randomEmailId() {
		Random random = new Random();
		String emailId = "ramsita" + random.nextInt(1000) + "@gmail.com";
		return emailId;
	}

//	@DataProvider
//	public Object[][] getRegisterData() {
//		return new Object[][] { { "Naruto", "uzumaki", "9982123456", "Naruto@123", "yes" },
//				{ "Sasuke", "uchiha", "8873456782", "Sasuke@123", "no" },
//				{ "Itachi", "uchiha", "6785490348", "Itachi@123", "yes" }, };
//	}

	@DataProvider(name = "testdata")
	public Object[][] getRegisteredData() {
		Object regData[][] = ExcelUtil.getTestData(Constants.REGISTER_TESTDATA_SHEET);
		return regData;
	}

	@Test(dataProvider = "testdata")
	public void accRegistrationTest(String firstName, String lastName, String telephone, String password,
			String subscribe) {
		boolean result = registrationPage.accRegistration(firstName, lastName, randomEmailId(), telephone, password,
				subscribe);
		Assert.assertTrue(result);
	}

}
