package com.sparta.SwagLabsTesting.framework.stepdefs;

import com.sparta.SwagLabsTesting.framework.pom.Cart;
import com.sparta.SwagLabsTesting.framework.pom.CheckoutPage;
import com.sparta.SwagLabsTesting.framework.pom.InventoryPage;
import com.sparta.SwagLabsTesting.framework.pom.LoginPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

public class CheckoutDefs {
    private static WebDriver webDriver;
    private CheckoutPage checkoutPage;

    private static final String DRIVER_lOCATION = "src/test/resources/chromedriver.exe";

    public CheckoutDefs() {
        System.setProperty("webdriver.chrome.driver", DRIVER_lOCATION);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setLogLevel(ChromeDriverLogLevel.SEVERE);
        chromeOptions.addArguments("headless");
        webDriver = new ChromeDriver(chromeOptions);
    }

    @Before
    public void setup() {
        webDriver.manage().deleteAllCookies();
        LoginPage loginPage = new LoginPage(webDriver);
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addOrRemoveSeveralItems(3); // adding 3 items to the cart
        Cart cart = inventoryPage.openCart();
        checkoutPage = cart.checkoutButton();
    }
    
    @Given("I am on the Checkout page")
    public void iAmOnTheCheckoutPage() {
        checkoutPage = new CheckoutPage(webDriver);
    }

    @When("I enter {string} as first name")
    public void iEnterAsFirstName(String firstName) {
        checkoutPage.enterFirstName(firstName);
    }

    @And("I enter {string} as last name")
    public void iEnterAsLastName(String lastName) {
        checkoutPage.enterLastName(lastName);
    }

    @And("I enter {string} as a postal code")
    public void iEnterAsAPostalCode(String postalCode) {
        checkoutPage.enterPostalCode(postalCode);
    }

    @And("I click continue button")
    public void iClickContinueButton() {
        checkoutPage.continueCheckout();
    }

    @Then("I will be navigated to the second step of the checkout")
    public void iWillBeNavigatedToTheSecondStepOfTheCheckout() {
        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-two.html", checkoutPage.getUrl());
    }

    @Then("I will receive an error message that states {string}")
    public void iWillReceiveAnErrorMessageThatStates(String message) {
        Assertions.assertEquals(message, checkoutPage.getErrorMessage());
    }
}