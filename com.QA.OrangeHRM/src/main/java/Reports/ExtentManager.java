package Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	static String reportpath = System.getProperty("user.dir") + "/Reports/ExtentReport.html";

	public static void initReports() {
		if (extent == null) {
			ExtentSparkReporter reporter = new ExtentSparkReporter(reportpath);
			extent = new ExtentReports();
			extent.attachReporter(reporter);
		}
	}

	public static void flushReports() {
		if (extent != null) {
			extent.flush();
		}
	}

	public static ExtentTest createTest(String testName) {
		ExtentTest extentTest = extent.createTest(testName);
		test.set(extentTest);
		return extentTest;
	}

	public static ExtentTest getTest() {
		return test.get();
	}

}
