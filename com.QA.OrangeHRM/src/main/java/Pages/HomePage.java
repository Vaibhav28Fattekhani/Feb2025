package Pages;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.SeleniumUtils;

public class HomePage {
	private WebDriver driver;

	@FindBy(xpath = "//span[text()='Admin']")
	private WebElement admin;

	@FindBy(xpath = "//span[text()='PIM']")
	private WebElement pim;

	@FindBy(xpath = "//span[text()='Time']")
	private WebElement time;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public boolean visibilityOfAdminTab() {
		return SeleniumUtils.seleniIsdisplayed(driver, admin);
	}

	public boolean visibilityOfPimTab() {
		return SeleniumUtils.seleniIsdisplayed(driver, pim);
	}

	public boolean visibilityOfTimeTab() {
		return SeleniumUtils.seleniIsdisplayed(driver, time);
	}

	public void clickToAdminTab() {
		SeleniumUtils.seleniumclick(driver, admin);
	}

	public String getPageTile() {
		return driver.getTitle();
	}

}
