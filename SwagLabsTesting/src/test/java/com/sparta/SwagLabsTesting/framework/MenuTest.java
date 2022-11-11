package com.sparta.SwagLabsTesting.framework;

import com.sparta.SwagLabsTesting.framework.pom.InventoryPage;
import com.sparta.SwagLabsTesting.framework.pom.LoginPage;
import com.sparta.SwagLabsTesting.framework.pom.Menu;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

public class MenuTest {

    private static WebDriver webDriver;
    private Menu menu;
    private InventoryPage inventoryPage;
    private LoginPage loginPage;
//

    @BeforeAll
    public static void setupAll() {
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setLogLevel(ChromeDriverLogLevel.SEVERE);
        webDriver = new ChromeDriver(chromeOptions);
    }
    @BeforeEach
    public void setup() {
        webDriver.manage().deleteAllCookies();
        webDriver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(webDriver);
        inventoryPage = loginPage.login("standard_user", "secret_sauce");
        webDriver.findElement(By.id("menu_button_container")).click();
        menu = new Menu(webDriver);



    }



    @Test
    @DisplayName("Checking that the webdriver works")
    public void checkDriver() {
        webDriver.get("https://www.saucedemo.com/");
        Assertions.assertEquals("https://www.saucedemo.com/", webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Testing the Inventory link")
    public void testInventoryLink (){
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html",
                menu.goToInventory().getUrl());
    }

    @Test
    @DisplayName("Testing the Login Link")
    public void testLoginLink (){
        Assertions.assertEquals("https://www.saucedemo.com/",
                menu.goToLogout().getUrl());
    }

    @Test
    @DisplayName("test About Page link")
    public void TestAboutPageLink(){
        menu.goToAboutPage();
        MatcherAssert.assertThat(webDriver.getCurrentUrl(), equals("https://saucelabs.com/"));
    }

    @Test
    @DisplayName("test reset Page link")
    public void TestResetLink(){
        MatcherAssert.assertThat(
                menu.resetPage().getUrl(), equals("https://www.saucedemo.com/inventory.html"));
    }


    @AfterEach
    public void teardown() {
        webDriver.close();
        webDriver.quit();
    }

    @AfterAll
    public static void teardownAll() {
        if (webDriver != null)
            webDriver.quit();
    }



}
