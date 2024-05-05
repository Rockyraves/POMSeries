package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(com.qa.opencart.listeners.TestAllureListener.class)
@Epic("Epic 200-Design Account Page For Open Cart Application")
@Story("US: 101 - Design Account page features.")
@Story("US: 102 - Design Search page features. ")

public class AccountPageTest extends BaseTest {
	@Description("pre login for account page test")
	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Description("Accounts page title test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void accountPageTitleTest() {
		String actAccountTitle = accountsPage.accountPageTitle();
		Assert.assertEquals(actAccountTitle, Constants.ACCOUNT_PAGE_TITLE);
	}

	@Description("Search Exist Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void isSearchFieldExistTest() {
		boolean flag = accountsPage.isSearchFieldExist();
		Assert.assertTrue(flag);
	}

	@Description("Accounts page header test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void isAccountsPageHeaderExistTest() {
		boolean flag = accountsPage.isAccountsPageHeaderExist();
		Assert.assertTrue(flag);
	}

	@Description("Accounts page section test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void getAccSectionListTest() {
		List<String> accSectionList = accountsPage.getAccSectionList();
		Assert.assertEquals(accSectionList, Constants.ACCOUNTS_SECTION_LIST);

	}

	@Description("Accounts page Footer test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void getAccFooterListTest() {
		List<String> accFooterList = accountsPage.getAccFooterList();
		Assert.assertEquals(accFooterList, Constants.ACCOUNTS_FOOTER_LIST);
	}

	@Description("Search page header test")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void searchHeaderTest() {
		searchResultsPage = accountsPage.doSearch("macbook");
		String actHeaderVal = searchResultsPage.getResultsPageHeaderValue();
		Assert.assertTrue(actHeaderVal.contains("macbook"));
	}

	@Description("Search page product counts test")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void searchProductCountTest() {
		searchResultsPage = accountsPage.doSearch("macbook");
		int actProductsCount = searchResultsPage.getProductsCount();
		Assert.assertEquals(actProductsCount, Constants.MACBOOK_PRODUCT_COUNT);
	}

	@Description("Search page search product test")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void searchProductTest() {
		searchResultsPage = accountsPage.doSearch("macbook");
		List<String> actProducts = searchResultsPage.getProductsResultList();
		Assert.assertEquals(actProducts, Constants.SEARCH_PRODUCTS_LIST);
	}

}
