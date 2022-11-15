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

public class MenuNavigationDefs {

    private static WebDriver webDriver;
    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";

    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private Menu menu;


    public MenuNavigationDefs(){
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
        menu = inventoryPage.openMenu();
    }


    @Given("^that the menu is open$")
    public void thatTheMenuIsOpen() {
        menu = new Menu(webDriver);
    }

    @When("^I click on the inventory link$")
    public void iClickOnTheInventoryLink() {
        menu.goToInventoryPage();

    }

    @Then("^I should be navigated to the inventory page$")
    public void iShouldBeNavigatedToTheInventoryPage() {
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", menu.goToInventoryPage().getUrl());
    }

    @When("^I click on the about page link$")
    public void iClickOnTheAboutPageLink() {
        menu.goToAboutPage();
    }

    @Then("^I should be navigated to the about page$")
    public void iShouldBeNavigatedToTheAboutPage() {
        MatcherAssert.assertThat(webDriver.getCurrentUrl(), equalTo("https://saucelabs.com/"));
    }

    @When("^I click on the logout link$")
    public void iClickOnTheLogoutLink() {
        menu.goToLogout();
    }

    @Then("^i should be navigated back to the login page$")
    public void iShouldBeNavigatedBackToTheLoginPage() {
        MatcherAssert.assertThat(webDriver.getCurrentUrl(), equalTo("https://www.saucedemo.com/"));
    }

    @When("^I click on the reset app link$")
    public void iClickOnTheResetAppLink() {
        menu.resetPage();
    }

    @When("^i click on the close menu button$")
    public void iClickOnTheCloseMenuButton() {
        menu.close();
    }

    @Then("^the menu will be hidden from the webpage$")
    public void theMenuWillBeHiddenFromTheWebpage() {
        Assertions.assertEquals("true", webDriver.findElement(By.className("bm-menu-wrap")).getAttribute("aria-hidden"));
    }

    @AfterAll
    public static void tearDown(){

        if (webDriver!=null){

            webDriver.close();
            webDriver.quit();}
    }

}
