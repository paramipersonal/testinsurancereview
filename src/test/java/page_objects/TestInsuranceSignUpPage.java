package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestInsuranceSignUpPage extends BasePage{
	
	public TestInsuranceSignUpPage(WebDriver driver,WebDriverWait explicitWait) {
		this.driver = driver;
		this.explicitWait = explicitWait;
	}
	
	By signUpLocator = By.linkText("Sign Up");
	By emailIdFieldLocator = By.id("em-ipt");
	By passwordFieldLocator = By.id("pw1-ipt");
	By confirmPasswordFieldLocator = By.id("pw2-ipt");
	By creditReportCheckboxLocator = By.id("get-my-report");
	By creditReportCheckBoxWrapper = By.cssSelector("input[aria-label='get credit score']");
	By joinButtonLocator = By.xpath("//span[text()='Join']");
	
	
	//Launch the Sign Up Page
	public void launchSignUpPage(String signUpURL) {
		driver.get(signUpURL);
		
	}
	
	// Wait for Sign Up Page to Launch Properly
	public void waitForSignUpPageToLoad() {
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(signUpLocator));
	}
	
	// Enter Email id and password
	public void fillUpSignUpForm(String emailId, String password) {
		locateInputField(emailIdFieldLocator).sendKeys(emailId);
		locateInputField(passwordFieldLocator).sendKeys(password);
		locateInputField(confirmPasswordFieldLocator).sendKeys(password);
	}
	
	//Un-check checkbox to receive free credit report
	public void uncheckCheckBoxForCreditReport() {
		WebElement checkBox = explicitWait.until(ExpectedConditions.elementToBeClickable(creditReportCheckboxLocator));
		checkBox.click();
		explicitWait.until(ExpectedConditions.attributeContains(creditReportCheckBoxWrapper, "class", "ng-empty"));
	}
	
	//SignUp
	public void clickJoinToSignUp() {
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(joinButtonLocator)).click();
		//Scope of improvement : Wait for the next Page to appear.
		
	}
	
	
}
