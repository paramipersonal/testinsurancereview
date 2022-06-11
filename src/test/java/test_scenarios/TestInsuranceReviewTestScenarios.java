package test_scenarios;

 
import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import page_objects.TestInsuranceConfirmReviewPage;
import page_objects.TestInsuranceHomePage;
import page_objects.TestInsuranceLoginPage;
import page_objects.TestInsuranceSignUpPage;
import page_objects.TestInsuranceWriteReviewPage;
import resources.Messages;

public class TestInsuranceReviewTestScenarios {

	WebDriver driver;
	TestInsuranceSignUpPage signUpPage;
	TestInsuranceLoginPage loginPage;
	TestInsuranceHomePage homePage;
	TestInsuranceWriteReviewPage writeReviewPage;
	TestInsuranceConfirmReviewPage confirmReviewPage;
	Logger logger;
	WebDriverWait explicitWait;

	@BeforeTest
	public void beforetest() {
		
		logger = LogManager.getLogger();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver,30,100);
		driver.manage().window().maximize();

	}

	@Test(priority= 0)
	public void loginAsLightUser() {
		logger.info("Test1: Login as a Light User");
		homePage = new TestInsuranceHomePage(driver,explicitWait);
		loginPage = new TestInsuranceLoginPage(driver,explicitWait);
		logger.info("Step1_1: Launch the test insurance home page.");
		homePage.launchTestInsuranceHomePage(Messages.getString("TestInsuranceCompanyHomePageURL"));
		logger.info("Step1_2: Click on the Login Button, in top left corner.");
		loginPage.clickLoginLink();
		logger.info("Step1_3: Wait for the login page to load.");
		loginPage.waitForLoginPageLoad();
		logger.info("Step1_4: Enter login email and password");
		loginPage.sendLoginDetails(Messages.getString("EmailIdForSignUpLogin"), Messages.getString("PasswordForSignUpLogin")); 
		logger.info("Step1_5: Click the login button.");
		loginPage.clickLoginButton();
		logger.info("Step1_6: Wait for the home page to load post login");
		homePage.waitForHomePagetoLoad();
	}



	@Test(priority= 1)
	public void provideFourStarRating() {
		logger.info("Test2: Provide a four star rating.");
		homePage = new TestInsuranceHomePage(driver,explicitWait);
		logger.info("Step2_1: Scroll down to the review section in the home page.");
		homePage.scrollDownToReviewsSection();
		logger.info("Step2_2: Wait for the stars to be visible");
		homePage.waitForStarsToBeVisible();
		logger.info("Step2_3: Hover over each star, till the 4th star to give a 4 star rating.");
		homePage.hoverToGiveFourStarRating();
		writeReviewPage = new TestInsuranceWriteReviewPage(driver,explicitWait);
		logger.info("Step2_4: Wait for the write review page to load.");
		writeReviewPage.waitForWriteReviewPagetoLoad();

	}

	@Test(priority= 2)
	public void writeReview() {
		logger.info("Test3: Write a review");
		writeReviewPage = new TestInsuranceWriteReviewPage(driver,explicitWait);
		logger.info("Step3_1: Select Insurance Type "+Messages.getString("InsuranceTypeToSelect"));
		writeReviewPage.selectGivenInsuranceTypeFromDropDown(Messages.getString("InsuranceTypeToSelect"));
		logger.info("Step3_2: Check if the review text has minimum 200 characters.");
		if(writeReviewPage.isTextLengthGreaterThan200(Messages.getString("ReviewText")).equals(false)) {
			logger.error("Review text length is less than 200");
			fail("Failing test, since review text provided was smaller than minimum length needed.");
		}	
		else {
			logger.info("Step3_3: Write a review");
			writeReviewPage.writeReview(Messages.getString("ReviewText")); 	
		}
		logger.info("Step3_4: Click Submit Button.");
		writeReviewPage.clickSubmitButton();
	}

	@Test(priority= 3)
	public void confirmReview() {
		logger.info("Test4: Verify review confirmation message");
		confirmReviewPage = new TestInsuranceConfirmReviewPage(driver,explicitWait);
		logger.info("Step4_1: Wait for confirmation page to load.");
		confirmReviewPage.validateReviewConfirmation(Messages.getString("ConfirmReview"));
	}

	@Test(priority= 4)
	public void checkIfReviewPostedInHomePage() {
		logger.info("Test5: Check if review got posted in home page.");
		homePage = new TestInsuranceHomePage(driver,explicitWait);
		logger.info("Step5_1: Wait for homepage to load.");
		homePage.launchTestInsuranceHomePage(Messages.getString("TestInsuranceCompanyHomePageURL"));
		homePage.waitForHomePagetoLoad();
		logger.info("Step5_2: Scroll down to reviews section");
		homePage.scrollDownToReviewsSection();
		logger.info("Step5_3: Wait for the review section to load.");
		homePage.waitForReviewSectionToLoad();
		logger.info("Step5_4: Check if your review is present.");
		homePage.locateYourReview(Messages.getString("ReviewText"));
	}


	@AfterTest
	public void tearDown() {
		driver.close();
	}



}
