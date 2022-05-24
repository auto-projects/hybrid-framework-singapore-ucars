package sg.ucars.nonlogin;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import pageObjects.sin.ucars.CarBuyerHomePageObject;
import pageObjects.sin.ucars.LoginCarBuyerPageObject;
import pageObjects.sin.ucars.PageGenerator;
import pageObjects.sin.ucars.UcarsHomePageObject;
import reportConfig.ExtentTestManager;
import utilities.DataUtil;

public class Homepage_UI_Non_Login_Signup extends BaseTest {

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-Condition - Step 01: Open browser '" + browserName + "' and navigate to '" + appUrl + "'");
		driver = getBrowserDriver(browserName, appUrl);

		ucarsHomePage = PageGenerator.getHomePage(driver);
		fakeData = DataUtil.getData();
		showBrowserConsoleLogs(driver);

	}

	@Test
	public void Ucars_01_Homepage_Stay_On_UCARS(Method method) {
		ExtentTestManager.startTest(method.getName(), "'STAY ON UCARS' HOMEPAGE");
		
		ExtentTestManager.getTest().log(Status.INFO, "Ucars_01_Homepage_Stay_On_UCARS - Step 01: Choose 'Stay on UCARS' from the pop-up");
		ucarsHomePage.clickOnButtonAtPopupByText(driver, "Stay on UCARS");
		ucarsHomePage.sleepInSecond(3);

		ExtentTestManager.getTest().log(Status.INFO, "Ucars_01_Homepage_Stay_On_UCARS - Step 02: Get all values at top-bar menu of homepage");
		ucarsHomePage.getAllValuesAtOptionsByClass(driver, "top-bar");
		
		ExtentTestManager.getTest().log(Status.INFO, "Ucars_01_Homepage_Stay_On_UCARS - Step 03: Get all links within all icons at top-bar menu of homepage");
		ucarsHomePage.getAllLinksWithinOptionsByClass(driver, "top-bar");
		
		ExtentTestManager.getTest().log(Status.INFO, "Ucars_01_Homepage_Stay_On_UCARS - Step 04: Get all values at nav-bar menu of homepage");
		ucarsHomePage.getAllValuesAtOptionsByID(driver, "NavBar");
		
		ExtentTestManager.getTest().log(Status.INFO, "Ucars_01_Homepage_Stay_On_UCARS - Step 05: Get all values at search options of homepage");
		ucarsHomePage.getAllValuesAtOptionsByClass(driver, "search-options");
		
		ExtentTestManager.getTest().log(Status.INFO, "Ucars_01_Homepage_Stay_On_UCARS - Step 06: Get all values at category of homepage");
		ucarsHomePage.getAllLinksWithinOptionsByClass(driver, "category");
		
		ExtentTestManager.getTest().log(Status.INFO, "Ucars_01_Homepage_Stay_On_UCARS - Step 07: Get all values at 'New Cars By Brand' of homepage");
		ucarsHomePage.getAllValuesAtOptionsByID(driver, "explore-brand");
		
		ExtentTestManager.getTest().log(Status.INFO, "Ucars_01_Homepage_Stay_On_UCARS - Step 08: Click on 'Type' button at 'New Cars By Type' of homepage");
		ucarsHomePage.clickOnButtonAtNewCarsByBrandOrType(driver, "tab-1");
		
		ExtentTestManager.getTest().log(Status.INFO, "Ucars_01_Homepage_Stay_On_UCARS - Step 09: Get all values at 'New Cars By Brand' of homepage");
		ucarsHomePage.getAllImagesAndNamesAtNewCarsByType(driver, "vehicle-type");
		
		ExtentTestManager.getTest().log(Status.INFO, "Ucars_01_Homepage_Stay_On_UCARS - Step 10: Get all values at 'Latest COE Prices' of homepage");
		ucarsHomePage.getAllValuesAtOptionsByClass(driver, "table");

	}
	@Test
	public void Ucars_02_Homepage_Redirect_To_CarBuyer(Method method) {
		ExtentTestManager.startTest(method.getName(), "'REDIRECT TO CAR-BUYER' HOMEPAGE");
		
		ExtentTestManager.getTest().log(Status.INFO, "Ucars_02_Homepage_Redirect_To_CarBuyer - Step 01: Open again Homepage");
		ucarsHomePage.cleanBrowserCacheAndOpenHomepage(driver);
		ucarsHomePage = PageGenerator.getHomePage(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "Ucars_02_Homepage_Redirect_To_CarBuyer - Step 02: Choose 'Redirect To CarBuyer' from the pop-up");
		ucarsHomePage.clickOnButtonAtPopupByText(driver, "Redirect to CarBuyer");
		ucarsHomePage.sleepInSecond(3);
		carBuyerHomePage = PageGenerator.getCarBuyerHomePage(driver);
	}
	@Test
	public void Ucars_03_Sign_up(Method method) {
		ExtentTestManager.startTest(method.getName(), "SIGN UP");
		
		ExtentTestManager.getTest().log(Status.INFO, "Ucars_03_Sign_up - Step 01: Click on 'Sign up' button");
		ucarsHomePage.clickOnButtonByText(driver, "Sign up");
		
		ExtentTestManager.getTest().log(Status.INFO, "Ucars_03_Sign_up - Step 02: Click on 'Redirect to CarBuyer' from the pop-up");
		ucarsHomePage.clickOnButtonByText(driver, "Redirect to CarBuyer");
		carBuyerHomePage = PageGenerator.getCarBuyerHomePage(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "Ucars_03_Sign_up - Step 03: Get link of 'CARBUYER.COM.SG' logo");
		carBuyerHomePage.getImageLinkOfLogo();
		
		ExtentTestManager.getTest().log(Status.INFO, "Ucars_03_Sign_up - Step 04: Click on 'Log in' button");
		carBuyerHomePage.clickOnButtonByText(driver, "Log in");
		loginCarBuyerPage = PageGenerator.getLoginCarBuyerPage(driver);
		
	}

	@AfterClass
	public void cleanBrowser() {
		log.info("► Close Browsers and Drivers ►");
		cleanDriverInstance();
	}

	WebDriver driver;
	DataUtil fakeData;
	UcarsHomePageObject ucarsHomePage;
	CarBuyerHomePageObject carBuyerHomePage;
	LoginCarBuyerPageObject loginCarBuyerPage;
}
