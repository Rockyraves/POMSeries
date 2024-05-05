package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;
@Listeners(com.qa.opencart.listeners.TestAllureListener.class)
public class ProductDescriptionPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private By productHeader = By.cssSelector("div#content h1");
	private By productImg = By.cssSelector("ul.thumbnails img");
	private By productInfoMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li ");
	private By productPriceInfoMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li ");
	private By productQuantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private By successMessage = By.cssSelector("div.alert.alert-success.alert-dismissible");
	private Map<String, String> productInfoMap;

	public ProductDescriptionPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String productHeaderText() {
		return eleUtil.doElementGetText(productHeader).trim();
	}

	public int getProductImagesCount() {
		return eleUtil.waitForElementsToBeVisible(productImg, Constants.DEFAULT_TIMEOUT).size();
	}

	public Map<String, String> getProductInfo() {
		productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap.put("ProductName", productHeaderText());
		productInfoMap.put("ProductImagesCount", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		return productInfoMap;
	}

	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productInfoMetaData);
		for (WebElement e : metaDataList) {
			String text = e.getText().trim();
			String meta[] = text.split(":");
			String metaKey = meta[0].trim();
			String metaValue = meta[1].trim();
			productInfoMap.put(metaKey, metaValue);

		}
	}

	private void getProductPriceData() {
		List<WebElement> metaPriceList = eleUtil.getElements(productPriceInfoMetaData);
		for (WebElement e : metaPriceList) {
			String price = metaPriceList.get(0).getText().trim();
			String extraPrice = metaPriceList.get(1).getText().trim();
			productInfoMap.put("price", price);
			productInfoMap.put("extraPrice", extraPrice);

		}
	}

}
