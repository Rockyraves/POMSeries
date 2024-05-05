package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
@Listeners(com.qa.opencart.listeners.TestAllureListener.class)
public class ProductsDescriptionPageTest extends BaseTest {

	@BeforeClass
	public void ProductDesPageSetUp() {
		accountsPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProductsDetails() {
		return new Object[][] { { "MacBook", "MacBook Pro" }, { "MacBook", "MacBook Air" },
				{ "samsung", "Samsung SyncMaster 941BW" }, };
	}

	@Test(dataProvider = "getProductsDetails")
	public void productHeaderTextTest(String searchedProductName, String selectedProductName) {
		searchResultsPage = accountsPage.doSearch(searchedProductName);
		productDesPage = searchResultsPage.selectProduct(selectedProductName);
		String actProductHeaderText = productDesPage.productHeaderText();
		Assert.assertEquals(actProductHeaderText, selectedProductName);
	}

	@Test(dataProvider = "getProductsDetails")
	public void ProductImagesCountTest(String searchedProductName, String selectedProductName) {
		searchResultsPage = accountsPage.doSearch(searchedProductName);
		productDesPage = searchResultsPage.selectProduct(selectedProductName);
		int actImgCount = productDesPage.getProductImagesCount();
		Assert.assertEquals(actImgCount, Constants.MACBOOK_PRODUCT_IMAGE_COUNT);
	}

	@Test(dataProvider = "getProductsDetails")
	public void ProductInfoTest(String searchedProductName, String selectedProductName) {
		searchResultsPage = accountsPage.doSearch(searchedProductName);
		productDesPage = searchResultsPage.selectProduct(selectedProductName);
		Map<String, String> actProductInfoMap = productDesPage.getProductInfo();
		actProductInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));
		SoftAssert.assertEquals(actProductInfoMap.get("ProductName"), "MacBook Pro");
		//SoftAssert.assertEquals(actProductInfoMap.get("ProductImagesCount"), "4");
		SoftAssert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		SoftAssert.assertEquals(actProductInfoMap.get("Availability"), "In Stock");
		SoftAssert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");
		SoftAssert.assertAll();
	}

}
