package Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static Properties properties;

	public static void loadConfig() throws IOException {
		String propertyfilepath = System.getProperty("user.dir") + "/src/main/java/Resources/Config.Properties";
		FileInputStream fis = new FileInputStream(propertyfilepath);
		properties = new Properties();
		properties.load(fis);

	}

	public static String get(String key) throws IOException {

		return properties.getProperty(key);
	}

}
