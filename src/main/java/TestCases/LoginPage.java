package TestCases;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import Base.Browser;
import Locators.LoginPageLocators;
import Utilities.TestData;

public class LoginPage extends Browser {

	@Test(description = "Verify user enters valid credentials and successful login after clicking the login button", groups = "Regression")
	public void VerifySuccessfullLogin() {
		ExtentTest test = reports.createTest("Verify Successful Login");

		try {
			test.info("URL: " + driver.getCurrentUrl());

			String title = driver.getTitle();
			Assert.assertEquals(title, "Swag Labs");
			test.pass("Title is Validated Successfully:" + title, getScreenshot());

			LoginPageLocators loginpagelocators = new LoginPageLocators(driver);
			loginpagelocators.enterCredentials(prop.getProperty("username"), prop.getProperty("password"));
			test.pass("Login is Successfull: ", getScreenshot());
		} catch (Throwable e) {
			test.fail("Test Failed: " + e.getMessage(), getScreenshot());
		}
	}

	@Test(dataProvider = "InvalidCredentialsData", dataProviderClass = Utilities.TestData.class) // description="valid
																									// username &
																									// invalid
																									// password")
	public void verifyInvalidCredentials1(String usernameData, String passwordData) {
		ExtentTest test = reports.createTest("Verify Login with Invalid Credentials ");

		try {
			test.info("URL: " + driver.getCurrentUrl());
			test.info("Username: " + usernameData + "   " + "Password: " + passwordData);

			LoginPageLocators loginpagelocators = new LoginPageLocators(driver);
			loginpagelocators.enterCredentials(prop.getProperty("username"), "9876abc");

			String actualErrorMsg = loginpagelocators.getErrorMessage();
			Assert.assertEquals(actualErrorMsg,
					"Epic sadface: Username and password do not match any user in this service");
			test.pass("Error is Validated Successfully: " + actualErrorMsg, getScreenshot());

		} catch (Throwable e) {
			test.fail("Test Failed: " + e.getMessage());
		}
	}

//	@Test(description="invalid username & valid password")
//	public void verifyInvalidCredentials2() {
//    LoginPageLocators loginpagelocators = new LoginPageLocators(driver);
//    loginpagelocators.enterCredentials("hello2345",prop.getProperty("password"));
//    
//    
//    String actualErrorMsg = loginpagelocators.getErrorMessage();
//    Assert.assertEquals(actualErrorMsg, "Epic sadface: Username and password do not match any user in this service");
//	}
//	
//
//	@Test(description="invalid username & invalid password")
//	public void verifyInvalidCredentials3() {
//    LoginPageLocators loginpagelocators = new LoginPageLocators(driver);
//    loginpagelocators.enterCredentials("teytwudg","9876abc");
//    
//    
//    String actualErrorMsg = loginpagelocators.getErrorMessage();
//    Assert.assertEquals(actualErrorMsg, "Epic sadface: Username and password do not match any user in this service");
//	}

}
