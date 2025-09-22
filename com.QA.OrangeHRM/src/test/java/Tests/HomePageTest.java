package Tests;

import java.io.IOException;

import org.testng.annotations.Test;

import Base.BaseTest;
import Config.ConfigReader;
import Pages.HomePage;
import Pages.LoginPage;

public class HomePageTest extends BaseTest {
	LoginPage loginpage;
	HomePage homepage;

	@Test(priority = 1)
	public void check_AdminTabIsDisplyed() throws InterruptedException, IOException {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.Login(ConfigReader.get("userid"), ConfigReader.get("password"));
		System.out.println(homepage.visibilityOfAdminTab());
	}

	@Test(priority = 2)
	public void check_AdminTabIsPim() throws InterruptedException, IOException {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.Login(ConfigReader.get("userid"), ConfigReader.get("password"));
		System.out.println(homepage.visibilityOfPimTab());

	}

	@Test(priority = 3)
	public void check_AdminTabIsTime() throws InterruptedException, IOException {
		loginpage = new LoginPage(driver);
		homepage = new HomePage(driver);
		loginpage.Login(ConfigReader.get("userid"), ConfigReader.get("password"));
		System.out.println(homepage.visibilityOfTimeTab());

	}
}
