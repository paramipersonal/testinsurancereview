package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	WebDriver driver;
	WebDriverWait explicitWait;
	
	public WebElement locateInputField(By locator) {
		 WebElement locatedField = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		 locatedField.click();
		 return locatedField;
	}
}
