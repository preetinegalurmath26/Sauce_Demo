package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.Browser;
import Locators.CheckoutCompleteLocators;
import Locators.CheckoutOverviewLocators;
import Locators.HomePageLocators;
import Locators.LoginPageLocators;

public class CheckoutComplete extends Browser {
	
	@Test(description = "Validate Checkout Complete page and return to Products page")
    public void validateOrderCompletion() throws InterruptedException {

        // Login
        LoginPageLocators login = new LoginPageLocators(driver);
        login.enterCredentials(prop.getProperty("username"), prop.getProperty("password"));

        // Select Item
        HomePageLocators home = new HomePageLocators(driver);
        home.selectAnItem();
       // Thread.sleep(3000);
        Click(home.backToProductBtn);
        //Thread.sleep(3000);
        Click(home.cartImg);
        //Thread.sleep(3000);
        Click(home.checkOutBtn);
        //Thread.sleep(3000);
        // Enter User Info Page
        home.enterUserAddress("Preeti", "Negalurmath", "581107");
        Click(home.continueButton);
        Thread.sleep(3000);

        // Checkout Overview Page
        CheckoutOverviewLocators overview = new CheckoutOverviewLocators(driver);
        Click(overview.finishBtn);
	
     // Checkout Complete Page
        CheckoutCompleteLocators complete = new CheckoutCompleteLocators(driver);

        Assert.assertEquals(
                getTextFromElement(complete.thankyouMessage).trim(), 
                "Thank you for your order!"
        );

        Assert.assertTrue(
                getTextFromElement(complete.dispatchMessage).contains("dispatched"),
                "Dispatch message is incorrect!"
        );

        Assert.assertTrue(
                driver.findElement(complete.ponyImage).isDisplayed(),
                "Pony Express image not visible!"
        );

        System.out.println("Order successfully completed!");
        Thread.sleep(3000);
        System.out.println(getTextFromElement(complete.thankyouMessage));
        System.out.println(getTextFromElement(complete.dispatchMessage));

     // Click Back Home
        Click(complete.backhomeButton);

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"), "Back Home did not redirect!");
        System.out.println("Successfully returned to product inventory page.");
    }
}
