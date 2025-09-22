package Base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import Config.ConfigReader;
import Driver.DriverFactory;
import Reports.ExtentManager;
import Utility.SeleniumUtils;

public class BaseTest {
	protected WebDriver driver;

	@BeforeMethod
	public void setUp() throws IOException {
		ConfigReader.loadConfig();
		driver = DriverFactory.initDriver();
		SeleniumUtils.deleteAllcookies(driver);
		driver.get(ConfigReader.get("baseUrl"));
		SeleniumUtils.maximizeWindow(driver);
		SeleniumUtils.pageLoadTimeout(driver);
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
