package pageObjects.sin.ucars;

import org.openqa.selenium.WebDriver;

public class PageGenerator {
	public static UcarsHomePageObject getHomePage(WebDriver driver) {
		return new UcarsHomePageObject(driver);
	}

	public static CarBuyerHomePageObject getCarBuyerHomePage(WebDriver driver) {
		return new CarBuyerHomePageObject(driver);
	}

	public static LoginCarBuyerPageObject getLoginCarBuyerPage(WebDriver driver) {
		return new LoginCarBuyerPageObject(driver);
	}

	public static SignupCarBuyerPageObject getSignupCarBuyerPage(WebDriver driver) {
		return new SignupCarBuyerPageObject(driver);
	}

	public static ForgotPasswordPageObject getForgotPasswordPage(WebDriver driver) {
		return new ForgotPasswordPageObject(driver);
	}

}
