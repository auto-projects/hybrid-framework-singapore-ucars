package pageObjects.sin.ucars;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.sin.ucars.LoginCarBuyerPageUI;

public class LoginCarBuyerPageObject extends BasePage {
	WebDriver driver;

	public LoginCarBuyerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnSignupLink() {
		waitForElementClickable(driver, LoginCarBuyerPageUI.SIGN_UP_LINK);
		clickOnElement(driver, LoginCarBuyerPageUI.SIGN_UP_LINK);

	}

	public boolean getAllMessagesOnNotiIcon() {
		List<WebElement> nameElements = getListWebElement(driver, LoginCarBuyerPageUI.NOTIFICATION_ICON);

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

	public void clickOnForgotPasswordLink() {
		waitForElementClickable(driver, LoginCarBuyerPageUI.FORGOT_PASSWORD_LINK);
		clickOnElement(driver, LoginCarBuyerPageUI.FORGOT_PASSWORD_LINK);

	}

}
