package pageUIs.sin.ucars;

public class BasePageUI {
	public static final String BUTTON_BY_TEXT_ON_POPUP = "//button[@type='button']/span[contains(text(),'%s')]";
	public static final String OPTIONS_DIV_BY_CLASS = "//div[@class='%s']";
	public static final String LINKS_WITHIN_OPTIONS_BY_CLASS = "//div[@class='%s']//a";
	public static final String OPTIONS_DIV_BY_ID = "//div[@id='%s']";

	public static final String BUTTON_AT_NEW_CARS_BRAND_OR_TYPE_BY_ID = "//div[@class='explore-grid section']//parent::div[@id='%s']";
	
	public static final String IMG_BY_ID = "//div[@id='%s']//img";
	public static final String NAME_OF_IMG_BY_ID = "//div[@id='%s']//h2";
}
