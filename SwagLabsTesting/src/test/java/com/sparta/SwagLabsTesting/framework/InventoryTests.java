package com.sparta.SwagLabsTesting.framework;

import com.sparta.SwagLabsTesting.framework.pom.InventoryPage;
import com.sparta.SwagLabsTesting.framework.pom.LoginPage;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

public class InventoryTests {
    private static WebDriver webDriver;
    private InventoryPage inventoryPage;
    private LoginPage loginPage;
    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";

    @BeforeAll
    public static void setupAll() {
        System.setProperty("webdriver.chrome.driver", DRIVER_LOCATION);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setLogLevel(ChromeDriverLogLevel.SEVERE);
//        chromeOptions.addArguments("headless");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.get("https://www.saucedemo.com/inventory.html");
    }

    @BeforeEach
    public void setUp(){
        webDriver.manage().deleteAllCookies();
        loginPage = new LoginPage(webDriver);
        inventoryPage = new InventoryPage(webDriver);
        inventoryPage = loginPage.login("standard_user", "secret_sauce");


    }

    @Test
    @DisplayName("Login page returns correct url")
    public void loginUrlTest() {
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", inventoryPage.getUrl());
    }

    @Test
    @DisplayName("Adding the backpack")
    public void addBackpack() {
        inventoryPage.addItem("add-to-cart-sauce-labs-backpack");

    }
}
