package Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base.BaseTest;
import Config.ConfigReader;
import Pages.LoginPage;
import Utility.ExcelUtils;

@Listeners(Listener.TestListener.class)
public class LoginTest extends BaseTest {
	LoginPage loginpage; // reference
	SoftAssert soft;
	String dataFilepath = System.getProperty("user.dir") + "/src/test/testdata/dataFile.xlsx";

	@Test(priority = 1, enabled = true)
	public void check_LoginPageTitle() {
		ExcelUtils.loadExcel(dataFilepath);
		loginpage = new LoginPage(driver);
		String expectedTile = ExcelUtils.getData("LoginCred", 1, 2);
		String actualTitle = loginpage.getLoginPageTile();
		System.out.println(actualTitle);
		Assert.assertEquals(actualTitle, expectedTile);
	}

	@Test(priority = 2, enabled = true)
	public void check_ValidLogin() throws IOException, InterruptedException {
		loginpage = new LoginPage(driver);// Initialize
		boolean actualusernamefield = loginpage.IsusernamefieldDisplayed();
		Assert.assertTrue(actualusernamefield);
		boolean actualpasswordfield = loginpage.IsPasswordfieldDisplayed();
		Assert.assertFalse(actualpasswordfield); // fail
		boolean actualloginbtnfield = loginpage.IsLoginBtnDisplayed();
		Assert.assertTrue(actualloginbtnfield);
		loginpage.Login(ConfigReader.get("userid"), ConfigReader.get("password"));
		Thread.sleep(10000);
	}

	@Test(priority = 3, enabled = true)
	public void check_ValidLoginWithSoftAssert() throws InterruptedException, IOException {
		soft = new SoftAssert();
		loginpage = new LoginPage(driver);// Initialize
		boolean actualusernamefield = loginpage.IsusernamefieldDisplayed();
		soft.assertTrue(actualusernamefield);
		boolean actualpasswordfield = loginpage.IsPasswordfieldDisplayed();
		soft.assertFalse(actualpasswordfield); // fail
		boolean actualloginbtnfield = loginpage.IsLoginBtnDisplayed();
		soft.assertTrue(actualloginbtnfield);
		loginpage.Login(ConfigReader.get("userid"), ConfigReader.get("password"));
		Thread.sleep(10000);
		soft.assertAll("Assertion execute");
	}

	@Test(priority = 4, enabled = true)
	public void invalid_ValidLogin() throws IOException, InterruptedException {
		loginpage = new LoginPage(driver);// Initialize
		loginpage.InvalidLogin(ConfigReader.get("password"), ConfigReader.get("userid"));
		Thread.sleep(10000);
	}

	@Test(priority = 5)
	public void check_ValidLogin_Excel() throws IOException, InterruptedException {
		ExcelUtils.loadExcel(dataFilepath);
		String username = ExcelUtils.getData("LoginCred", 1, 0);
		String pass = ExcelUtils.getData("LoginCred", 1, 1);
		loginpage = new LoginPage(driver);// Initialize
		loginpage.Login(username, pass);
		Thread.sleep(10000);
	}

	@DataProvider
	public Object[][] getDataForInvalidLogin() {
		ExcelUtils.loadExcel(dataFilepath);
		return ExcelUtils.getSheetData("DataDriven");
	}

	@Test(priority = 6, enabled = false, dataProvider = "getDataForInvalidLogin")
	public void check_InvalidLogin_WithMultipleset(String username, String pass)
			throws IOException, InterruptedException {
		ExcelUtils.loadExcel(dataFilepath);
		loginpage = new LoginPage(driver);// Initialize
		loginpage.Login(username, pass);
		Thread.sleep(10000);
	}

}
