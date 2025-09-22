package Driver;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import Config.ConfigReader;

public class DriverFactory {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>(); // To handle multiple threads

	public static WebDriver initDriver() throws IOException {
		String browser = ConfigReader.get("browser");
		if (browser.equalsIgnoreCase("chrome")) // True
		{
			ChromeOptions options = new ChromeOptions();
			boolean isheadless = Boolean.parseBoolean(ConfigReader.get("headless"));
			if (isheadless) // true or false
			{
				options.addArguments("--headless=new");
			}
			driver.set(new ChromeDriver(options));
		} else if (browser.equalsIgnoreCase("edge")) {

		}
		return getDriver();

	}

	public static WebDriver getDriver() {
		return driver.get();
	}

}
