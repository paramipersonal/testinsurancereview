# Test Insurance Review


* [Prerequisites](#prerequisites)
* [Main Dependencies](#main-dependencies)
* [Clone and run project using Eclipse](#clone-and-run-project-using-eclipse)
* [Assumptions](#assumptions)
* [Project Structure and Descriptions](#project-structure-and-descriptions)
* [Constraints and Challenges](#constraints-and-challenges)
* [Future Scopes](#future-scopes)

## Prerequisites
1. **Eclipse IDE**: Eclipse IDE for Java developers can be used to open this maven project 

## Main Dependencies
1. Java
2. Selenium 3.0
3. Maven
4. TestNG
5. log4j 2


#### Dependency links from maven repository used in pom.xml
```
	<dependency>
    		<groupId>org.seleniumhq.selenium</groupId>
    		<artifactId>selenium-java</artifactId>
    		<version>3.0.0</version>
	</dependency>
```

```
	<dependency>
    		<groupId>io.github.bonigarcia</groupId>
    		<artifactId>webdrivermanager</artifactId>
    		<version>5.2.0</version>
	</dependency>
```
```
	<dependency>
    		<groupId>org.testng</groupId>
    		<artifactId>testng</artifactId>
    		<version>7.6.0</version>
    		<scope>test</scope>
	</dependency>
	
```
```
	<dependency>
    		<groupId>org.apache.logging.log4j</groupId>
    		<artifactId>log4j-core</artifactId>
    		<version>2.17.2</version>
	</dependency>
	
```
```
	<dependency>
    		<groupId>org.apache.logging.log4j</groupId>
    		<artifactId>log4j-api</artifactId>
    		<version>2.17.2</version>
	</dependency>
	
```

## Clone and Run Project using Eclipse:
1. Clone the repository link:
```
https://github.com/paramipersonal/testinsurancereview.git
```
2. Open Eclipse IDE
3. Click on File
4. Go to Import
5. Navigate to Import from Git ,using smart Import
6. Clone the github repository.
7. Eclipse will fetch the master branch, keep clicking on Next button until project is imported.
8. Once all the dependency errors get resolved, run the [TestInsuranceReviewTestScenarios.java](https://github.com/paramipersonal/testinsurancereview/blob/master/src/test/java/test_scenarios/TestInsuranceReviewTestScenarios.java) file to run the tests.

## Assumptions
The light user that will be used to run the TestInsuranceReviewTestScenarios.java will be manually created. 
This assumption was made because, before the light user could post any sort of reviews after logging in .It needs to verify its email id by clicking on a link sent by WalletHub to that particular email id used. But still if you want to run an automated sign Up, 
I have separately provided the code for Just Sign Up [here](#standalone-signup-functionality-code).

## Project Structure and Descriptions
This is a simple project that uses Selenium 3, Java, TestNG to perform the below actions : 
1. Launch the [Test Insurance Company homepage](https://wallethub.com/profile/13732055i).
2. Login as a light user,  using your choice of email id and password.
3. Hover over the stars one by one and  give a four star rating in the reviews section of the page.
4. Select an Insurance Type in the Review Page.
5. Write a Review with a text having character count not less than 200 characters.
6. Confirm that review got posted and assert the review confirmation.
7. Launch the homepage again, locate the newly created review in that page. 

### Files
There are three packages under the [source/test/java](https://github.com/paramipersonal/testinsurancereview/tree/master/src/test/java) directory:
* [Package: page_objects](#files-inside-page-objects-directory)
* [Package: test_scenarios](#files-inside-the-test_scenarios-directory)
* [Package: resources](#files-inside-the-resources-directory)


#### Files inside **page-objects** directory
| Class Name            | Test                                                                                                                                     |
|-----------------------|------------------------------------------------------------------------------------------------------------------------------------------|
| BasePage.java        | Contains driver and explicitWait opject declarations and a method common to all the Pages.|        
| TestInsuranceHomePage.java | Contains variables and methods to locate various web elements in the Test Insurance Company Home Page                                                    
| TestInsuranceSignUpPage.java | Contains variables and methods to locate various web elements in the Test Insurance Company SingUp Page                                                    
| TestInsuranceLoginPage.java | Contains variables and methods to locate various web elements in the Test Insurance Company Login Page                                                    
| TestInsuranceWriteReviewPage.java | Contains variables and methods to locate various web elements in the Test Insurance Company Write Review Page                                                    
| TestInsuranceConfirmReviewPage.java        | Contains variables and methods to locate various web elements in the Test Insurance Company Confirm Review Page.   

#### Files inside the **test_scenarios** directory
| Class Name            | Test                                                                                                                                     |
|-----------------------|------------------------------------------------------------------------------------------------------------------------------------------|
| TestInsuranceReviewTestScenarios.java        | Contains the test cases that needs to be implemented as mentioned in Project Description.|           

#### Files inside the **resources** directory
| Class Name            | Test                                                                                                                                     |
|-----------------------|------------------------------------------------------------------------------------------------------------------------------------------|
| Messages.java        | Java class containing the resource bundles for string externalization.|           
| messages.properties | Contains the configurable string inputs like, URL to launch, email ID, password, review text etc                                      

## Constraints and Challenges
1. While testing, the dummy users that were needed to be created, could not be deleted even by the user itself.
2. One light user, could just write one review. Even the author did not have the delete review authorization.
3. Thus if the write review part had to be tested multiple times, new dummy users had to be created. 
4. Since all these dummy users, needed to point to legitimate email ids where  verification codes were being sent, testing was a bit inconvenient. As a result, I finally moved to Signing up once, manually and then proceeded with the rest of the scenarios.

## Standalone SignUp Functionality Code
```
@Test(priority = 0)
	public void createLightUser() {
		logger.info("Test1: Create a light User");
		signUpPage = new TestInsuranceSignUpPage(driver, explicitWait);
		logger.info("Step1: Launch the WalletHub Light User Sign up page.");
		signUpPage.launchSignUpPage(Messages.getString("CreateLightUserURL"));
		logger.info("Step2: Wait for the WalletHub Sign Up Page to load properly.");
		signUpPage.waitForSignUpPageToLoad();
		logger.info("Step3: Enter the email id and password required for sign up");
		signUpPage.fillUpSignUpForm(Messages.getString("EmailIdForSignUpLogin"),
		Messages.getString("PasswordForSignUpLogin"));
		logger.info("Step4: Uncheck the checkbox to receive credit report");
		signUpPage.uncheckCheckBoxForCreditReport();
		logger.info("Step5: Click join to sign up");
		signUpPage.clickJoinToSignUp();
	}
```
Corresponding Page Object can be found in [SignUp Page Object](https://github.com/paramipersonal/testinsurancereview/blob/master/src/test/java/page_objects/TestInsuranceSignUpPage.java)

## Future Scopes
1. User given the capacity to delete themselves.
2. Dummy users given the capacity to post multiple reviews.
3. More optimized use of the page object model.
4. Fetching the type of email id that a dummy user is using to create an account and launching that particular website's email portal. ( For example, If one user uses, abc@outlook.com to sign up, then outlook is launched next to proceed with email id verification.)


