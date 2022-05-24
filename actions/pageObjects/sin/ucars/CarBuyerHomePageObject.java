package pageObjects.sin.ucars;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.sin.ucars.CarBuyerHomePageUI;

public class CarBuyerHomePageObject extends BasePage {
	WebDriver driver;

	public CarBuyerHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean getImageLinkOfLogo() {
		List<WebElement> nameElements = getListWebElement(driver, CarBuyerHomePageUI.LOGO_IMG);
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

}
