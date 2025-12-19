package TestNg;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class Demo extends Base {

	@Test(description = "Verify 1st Tc", enabled=true,groups= {"Smoke","Regression"})
	public void test1() {
		System.out.println("This is my first TestNg Project");
	}
	
	@Test(description = "Verify 2nd Tc")
	public void test2() {
		System.out.println("This is my 2nd TC");
		//System.out.println(10/0);
	}
	
	@Test(priority=2, groups="Regression")
	public void test3() {
		System.out.println("This is my 3rd TC");
	}
	
	@BeforeClass
	public void beforeClass() {
		System.out.println("BeforeClass executed - Test user data loaded");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("AfterClass executed - Test user data deleted");
	}
	
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
