package com.sparta.SwagLabsTesting.framework.stepdefs;


import com.sparta.SwagLabsTesting.framework.pom.InventoryPage;
import com.sparta.SwagLabsTesting.framework.pom.LoginPage;
import com.sparta.SwagLabsTesting.framework.pom.Menu;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.hamcrest.Matchers.equalTo;

public class InventoryDefs {

    private static WebDriver webDriver;
    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private Menu menu;

    public InventoryDefs(){
        System.setProperty("webdriver.chrome.driver", DRIVER_LOCATION);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setLogLevel(ChromeDriverLogLevel.SEVERE);
        chromeOptions.addArguments("headless");
        webDriver = new ChromeDriver(chromeOptions);
    }

    @Before
    public void setup() {
        webDriver.manage().deleteAllCookies();
        webDriver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
    }
    @Given("I am on the inventory page")
    public void iAmOnTheInventoryPage() {
        inventoryPage = new InventoryPage(webDriver);
    }

    @When("I click on sorting field in upper right corner")
    public void iClickOnSortingFieldInUpperRightCorner() {
        inventoryPage.clickProductSortButton();
    }


    @When("I click Add to cart button")
    public void iClickAddToCartButton() {
        inventoryPage.clickItemButton(0);
    }

    @Then("Product should be added to the cart")
    public void productShouldBeAddedToTheCart() {
        Assertions.assertEquals(1, inventoryPage.getCartTotal());
    }

    @When("I have a product added to cart")
    public void iHaveAProductAddedToCart() {
        inventoryPage.clickItemButton(0);
    }

    @And("I click Remove button")
    public void iClickRemoveButton() {
        inventoryPage.clickItemButton(0);
    }

    @Then("Remove button should change to Add to cart")
    public void removeButtonShouldChangeToAddToCart() {
        Assertions.assertTrue(inventoryPage.removeButtonIsPresent(0));
    }

    @And("I click one of possible sorting options")
    public void iClickOneOfPossibleSortingOptions() {
        inventoryPage.clickProductSortNameAZ();
    }

//    @Then("Products on page should be sorted by name[A-Z]")
//    public void productsOnPageShouldBeSortedByNameAZ() {
//        Assertions.assertTrue(inventoryPage.productIsSortedAZ());
//    }

    @AfterAll
    public static void tearDown(){

        if (webDriver!=null){

            webDriver.close();
            webDriver.quit();}
    }

}
