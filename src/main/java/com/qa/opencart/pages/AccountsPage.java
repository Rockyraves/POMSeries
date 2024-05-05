package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
@Listeners(com.qa.opencart.listeners.TestAllureListener.class)
public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By searchField = By.cssSelector("div #search input");
	private By accountLogo = By.cssSelector("div #logo");
	private By searchBtn = By.cssSelector("div #search button");
	private By accountSectionList = By.cssSelector("div #content h2");
	private By accFooterList = By.xpath("//div[@class='col-sm-3']/h5");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String accountPageTitle() {
		return eleUtil.waitForTitleIs(Constants.DEFAULT_TIMEOUT, Constants.ACCOUNT_PAGE_TITLE);
	}

	public boolean isSearchFieldExist() {
		return eleUtil.doIsDisplayed(searchField);
	}

	public SearchResultsPage doSearch(String productName) {
		if (isSearchFieldExist()) {
			eleUtil.doSendKeys(searchField, productName);
			eleUtil.doClick(searchBtn);
			return new SearchResultsPage(driver);
		}
		return null;

	}

	public boolean isAccountsPageHeaderExist() {
		return eleUtil.doIsDisplayed(accountLogo);
	}

	public List<String> getAccSectionList() {
		List<WebElement> elelist = eleUtil.getElements(accountSectionList);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : elelist) {
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;
	}

	public List<String> getAccFooterList() {
		List<WebElement> eleList = eleUtil.getElements(accFooterList);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;
	}

}
