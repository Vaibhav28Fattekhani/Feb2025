package Tests;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Base.BaseTest;
import Config.ConfigReader;
import Listener.TestListener;
import Pages.AdminPage;
import Pages.HomePage;
import Pages.LoginPage;

@Listeners(TestListener.class)
public class AdminPageTest extends BaseTest {

	LoginPage loginpage;
	HomePage homepage;
	AdminPage adminpage;

	@Test(priority = 1)
	public void check_AdminTabIsDisplyed() throws InterruptedException, IOException {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		adminpage = new AdminPage(driver);
		loginpage.Login(ConfigReader.get("userid"), ConfigReader.get("password"));
		homepage.clickToAdminTab();
		System.out.println(adminpage.visibilityOfUserManagmentTab());

	}

	@Test(priority = 2)
	public void check_AdminTabIsPim() throws InterruptedException, IOException {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		adminpage = new AdminPage(driver);
		loginpage.Login(ConfigReader.get("userid"), ConfigReader.get("password"));
		homepage.clickToAdminTab();
		System.out.println(adminpage.visibilityOfJobTab());

	}
}
