package Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPageLocators {

	
	WebDriver driver;
	
	By username = By.id("user-name");
	By password = By.name("password");
	By loginBtn = By.id("login-button");
	By errorMsg = By.cssSelector("h3[data-test='error']");
	
	
	public LoginPageLocators(WebDriver driver){
		this.driver = driver;
	}
	
	public void enterCredentials(String name, String pwd) {
		driver.findElement(username).sendKeys(name);
		driver.findElement(password).sendKeys(pwd);
		driver.findElement(loginBtn).click();
	}
	
	public String getErrorMessage() {
		return driver.findElement(errorMsg).getText();
	}
}
