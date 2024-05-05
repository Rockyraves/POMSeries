package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;
@Listeners(com.qa.opencart.listeners.TestAllureListener.class)
public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private By searchHeader = By.cssSelector("div#content h1");
	private By productsNames = By.cssSelector("div.caption a");

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	@Step("getResultsPageHeaderValue")
	public String getResultsPageHeaderValue() {
		if (eleUtil.doIsDisplayed(searchHeader)) {
			return eleUtil.doElementGetText(searchHeader);
		}
		return null;
	}
	
	@Step("getProductsCount")
	public int getProductsCount() {
		return eleUtil.waitForElementsToBeVisible(productsNames, Constants.DEFAULT_TIMEOUT).size();
	}
	
	@Step("getProductsResultList")
	public List<String> getProductsResultList() {
		List<WebElement> elelist = eleUtil.waitForElementsToBeVisible(productsNames, Constants.DEFAULT_TIMEOUT);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : elelist) {
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;
	}
	@Step("selectProduct {0}")
	public ProductDescriptionPage selectProduct(String productName) {
		System.out.println("ProductName is : " + productName);
		List<WebElement> eleList = eleUtil.waitForElementsToBeVisible(productsNames, Constants.DEFAULT_TIMEOUT);
		for (WebElement e : eleList) {
			String text = e.getText();
			if (text.equalsIgnoreCase(productName)) {
				e.click();
				break;
			}
		}
		return new ProductDescriptionPage(driver);

	}

}
