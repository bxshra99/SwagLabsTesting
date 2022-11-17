package com.sparta.SwagLabsTesting.framework.stepdefs;

import com.sparta.SwagLabsTesting.framework.pom.InventoryPage;
import com.sparta.SwagLabsTesting.framework.pom.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.hamcrest.CoreMatchers.equalTo;

public class LoginDefs {
    private static WebDriver webDriver;
    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    public LoginDefs() {
        System.setProperty("webdriver.chrome.driver", DRIVER_LOCATION);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setLogLevel(ChromeDriverLogLevel.SEVERE);
 //       chromeOptions.addArguments("headless");
        webDriver = new ChromeDriver(chromeOptions);
    }

    @Before
    public void setUp() {
        webDriver.manage().deleteAllCookies();
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage(){
        LoginPage loginPage = new LoginPage(webDriver);
        this.loginPage = loginPage;
//        throw new PendingException();
    }

    @When("I enter {string} into the username box")
    public void iEnterIntoTheUsernameBox(String username){
        loginPage.enterUsername(username);
//        throw new PendingException();
    }

    @And("I enter {string} into the password")
    public void iEnterIntoThePassword(String password) {
        loginPage.enterPassword(password);
  //      throw new PendingException();
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        inventoryPage = loginPage.clickLogin();
    //    throw new PendingException();
    }

    @Then("I will see the message {string}")
    public void iWillSeeTheMessage(String message) {
        Assertions.assertTrue(loginPage.hasMessage(message));
   //     throw new PendingException();
    }

    @Then("I will be navigated to the inventory page")
    public void iWillBeNavigatedToTheInventoryPage() {
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", inventoryPage.getUrl());
    }

    @After
    public void tearDown() {
        if (webDriver != null) {
            webDriver.close();
            webDriver.quit();
        }
    }

    @When("I input the correct login information and press login")
    public void iInputTheCorrectLoginInformationAndPressLogin() {
        loginPage.login("standard_user", "secret_sauce");
    }

    @Then("I am taken to the inventory page")
    public void iAmTakenToTheInventoryPage() {
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", loginPage.getUrl());
    }





    //Scenario 2 - Wrong username, right password
    @When("I input the wrong username and the right password, and press login")
    public void iInputTheWrongUsernameAndTheRightPasswordAndPressLogin() {
        loginPage.login("user_123", "secret_sauce");
    }

    @Then("I see a message telling me that my details do not match any username or password in the service.")
    public void iSeeAMessageTellingMeThatMyDetailsDoNotMatchAnyUsernameOrPasswordInTheService() {
        MatcherAssert.assertThat(webDriver.findElement(By.tagName("h3")).getText().contains("Username and password do not match any user in this service"), equalTo(true));
    }


    //Scenario 3 - Correct username, wrong password
    @When("I input the correct username and wrong password, and press login")
    public void iInputTheCorrectUsernameAndWrongPasswordAndPressLogin() {
        loginPage.login("standard_user", "pass_word");

    }


    //Scenario 4 - Username but no password
    @When("I input a username but no password and press login")
    public void iInputAUsernameButNoPasswordAndPressLogin() {
        loginPage.login("standard_user", "");
    }

    @Then("I see a message that tells me a password is required.")
    public void iSeeAMessageThatTellsMeAPasswordIsRequired() {
        MatcherAssert.assertThat(webDriver.findElement(By.tagName("h3")).getAttribute("data-test"), equalTo("error"));
        MatcherAssert.assertThat(webDriver.findElement(By.tagName("h3")).getText().contains("Password is required"), equalTo(true));
    }



    //Scenario 5 - password but no username
    @When("I input a password but no username and press login")
    public void iInputAPasswordButNoUsernameAndPressLogin() {
        loginPage.login("", "secret_sauce");
    }

    @Then("I see a message that tells me a username is required.")
    public void iSeeAMessageThatTellsMeAUsernameIsRequired() {
        MatcherAssert.assertThat(webDriver.findElement(By.tagName("h3")).getAttribute("data-test"), equalTo("error"));
        MatcherAssert.assertThat(webDriver.findElement(By.tagName("h3")).getText().contains("Username is required"), equalTo(true));
    }

    @AfterAll
    public static void afterAll() {
        webDriver.close(); // closes window
        webDriver.quit(); // destroys window
    }



}
