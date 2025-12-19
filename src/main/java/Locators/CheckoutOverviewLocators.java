package Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutOverviewLocators  {

	 WebDriver driver;

	    public By titleHeader = By.className("title"); 
	    public By itemName = By.cssSelector(".inventory_item_name");
	    public By itemPrice = By.cssSelector(".inventory_item_price");
	    public By paymentInfo = By.xpath("//div[text()='Payment Information:']/following-sibling::div");
	    public By shippingInfo = By.xpath("//div[text()='Shipping Information:']/following-sibling::div");
	    public By itemTotal = By.cssSelector(".summary_subtotal_label");
	    public By tax = By.cssSelector(".summary_tax_label");
	    public By totalPrice = By.cssSelector(".summary_total_label");
	    public By finishBtn = By.id("finish");

	    public CheckoutOverviewLocators(WebDriver driver) {
			this.driver = driver;
		}
}
