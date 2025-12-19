package TestNg;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class Base {

	@BeforeMethod
	public void LaunchBrowser() {
		System.out.println("Opening browser");
	}
	
	@AfterMethod
	public void CloseBrowser() {
		System.out.println("Closing browser");
	}
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("@BeforeSuite executed - Reporting started");
	}
	
	@AfterSuite
	public void aftetrSuite() {
		System.out.println("@AfterSuite executed - Reporting closure");
	}
	
}
