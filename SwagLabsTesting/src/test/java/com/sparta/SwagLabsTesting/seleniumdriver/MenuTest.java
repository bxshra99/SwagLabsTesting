package com.sparta.SwagLabsTesting.seleniumdriver;

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
        webDriver.get("https://www.saucedemo.com/inventory.html");
    }


    @Test
    @DisplayName("Checking that the webdriver works")
    public void checkDriver() {
        webDriver.get("https://www.saucedemo.com/inventory.html");
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", webDriver.getCurrentUrl());
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
