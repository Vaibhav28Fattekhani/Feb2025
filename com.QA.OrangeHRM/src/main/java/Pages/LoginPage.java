package Pages;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.SeleniumUtils;

public class LoginPage {
	private WebDriver driver;

	@FindBy(xpath = "//input[@name='username']")
	private WebElement username;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement loginbtn;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void inputUsername(String name) {
		SeleniumUtils.sendKeys(driver, username, name);

	}

	public void inputPassword(String pass) {
		SeleniumUtils.sendKeys(driver, password, pass);
	}

	public void clickToLoginBtn() {
		SeleniumUtils.seleniumclick(driver, loginbtn);
	}

	public boolean IsusernamefieldDisplayed() {
		return SeleniumUtils.seleniIsdisplayed(driver, username);

	}

	public boolean IsPasswordfieldDisplayed() {
		return SeleniumUtils.seleniIsdisplayed(driver, password);
	}

	public boolean IsLoginBtnDisplayed() {
		return SeleniumUtils.seleniIsdisplayed(driver, loginbtn);
	}

	public String getLoginPageTile() {
		return driver.getTitle();
	}

	public void Login(String name, String pass) throws InterruptedException {
		inputUsername(name);
		Thread.sleep(4000);
		inputPassword(pass);
		Thread.sleep(4000);
		clickToLoginBtn();
	}

	public void InvalidLogin(String name, String pass) {
		inputUsername(name);
		inputPassword(pass);
		clickToLoginBtn();
	}

}
