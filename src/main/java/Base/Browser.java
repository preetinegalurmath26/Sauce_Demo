package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {

	public static Properties prop = new Properties();
	public static WebDriver driver;
	public static ExtentReports reports = new ExtentReports();;

	@BeforeSuite
	public void initiateExtentReporting() {
		String time = Instant.now().toString();
		time = time.replace(":", "-").replace(".", "-");
		System.out.println(time);
		ExtentSparkReporter reporter = new ExtentSparkReporter(
				System.getProperty("user.dir") + "/Reports/ExtentReport_1" + time + ".html");
		// reports=new ExtentReports();
		reports.attachReporter(reporter);
	}

	@AfterSuite
	public void stopReport() {
		reports.flush();
	}

	@BeforeMethod
	public void LaunchBrowser() {

		try {

			FileInputStream fis = new FileInputStream(
					"/Users/Selenium_Automation/Sauce_Demo/src/main/java/Configurations/Config.properties");

			prop.load(fis);

		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		}

		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			// Enable JavaScript by not disabling it
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.default_content_setting_values.javascript", 1); // 1 = Allow
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browser.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			throw new InvalidArgumentException("Invalid browser:" + browser);
		}

		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
		driver.manage().deleteAllCookies();

		String implWait = prop.getProperty("IMPLICIT_WAIT");
		long iw = Long.parseLong(implWait);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(iw));
	}

	@AfterMethod
	public void CloseBrowser() {

		// Closes all browser windows and ends the session
		driver.quit();
	}

	public void Click(By locator) {
		driver.findElement(locator).click();
	}

	public String getTextFromElement(By locator) {
		return driver.findElement(locator).getText();
	}

	public Media getScreenshot() {
		TakesScreenshot ts = (TakesScreenshot) driver;

		return MediaEntityBuilder.createScreenCaptureFromBase64String(ts.getScreenshotAs(OutputType.BASE64)).build();
	}
}
