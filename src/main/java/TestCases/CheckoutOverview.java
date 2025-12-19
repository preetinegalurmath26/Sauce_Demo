package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.Browser;
import Locators.CheckoutOverviewLocators;
import Locators.HomePageLocators;
import Locators.LoginPageLocators;

public class CheckoutOverview extends Browser {

	 @Test(description = "Validate Checkout Overview page and complete order successfully")
	    public void validateCheckoutOverviewAndFinish() throws InterruptedException {

	        
	        LoginPageLocators login = new LoginPageLocators(driver);
	        login.enterCredentials(prop.getProperty("username"), prop.getProperty("password"));

	        
	        HomePageLocators home = new HomePageLocators(driver);
	        String selectedItemPrice = home.selectAnItem();
	        Click(home.backToProductBtn);
	        Click(home.cartImg);

	        // cart header validation
	        Assert.assertEquals(getTextFromElement(home.cartHeader).trim(), "Your Cart");

	        String priceInCart = getTextFromElement(home.inventoryItemPrice).trim();
	        Assert.assertEquals(priceInCart, selectedItemPrice, "Price mismatch between product & cart!");

	        Click(home.checkOutBtn);

	        Assert.assertEquals(
	                getTextFromElement(home.cartHeader).trim(), 
	                "Checkout: Your Information"
	        );
	        //  user address
	        home.enterUserAddress("Preeti", "Negalurmath", "581107");
	        Thread.sleep(3000);
	        Click(home.continueButton);

	        // checkout Overview Page
	        CheckoutOverviewLocators overview = new CheckoutOverviewLocators(driver);

	        // validate page header
	        Assert.assertEquals(
	                getTextFromElement(overview.titleHeader).trim(), 
	                "Checkout: Overview"
	        );

	        //validate Item Details
	        String overviewItemName = getTextFromElement(overview.itemName);
	        Assert.assertTrue(overviewItemName.contains("Bike"), "Item Name is incorrect!");

	        String overviewItemPrice = getTextFromElement(overview.itemPrice);
	        Assert.assertEquals(overviewItemPrice, selectedItemPrice, "Item price mismatch!");

	        System.out.println("✔ Item Name: " + overviewItemName);
	        System.out.println("✔ Item Price: " + overviewItemPrice);
	        
	     // validate Payment Information
	        String payment = getTextFromElement(overview.paymentInfo);
	        Assert.assertTrue(payment.contains("SauceCard"), "Payment Information invalid!");
	        System.out.println("✔ Payment Info: " + payment);

	        // validate Shipping Information
	        String shipping = getTextFromElement(overview.shippingInfo);
	        Assert.assertTrue(shipping.contains("Free Pony Express Delivery"), "Shipping Info invalid!");
	        System.out.println("✔ Shipping Info: " + shipping);

	     // validate Item Total, Tax, Total Price
	        String itemTotalLabel = getTextFromElement(overview.itemTotal);
	        String taxLabel = getTextFromElement(overview.tax);
	        String finalTotalLabel = getTextFromElement(overview.totalPrice);

	        System.out.println( itemTotalLabel);
	        System.out.println( taxLabel);
	        System.out.println(finalTotalLabel);

	        // numeric validation
	        double itemAmount = Double.parseDouble(itemTotalLabel.replace("Item total: $", ""));
	        double taxAmount = Double.parseDouble(taxLabel.replace("Tax: $", ""));
	        double finalAmount = Double.parseDouble(finalTotalLabel.replace("Total: $", ""));

	        Assert.assertEquals(finalAmount, itemAmount + taxAmount, 0.001, "Total Price calculation incorrect!");
;


	        // click Finish and Validate
	        Click(overview.finishBtn);
	        String expectedUrl = "https://www.saucedemo.com/checkout-complete.html";
	        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Order did not complete!");

	        System.out.println("ORDER SUCCESSFULLY PLACED!");
	        System.out.println("Redirected to: " + expectedUrl);
	    }
	        
}
