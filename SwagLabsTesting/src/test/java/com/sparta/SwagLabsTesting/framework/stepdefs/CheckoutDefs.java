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
    }

    @When("I enter {string} as first name")
    public void iEnterAsFirstName(String arg0) {
    }

    @And("I enter {string} as last name")
    public void iEnterAsLastName(String arg0) {
    }

    @And("I enter {string} as a poctal code")
    public void iEnterAsAPoctalCode(String arg0) {
    }

    @And("I click continue button")
    public void iClickContinueButton() {
    }

    @Then("I will be navigated to the {int}nd step of the checkout")
    public void iWillBeNavigatedToTheNdStepOfTheCheckout(int arg0) {
    }

    @When("I enter {string} as the first name")
    public void iEnterAsTheFirstName(String arg0) {
    }

    @And("I enter {string} as a postal code")
    public void iEnterAsAPostalCode(String arg0) {
    }

    @Then("I will recieve an error message that states {string}")
    public void iWillRecieveAnErrorMessageThatStates(String arg0) {
    }

    @When("When I enter {string} as first name")
    public void whenIEnterAsFirstName(String arg0) {
    }

    @And("I enter {string} as a last name")
    public void iEnterAsALastName(String arg0) {
    }
}
