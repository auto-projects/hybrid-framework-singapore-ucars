package pageObjects.sin.ucars;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.sin.ucars.SignupCarBuyerPageUI;

public class SignupCarBuyerPageObject extends BasePage {
	WebDriver driver;

	public SignupCarBuyerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void hideTheHotJarBanner() {
		waitForElementClickable(driver, SignupCarBuyerPageUI.HOT_JAR_BANNER);
		clickOnElement(driver, SignupCarBuyerPageUI.HOT_JAR_BANNER);

	}

}
