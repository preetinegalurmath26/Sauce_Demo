package Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutCompleteLocators {
	WebDriver driver;
	
	By  checkoutComplete= By.cssSelector(".title");
	public By ponyImage= By.className("pony_express");
	public By thankyouMessage = By.className("complete-header");
	public By dispatchMessage = By.className("complete-text");
	public By backhomeButton = By.id("back-to-products");
	
	
	public  CheckoutCompleteLocators(WebDriver driver) {
		this.driver = driver;
	}
}
