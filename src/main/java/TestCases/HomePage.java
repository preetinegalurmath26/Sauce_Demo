package TestCases;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import Base.Browser;
import Locators.HomePageLocators;
import Locators.LoginPageLocators;
import Utilities.ExcelUtilities;

public class HomePage extends Browser {

	@Test(description = "Place one E2E order")
	public void placeOneE2EOrder() throws InterruptedException {
		ExtentTest test = reports.createTest("Verify HomePage");
		
		
		LoginPageLocators loginpagelocators = new LoginPageLocators(driver);
		loginpagelocators.enterCredentials(prop.getProperty("username"), prop.getProperty("password"));

		
		try {
			test.info("URL: " + driver.getCurrentUrl());
			
		HomePageLocators homePageLocators = new HomePageLocators(driver);
		String invPrice = homePageLocators.selectAnItem();
		System.out.println("Inventory Price: " + invPrice);
		Click(homePageLocators.backToProductBtn);
		Click(homePageLocators.cartImg);
		String cartHeader = getTextFromElement(homePageLocators.cartHeader).trim();
		Assert.assertEquals(cartHeader, "Your Cart");
		test.pass("Added to Cart Successfully", getScreenshot());
		

		String inventoryItemPrice = getTextFromElement(homePageLocators.inventoryItemPrice).trim();
		Assert.assertEquals(inventoryItemPrice, invPrice);
		Click(homePageLocators.checkOutBtn);
		Assert.assertEquals(getTextFromElement(homePageLocators.cartHeader).trim(), "Checkout: Your Information");
		test.pass("Checkout is Validated Successfully", getScreenshot());

		Map<String, String> testDataMap = ExcelUtilities.getUserAdressDataFromExcelSheet("Sheet1");
		homePageLocators.enterUserAddress(testDataMap.get("First_Name"), testDataMap.get("Last_Name"),
				testDataMap.get("Postal_Code"));
		Thread.sleep(3000);
		Click(homePageLocators.continueButton);
		
		} catch (Throwable e) {
			test.fail("Test Failed: " + e.getMessage(), getScreenshot());
		}

	}
}
