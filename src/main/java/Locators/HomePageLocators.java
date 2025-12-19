package Locators;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageLocators {

	WebDriver driver;

	By bikeItem = By.xpath("//div[text()='Sauce Labs Bike Light']");
	By addToCart = By.id("add-to-cart");
	By invPrice = By.className("inventory_details_price");

	public By backToProductBtn = By.id("back-to-products");
	public By cartImg = By.className("shopping_cart_link");
	public By cartHeader = By.className("header_secondary_container");
	public By inventoryItemPrice = By.className("inventory_item_price");
	public By checkOutBtn = By.id("checkout");
	public By firstName = By.id("first-name");
	public By lastName = By.name("lastName");
	public By postalCode = By.id("postal-code");
	public By continueButton = By.id("continue");

	public HomePageLocators(WebDriver driver) {
		this.driver = driver;
	}

	public String selectAnItem() {
		driver.findElement(bikeItem).click();
		driver.findElement(addToCart).click();
		return driver.findElement(invPrice).getText();

	}

	public void enterUserAddress(String f_name, String l_Name, String code) {

		driver.findElement(this.firstName).sendKeys(f_name);
		driver.findElement(this.lastName).sendKeys(l_Name);
		driver.findElement(this.postalCode).sendKeys(code);
	}

}
