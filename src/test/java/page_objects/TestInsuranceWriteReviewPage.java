package page_objects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestInsuranceWriteReviewPage extends BasePage {

	public TestInsuranceWriteReviewPage(WebDriver driver,WebDriverWait explicitWait) {
		this.driver = driver;
		this.explicitWait = explicitWait;
	}
	
	By reviewPageHeaderLocator = By.className("wrev-prd-name");
	By dropDownLocator = By.className("wrev-drp");
	By locateInsurance = By.xpath("//div[text()='Health Insurance']");
	By textReviewFieldLocator = By.xpath("//textarea[contains(@class, 'textarea') and contains(@class, 'wrev-user-input')]");
	By dropDownPlaceHolder = By.className("dropdown-placeholder");
	By reviewTextCharacterCounter = By.className("wrev-user-input-count");
	By submitButtonLocator = By.className("sbn-action");
	
	
	//wait for write review page to load
	public void waitForWriteReviewPagetoLoad() {
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(reviewPageHeaderLocator));
	}
	
	//Select value from drop down list
	public void selectGivenInsuranceTypeFromDropDown(String insuranceType) {
		WebElement dropDownHolder= explicitWait.until(ExpectedConditions.elementToBeClickable(dropDownLocator));
		dropDownHolder.click();
		WebElement dropDownExpanded = dropDownHolder.findElement(dropDownPlaceHolder);
		System.out.println("Dropdown expanded: "+dropDownExpanded.getAttribute("aria-expanded"));
		Actions action = new Actions(driver);
		List<WebElement> listOfInsurances = dropDownHolder.findElements(By.tagName("li"));
		for(int i = 0; i<listOfInsurances.size(); i++) {
			WebElement insuranceOption = listOfInsurances.get(i);
			if(insuranceOption.getText().equals(insuranceType)) {
				action.moveToElement(insuranceOption).doubleClick().build().perform();
				break;
			}
		}
		explicitWait.until(ExpectedConditions.elementToBeClickable(textReviewFieldLocator));

		
	}
	
	//Send review text
	public void writeReview(String reviewText) {
		locateInputField(textReviewFieldLocator).sendKeys(reviewText);
		
	}
	
	//Check review text length
	public Boolean isTextLengthGreaterThan200(String reviewText) {
		if(reviewText.length()<200) {
			return false;
		}
		else
			return true;
	}
	
	public void clickSubmitButton() {
		WebElement submitButton = explicitWait.until(ExpectedConditions.elementToBeClickable(submitButtonLocator));
		submitButton.click();
	}
	
	
	
}
