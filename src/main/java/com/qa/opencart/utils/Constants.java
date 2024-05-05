package com.qa.opencart.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final List<String> ACCOUNTS_SECTION_LIST = Arrays.asList("My Account", "My Orders",
			"My Affiliate Account", "Newsletter");
	public static final List<String> ACCOUNTS_FOOTER_LIST = Arrays.asList("Information", "Customer Service", "Extras",
			"My Account");
	public static final int DEFAULT_TIMEOUT = 5;
	public static final int MACBOOK_PRODUCT_COUNT = 3;
	public static final List<String> SEARCH_PRODUCTS_LIST = Arrays.asList("MacBook", "MacBook Air", "MacBook Pro");
	public static final int MACBOOK_PRODUCT_IMAGE_COUNT = 4;
	public static final String REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	public static final String REGISTER_TESTDATA_SHEET = "Sheet1";
	public static final String TEST_DATA_SHEET = "./src/test/resources/testData/democartTestdata.xlsx";
}
