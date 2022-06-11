package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestInsuranceConfirmReviewPage extends BasePage {
		
	public TestInsuranceConfirmReviewPage(WebDriver driver,WebDriverWait explicitWait) {
		this.driver = driver;
		this.explicitWait = explicitWait;
	}

	By reviewConfirmationPageLocator = By.className("rvc-header");
	
	//wait For Review Confirmation Page Load
	public void validateReviewConfirmation(String reviewConfirm) {
		WebElement reviewConfirmation  = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reviewConfirmationPageLocator));
		reviewConfirmation.getText().contains(reviewConfirm);
	}
	
}
