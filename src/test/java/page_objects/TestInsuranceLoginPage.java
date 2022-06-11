package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestInsuranceLoginPage extends BasePage {
	
	
	public TestInsuranceLoginPage(WebDriver driver,WebDriverWait explicitWait) {
		this.driver = driver;
		this.explicitWait = explicitWait;
	}
	
	By locateLoginLink = By.className("brgm-signup-login");
	By loginPageLocator = By.id("join-login");
	By emailIdFieldLocator = By.id("email");
	By passwordFieldLocator = By.id("password");
	By loginButtonLocator = By.xpath("//span[text()='Login']");
	
	
	//Click on Login Link
	public void clickLoginLink() {
		explicitWait.until(ExpectedConditions.elementToBeClickable(locateLoginLink)).click();
		
	}
	
	
	//Wait for login page to load properly
	public void waitForLoginPageLoad() {
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(loginPageLocator));
		
	}
	
	//Send Email Id and password
	public void sendLoginDetails(String emailId, String password) {
		locateInputField(emailIdFieldLocator).sendKeys(emailId);
		locateInputField(passwordFieldLocator).sendKeys(password);
		
	}
	
	//Click on login button
	public void clickLoginButton() {
		explicitWait.until(ExpectedConditions.elementToBeClickable(loginButtonLocator)).click();
	}
	

}
