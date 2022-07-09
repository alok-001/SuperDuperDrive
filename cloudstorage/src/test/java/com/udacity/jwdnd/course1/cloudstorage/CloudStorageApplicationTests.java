package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private static EncryptionService encryptionService;
	private static WebDriver driver;
	private static HomePage homePage;
	private static LoginPage loginPage;
	private static ResultPage resultPage;
	private static SignUpPage signUpPage;
	

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		encryptionService = new EncryptionService();
		signUpPage = new SignUpPage(driver);
		resultPage = new ResultPage(driver);
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void getSignUpPage(){
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}
	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doMockSignUp(String firstName, String lastName, String userName, String password){
		// Create a dummy account for logging in later.

		// Visit the sign-up page.
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		driver.get("http://localhost:" + this.port + "/signup");
		webDriverWait.until(ExpectedConditions.titleContains("Sign Up"));
		
		// Fill out credentials
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputFirstName")));
		WebElement inputFirstName = driver.findElement(By.id("inputFirstName"));
		inputFirstName.click();
		inputFirstName.sendKeys(firstName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputLastName")));
		WebElement inputLastName = driver.findElement(By.id("inputLastName"));
		inputLastName.click();
		inputLastName.sendKeys(lastName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement inputUsername = driver.findElement(By.id("inputUsername"));
		inputUsername.click();
		inputUsername.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement inputPassword = driver.findElement(By.id("inputPassword"));
		inputPassword.click();
		inputPassword.sendKeys(password);

		// Attempt to sign up.
		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buttonSignUp")));
		WebElement buttonSignUp = driver.findElement(By.id("buttonSignUp"));
		buttonSignUp.click();

		/* Check that the sign up was successful. 
		// You may have to modify the element "success-msg" and the sign-up 
		// success message below depening on the rest of your code.
		*/
		Assertions.assertTrue(driver.findElement(By.id("success-msg")).getText().contains("You successfully signed up!"));
	}

	
	
	/**
	 * PLEASE DO NOT DELETE THIS method.
	 * Helper method for Udacity-supplied sanity checks.
	 **/
	private void doLogIn(String userName, String password)
	{
		// Log in to our dummy account.
		driver.get("http://localhost:" + this.port + "/login");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputUsername")));
		WebElement loginUserName = driver.findElement(By.id("inputUsername"));
		loginUserName.click();
		loginUserName.sendKeys(userName);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("inputPassword")));
		WebElement loginPassword = driver.findElement(By.id("inputPassword"));
		loginPassword.click();
		loginPassword.sendKeys(password);

		webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
		WebElement loginButton = driver.findElement(By.id("login-button"));
		loginButton.click();

		webDriverWait.until(ExpectedConditions.titleContains("Home"));

	}

	@Test
	public void getHomePageDirectly(){
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertNotEquals("Home", driver.getTitle());
		Assertions.assertEquals("Login", driver.getTitle());
	}


	@Test
	public void getResultPageDirectly(){
		driver.get("http://localhost:" + this.port + "/result");
		Assertions.assertNotEquals("result", driver.getTitle());
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void signUp_LogIn_and_LogOut(){
		signUpPage = new SignUpPage(driver);
		driver.get("http://localhost:" + this.port + "/signup");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		signUpPage.signup("Goldy", "Meshram", "goldym", "1234Abc");
		Assertions.assertEquals("Login", driver.getTitle());
		loginPage = new LoginPage(driver);
		driver.get("http://localhost:" + this.port + "/login");
		loginPage.login("goldym", "1234Abc");
		Assertions.assertEquals("Home", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/home");
		WebElement logOut = driver.findElement(By.id("logout-button"));
		logOut.click();
		Assertions.assertEquals("Login", driver.getTitle());
	}


	@Test
	public void NotAccessToHomePageWithoutLogIn() {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertNotEquals("home", driver.getTitle());
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void signupAndLogin(){
		signUpPage = new SignUpPage(driver);
		driver.get("http://localhost:" + this.port + "/signup");
		WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
		signUpPage.signup("Goldy", "Meshram", "goldym", "1234Abc");
		Assertions.assertEquals("Login", driver.getTitle());
		loginPage = new LoginPage(driver);
		driver.get("http://localhost:" + this.port + "/login");
		loginPage.login("goldym", "1234Abc");
		Assertions.assertEquals("Home", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/home");
	}

	@Test
	public void AddNotes(){
		String noteTitle= "noteTitle";
		String noteDes = "noteDescription";
		signupAndLogin();
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Home", driver.getTitle());
		HomePage homePage = new HomePage(driver);
		homePage.NoteTabClicked();
		homePage.AddNoteButtonClicked();
		homePage.addNote(noteTitle, noteDes);
		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		resultPage.goToHomeLinkFromSuccess();
		Assertions.assertEquals("Home", driver.getTitle());
	}

	@Test
	public void EditNotesAndSave(){
		String noteTitleEdit= "noteTitleEdit";
		String noteDesEdit = "noteDescriptionEdit";
		signupAndLogin();
		AddNotes();
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Home", driver.getTitle());
		HomePage homePage = new HomePage(driver);
		homePage.NoteTabClicked();
		homePage.EditNoteButtonClicked();
		homePage.addNote(noteTitleEdit, noteDesEdit);
		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		resultPage.goToHomeLinkFromSuccess();
		Assertions.assertEquals("Home", driver.getTitle());
		Assertions.assertEquals(noteTitleEdit, homePage.getNoteTitleDisplayed());
		Assertions.assertEquals(noteDesEdit, homePage.getNoteDescriptionDisplayed());
	}

	@Test
	public void DeleteExistingNote(){
		signupAndLogin();
		AddNotes();
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Home", driver.getTitle());
		HomePage homePage = new HomePage(driver);
		homePage.NoteTabClicked();
		homePage.NoteDeleteClicked();
		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		resultPage.goToHomeLinkFromSuccess();
		Assertions.assertEquals("Home", driver.getTitle());
	}

	@Test
	public void AddCredential(){
		String Url = "abc.com";
		String Username = "Goldy";
		String password = "1234";

		signupAndLogin();
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Home", driver.getTitle());
		HomePage homePage = new HomePage(driver);
		homePage.CredentialTableClicked();
		homePage.AddCredentialbtn();
		homePage.AddCredential(Url, Username, password);
		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		resultPage.goToHomeLinkFromSuccess();
		Assertions.assertEquals("Home", driver.getTitle());

	}

	@Test
	public void AddAndEditCredential(){
		String Url = "c.com";
		String Username = "Gldy";
		String password = "134";
		signupAndLogin();
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Home", driver.getTitle());
		HomePage homePage = new HomePage(driver);
		homePage.CredentialTableClicked();
		AddCredential();
		homePage.EditCredentialbtn();
		homePage.AddCredential(Url, Username, password);
		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		resultPage.goToHomeLinkFromSuccess();
		Assertions.assertEquals("Home", driver.getTitle());
		Assertions.assertEquals(Url, homePage.getCredentialURLDisplay());
		Assertions.assertEquals(Username, homePage.getCredentialUsernameDisplay());
	}

	@Test
	public void DeleteCredential(){
		signupAndLogin();
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Home", driver.getTitle());
		HomePage homePage = new HomePage(driver);
		homePage.CredentialTableClicked();
		AddCredential();
		homePage.DeleteCredentialbtn();
		Assertions.assertEquals("Result", driver.getTitle());
		ResultPage resultPage = new ResultPage(driver);
		resultPage.goToHomeLinkFromSuccess();
		Assertions.assertEquals("Home", driver.getTitle());
	}

}
