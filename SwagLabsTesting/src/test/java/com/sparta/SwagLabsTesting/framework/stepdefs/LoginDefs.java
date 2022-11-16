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
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

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
}
