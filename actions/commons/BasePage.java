package commons;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.network.Network;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageUIs.sin.ucars.BasePageUI;

public class BasePage {

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	// Selenium Web Browser function
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	protected String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	protected String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	protected String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();

	}

	public void setAllCookies(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}

	}

	protected void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	protected void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	// Alert
	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	protected String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	protected void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	// Windows
	protected void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	protected void switchToWindowByTitle(WebDriver driver, String tabTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(tabTitle)) {
				break;
			}
		}
	}

	protected void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	// Selenium Web Element Function
	public By getByXpath(String xpathxpathLocator) {
		return By.xpath(xpathxpathLocator);
	}

	// Dynamic Locator
	private String getDynamicXpath(String xpathLocator, String... dynamicValues) {
		xpathLocator = String.format(xpathLocator, (Object[]) dynamicValues);
		return xpathLocator;
	}

	public WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElements(By.xpath(xpathLocator));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return driver.findElements(By.xpath(getDynamicXpath(xpathLocator, dynamicValues)));
	}

	public void clickToElement(WebDriver driver, String xpathLocator) {
		getWebElement(driver, xpathLocator).click();
	}

	public void clickToElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).click();
	}

	public void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue) {
		WebElement element = getWebElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(textValue);
	}

	public void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	protected void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String itemText) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		select.selectByVisibleText(itemText);
	}

	// selectItemInDefaultDropdown(driver,
	// BasePageNopCommerceUI.DYNAMIC_DROPDOWN_BY_NAME, itemText, dropdownName);
	protected void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String itemText,
			String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
		select.selectByVisibleText(itemText);
	}

	/**
	 * Default Dropdown - Get Disabled Text then Verify
	 * 
	 * @param driver
	 * @param xpathLocator
	 * @param itemText
	 * @param dynamicValues
	 * @return
	 */
	protected String getSelectedItemByTextInDefaultDropdown(WebDriver driver, String xpathLocator, String itemText,
			String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
		select.selectByVisibleText(itemText);
		return select.getFirstSelectedOption().getText();
	}

	protected String getSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}

	protected String getSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}

	protected boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.isMultiple();
	}

	protected void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath,
			String expectedTextItem) {
		getWebElement(driver, parentXpath).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
	}

	protected String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}

	protected String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName,
			String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).getText().trim();
	}

	public String getElementText(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).getText().trim();
	}

	protected String getElementCssvalue(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	}

	protected String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	protected int getElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElement(driver, xpathLocator).size();
	}

	public int getElementSize(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).size();
	}

	protected void checkToDefaultCheckboxOrRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void checkToDefaultCheckboxOrRadio(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToDefaultCheckboxOrRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (element.isSelected()) {
			element.click();
		}
	}

	protected void uncheckToDefaultCheckboxOrRadio(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		try {
			return getWebElement(driver, xpathLocator).isDisplayed();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String xpathLocator) {
		System.out.println("Start time = " + new Date().toString());
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = getListWebElement(driver, xpathLocator);
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);

		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible on UI");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else {
			System.out.println("Element in DOM and visible on UI");
			return false;
		}
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public boolean isElementDisplayed(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).isDisplayed();
	}

	protected boolean isElementEnabled(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}

	protected boolean isElementEnabled(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).isEnabled();
	}

	protected boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();
	}

	protected boolean isElementSelected(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).isSelected();
	}

	protected void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));

	}

	protected void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	// Actions
	protected void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).perform();
	}

	protected void hoverMouseToElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues))).perform();
	}

	protected void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, xpathLocator), key).perform();
	}

	protected void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)), key).perform();
	}

	protected void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	protected void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	protected void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	protected void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}

	protected void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, xpathLocator));
	}

	public boolean isJQueryAjaxLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery  != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	protected String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
				getWebElement(driver, xpathLocator));
	}

	protected boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	protected boolean isImageLoaded(WebDriver driver, String xpathLocator, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)));
		return status;
	}

	// Wait
	public void waitForElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLocator)));
	}

	public void waitForElementVisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(getDynamicXpath(xpathLocator, dynamicValues))));
	}

	public void waitForAllElementVisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpathLocator)));
	}

	public void waitForAllElementVisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(By.xpath(getDynamicXpath(xpathLocator, dynamicValues))));
	}

	public void waitForElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathLocator)));
	}

	public void waitForElementInvisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath(getDynamicXpath(xpathLocator, dynamicValues))));
	}

	public void waitForAllElementInvisible(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, xpathLocator)));
	}

	public void waitForAllElementInvisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions
				.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues))));
	}

	public void waitForElementClickable(WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathLocator)));
	}

	public void waitForElementClickable(WebDriver driver, String xpathLocator, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
		explicitWait
				.until(ExpectedConditions.elementToBeClickable(By.xpath(getDynamicXpath(xpathLocator, dynamicValues))));
	}

	// UCARS HOMEPAGE
	public void clickOnButtonAtPopupByText(WebDriver driver, String buttonOnPopupByText) {
		waitForElementClickable(driver, BasePageUI.BUTTON_BY_TEXT_ON_POPUP, buttonOnPopupByText);
		clickToElement(driver, BasePageUI.BUTTON_BY_TEXT_ON_POPUP, buttonOnPopupByText);

	}

	public boolean getAllValuesAtOptionsByClass(WebDriver driver, String divByClass) {
		List<WebElement> nameElements = getListWebElement(driver, BasePageUI.OPTIONS_DIV_BY_CLASS, divByClass);

		List<String> nameValues = new ArrayList<String>();

		for (WebElement name : nameElements) {
			nameValues.add(name.getText());
		}
		List<String> nameValuesClone = new ArrayList<String>();
		for (String profileList : nameValues) {
			nameValuesClone.add(profileList);
		}
		System.out.println("►►►►►►►►►►►►►►►►►►►►►►►►►►►►►►");
		for (String profileList : nameValuesClone) {
			System.out.println(profileList);
		}

		return nameValues.equals(nameValuesClone);

	}

	public boolean getAllValuesAtOptionsByID(WebDriver driver, String divByID) {
		List<WebElement> nameElements = getListWebElement(driver, BasePageUI.OPTIONS_DIV_BY_ID, divByID);

		List<String> nameValues = new ArrayList<String>();

		for (WebElement name : nameElements) {
			nameValues.add(name.getText());
		}
		List<String> nameValuesClone = new ArrayList<String>();
		for (String profileList : nameValues) {
			nameValuesClone.add(profileList);
		}
		System.out.println("►►►►►►►►►►►►►►►►►►►►►►►►►►►►►►");
		for (String profileList : nameValuesClone) {
			System.out.println(profileList);
		}

		return nameValues.equals(nameValuesClone);

	}

	public boolean getAllLinksWithinOptionsByClass(WebDriver driver, String divByClass) {
		List<WebElement> nameElements = getListWebElement(driver, BasePageUI.LINKS_WITHIN_OPTIONS_BY_CLASS, divByClass);

		List<String> nameValues = new ArrayList<String>();

		for (WebElement name : nameElements) {
			nameValues.add(name.getAttribute("href"));
		}
		List<String> nameValuesClone = new ArrayList<String>();
		for (String profileList : nameValues) {
			nameValuesClone.add(profileList);
		}
		System.out.println("►►►►►►►►►►►►►►►►►►►►►►►►►►►►►►");
		for (String profileList : nameValuesClone) {
			System.out.println(profileList);
		}

		return nameValues.equals(nameValuesClone);

	}

	public void clickOnButtonAtNewCarsByBrandOrType(WebDriver driver, String buttonByID) {
		waitForElementClickable(driver, BasePageUI.BUTTON_AT_NEW_CARS_BRAND_OR_TYPE_BY_ID, buttonByID);
		clickToElement(driver, BasePageUI.BUTTON_AT_NEW_CARS_BRAND_OR_TYPE_BY_ID, buttonByID);

	}

	public boolean getAllImagesAndNamesAtNewCarsByType(WebDriver driver, String byID) {
		List<String> imgAndNameValuesClone = new ArrayList<String>();

		List<WebElement> imgElements = getListWebElement(driver, BasePageUI.IMG_BY_ID, byID);
		List<String> imgValues = new ArrayList<String>();

		for (WebElement img : imgElements) {
			imgValues.add(img.getAttribute("src"));
		}
		for (String imgAndNameList : imgValues) {
			imgAndNameValuesClone.add(imgAndNameList);
		}
		List<WebElement> nameElements = getListWebElement(driver, BasePageUI.NAME_OF_IMG_BY_ID, byID);
		List<String> nameValues = new ArrayList<String>();
		for (WebElement name : nameElements) {
			nameValues.add(name.getText());
		}
		for (String imgAndNameList : nameValues) {
			imgAndNameValuesClone.add(imgAndNameList);
		}
		System.out.println("►►►►►►►►►►►►►►►►►►►►►►►►►►►►►►");
		for (String imgAndNameList : imgAndNameValuesClone) {
			System.out.println(imgAndNameList);

		}
		return imgAndNameValuesClone.equals(imgAndNameValuesClone);

	}

	public void cleanBrowserCacheAndOpenHomepage(WebDriver driver) {
		driver.manage().deleteAllCookies();
		driver.get("chrome://settings/clearBrowserData");        
		driver.findElement(By.cssSelector("* /deep/ #clearBrowsingDataConfirm")).click();
		backToPage(driver);
	}

	public long longTimeout = GlobalConstants.LONG_TIMEOUT;

}
