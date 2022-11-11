package com.sparta.SwagLabsTesting.framework;

import com.sparta.SwagLabsTesting.framework.pom.Menu;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

public class MenuTest {

    private static WebDriver webDriver;


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
                Menu.goToInventory().getUrl());
    }

    @Test
    @DisplayName("Testing the Login Link")
    public void testLoginLink (){
        Assertions.assertEquals("https://www.saucedemo.com/",
                Menu.goToLogout().getUrl());
    }

    @Test
    @DisplayName("test About Page link")
    public void TestAboutPageLink(){
        MatcherAssert.assertThat(
                Menu.goToTheAboutPage().getUrl(), equals("https://saucelabs.com/"));
    }

    @Test
    @DisplayName("test reset Page link")
    public void TestAboutPageLink(){
        MatcherAssert.assertThat(
                Menu.resetPage().getUrl(), equals("https://www.saucedemo.com/inventory.html"));
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
