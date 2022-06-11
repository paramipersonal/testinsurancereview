package page_objects;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestInsuranceHomePage extends BasePage
{
	
	public TestInsuranceHomePage(WebDriver driver,WebDriverWait explicitWait) {
		this.driver = driver;
		this.explicitWait = explicitWait;
	}
	
	
	By reviewSectionStarLocator = By.className("review-action");
	By reviewSectionLocator = By.id("reviews-section");
	By homePageLocator = By.id("web-app");
    By myReviewLocator = By.className("rvtab-content");
	
	
	public void launchTestInsuranceHomePage(String homePageURL) {
		 driver.get(homePageURL);	
		 waitForHomePagetoLoad();

	}
	
	public void waitForHomePagetoLoad() {
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(homePageLocator));
	}
	
	
	public void hoverToGiveFourStarRating() {
		Actions action = new Actions(driver);
		List<WebElement> findStars = driver.findElements(By.xpath("//div[@class='rating-box-wrapper']//*[local-name()='svg' and @aria-hidden='false']"));
		for(int starIndex =0 ;starIndex<4;starIndex++) {
			WebElement star = findStars.get(starIndex);
			action.moveToElement(star).build().perform();
			waitTillStarLightsUp(star,starIndex+1);
			if(starIndex ==3) {
				action.moveToElement(star).click().build().perform();
			}
		}
	
	}
	
	public void scrollDownToReviewsSection() {
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("window.scrollBy(0,900)","");
		
	}
	
	public void waitForStarsToBeVisible() {
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reviewSectionStarLocator));
	}
	
	public void waitForReviewSectionToLoad() {
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reviewSectionLocator));
	}
	
	
	public void waitTillStarLightsUp(WebElement star, int rating) {
		String lightUp = explicitWait.until(ExpectedConditions.visibilityOf(star)).getAttribute("aria-checked");
		if(lightUp.equals("true")) {
			System.out.println("Star rating "+rating +" lit up.");
		}
		else {
			System.out.println("Star rating did not light up.");
		}
	
	}
	
	public void locateYourReview(String reviewText) {
		WebElement yourReview = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(myReviewLocator));
		assertEquals(yourReview.getText().contains("a distinct section of a piece of writing"),true);		
	}
	


}
