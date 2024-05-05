package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.driverfactory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {

		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);

	}

	public WebElement getElement(By locator) {

		WebElement element = driver.findElement(locator);

		if (Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
		return element;
	}

	public void doSendKeys(By locator, String Value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(Value);
	}

	public By getBy(String locatorType, String locatorvalue) {

		By locator = null;
		switch (locatorType.toLowerCase()) {
		case "id":
			locator = By.id(locatorvalue);
			break;

		case "name":
			locator = By.name(locatorvalue);
			break;

		case "className":
			locator = By.className(locatorvalue);
			break;

		case "xpath":
			locator = By.xpath(locatorvalue);
			break;

		case "cssSelector":
			locator = By.cssSelector(locatorvalue);
			break;

		case "linkText":
			locator = By.linkText(locatorvalue);
			break;

		case "partiallinkText":
			locator = By.partialLinkText(locatorvalue);
			break;

		case "tagName":
			locator = By.tagName(locatorvalue);
			break;

		default:
			System.out.println("please pass the right locator");
			break;
		}
		return locator;
	}

	public void doClick(By locator) {
		getElement(locator).click();

	}

	public String doElementGetText(By locator) {
		return getElement(locator).getText();

	}

	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public boolean isElementPresent(By locator) {

		if (getElements(locator).size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean doEnabled(By locator) {
		return getElement(locator).isEnabled();
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public List<String> getLinkTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();

		for (WebElement e : eleList) {
			String text = e.getText();
			if (e.getText().length() != 0) {
				System.out.println();
				eleTextList.add(text);
			}
		}
		return eleTextList;

	}

	public List<String> getElementAttributeList(By locator, String attributeValue) {
		List<WebElement> eleutil = getElements(locator);
		List<String> eleAttrList = new ArrayList<String>();
		for (WebElement e : eleutil) {
			String attrval = e.getAttribute(attributeValue);
			System.out.println(attrval);
			eleAttrList.add(attrval);

		}
		return eleAttrList;

	}

	// ***************************Dropdown Utils***************************//

	public void doSelectVisibleText(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(value);
	}

	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public List<String> doGetDropdownOptions(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> OptionList = select.getOptions();
		List<String> OptionValueList = new ArrayList<String>();
		System.out.println(OptionList.size());

		for (WebElement e : OptionList) {
			String text = e.getText();
			// System.out.println(text);
			OptionValueList.add(text);

		}
		return OptionValueList;
	}

	public void doSelectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> OptionList = select.getOptions();
		for (WebElement e : OptionList) {
			String Text = e.getText();
			if (Text.equals(value)) {
				e.click();
				break;
			}
		}

		List<WebElement> countryList = driver.findElements(By.xpath("//select[@id='Form_submitForm_Country']/option"));

		for (WebElement e : countryList) {
			String text = e.getText();
			if (text.equals("India")) {
				e.click();
				break;
			}

		}

	}

	public void getSuggestion(By locator, String value) {
		List<WebElement> SuggestionOptionList = getElements(locator);
		for (WebElement e : SuggestionOptionList) {
			String Text = e.getText();
			if (Text.equals(value)) {
				e.click();
				break;
			}
		}

	}

	// ********************************Actions Util*************************//

	// For two level mouse over

	public void selectSubElement(By parentMenu, By childMenu) throws InterruptedException {// these method is for Two
																							// Element
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(2000);
		getElement(childMenu).click();

	}

	// For three level mouse over

	public void selectSubElements(By parentMenu, By childMenu, By SubChildMenu) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(2000);

		act.moveToElement(getElement(childMenu)).perform();
		Thread.sleep(2000);

		getElement(SubChildMenu).click();
	}

	// For four level mouse over

	public void selectSubElementslevel4(By parentMenu, By childMenu1, By childMenu2, By childMenu3)
			throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenu)).perform();
		Thread.sleep(2000);

		act.moveToElement(getElement(childMenu1)).perform();
		Thread.sleep(2000);

		act.moveToElement(getElement(childMenu2)).perform();
		Thread.sleep(2000);

		getElement(childMenu3).click();
	}

	public int getRightClickOptionListCount(By rightClickBtn, By rightClickOptions) {
		return getRightClickOptionList(rightClickBtn, rightClickOptions).size();
	}

	public void doContextMethod(By locator) {
		Actions act = new Actions(driver);
		act.contextClick(getElement(locator)).perform();
	}

	public void selectRightClickMenu(By rightClickBtn, By rightClickOptions, String value) {
		doContextMethod(rightClickBtn);
		List<WebElement> listOptions = getElements(rightClickOptions);
		System.out.println(listOptions.size());
		for (WebElement e : listOptions) {
			String text = e.getText();
			// System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public List<String> getRightClickOptionList(By rightClickBtn, By rightClickOptions) {
		doContextMethod(rightClickBtn);
		List<WebElement> listOptions = getElements(rightClickOptions);
		List<String> listitemsList = new ArrayList<String>();
		// System.out.println(listOptions.size());
		for (WebElement e : listOptions) {
			String Text = e.getText();
			// System.out.println(Text);
			listitemsList.add(Text);
		}
		return listitemsList;
	}

	// Clicks in the middle of the given element. Equivalent to:
	// Actions.moveToElement(onElement).click()
	public void getActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	// Equivalent to calling: Actions.click(element).sendKeys(keysToSend).
	public void getActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	// ******************************wait utils*****************************//
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeOut
	 * @param pollingTime(By default=500ms)
	 * @return
	 */
	public WebElement waitForElementPresent(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @param pollingTime(By default=500ms)
	 * @return
	 */
	public WebElement waitForElementToBeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible.
	 * 
	 * @param locator
	 * @param timeOut
	 * @param polling time
	 * @return
	 */

	// default polling time in selenium is 500milli sec

	public WebElement waitForElementPresent(By locator, int timeOut, long pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(pollingTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @param pollingTime
	 * @return
	 */

	public WebElement waitForElementToBeVisible(By locator, int timeOut, long pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(pollingTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public Alert waitForAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(int timeOut) {
		waitForAlert(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForAlert(timeOut).dismiss();
	}

	public String getAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();
	}

	public String waitForUrl(int timeOut, String Fraction) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlContains(Fraction))) {
			return driver.getCurrentUrl();
		}
		return null;
	}

	public String waitForUrlToBe(int timeOut, String UrlVal) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.urlToBe(UrlVal))) {
			return driver.getCurrentUrl();
		}
		return null;
	}

	public String waitForTitleContains(int timeOut, String FractionTitle) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(FractionTitle))) {
			return driver.getTitle();
		}
		return null;
	}

	public String waitForTitleIs(int timeOut, String TitleVal) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(TitleVal))) {
			return driver.getTitle();
		} else {
			System.out.println("title is not correct.....");
			return null;
		}

	}

	public WebDriver waitForFrameByLocator(int timeOut, By frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public WebDriver waitForFrameByIndex(int timeOut, int frameIndex) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public WebDriver waitForFrameByString(int timeOut, String frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public WebDriver waitForFrameByWebElement(int timeOut, WebElement frameLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	/*
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 */

	public void ClickWhenReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	/*
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 */

	public void ClickWhenElementReady(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(getElement(locator))).click();
	}

	public void printAllElements(By locator, int timeOut) {
		List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut);
		for (WebElement e : eleList) {
			System.out.println(e.getText());
		}
	}

	public List<String> getElementTextListByWait(By locator, int timeOut) {
		List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;
	}

	public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	// **************************Custom Waits ********************************//

	public WebElement retryingElement(By locator, int timeOut) {
		WebElement element = null;

		int attempts = 0;

		while (attempts < timeOut) {
			try {
				element = getElement(locator);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("element is not found in attempts" + attempts + "sec" + ":" + timeOut);
				try {
					Thread.sleep(500);// default interval time
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			attempts++;

		}
		if (element == null) {
			try {
				throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
			} catch (Exception e) {
				System.out.println("element not found Exception... tried for:" + timeOut + "secs with the interval of "
						+ 500 + "ms");
			}
		}
		return element;

	}

	public WebElement retryingElement(By locator, int timeOut, int intervalTime) {
		WebElement element = null;

		int attempts = 0;

		while (attempts < timeOut) {
			try {
				element = getElement(locator);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("element is not found in attempts" + attempts + "sec" + ":" + timeOut);
				try {
					Thread.sleep(intervalTime);// custom interval time
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			attempts++;

		}
		if (element == null) {
			try {
				throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
			} catch (Exception e) {
				System.out.println("element not found Exception... tried for:" + timeOut + "secs with the interval of "
						+ intervalTime + "ms");
			}
		}
		return element;

	}

	public WebDriver waitForFrameWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchFieldException.class);

		return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public Alert waitForAlertPresentWithFluentWait(int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoAlertPresentException.class);

		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public WebElement waitForElementPresentWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(NoSuchElementException.class, ElementNotInteractableException.class);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	public void waitForElementVisibleWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

}
