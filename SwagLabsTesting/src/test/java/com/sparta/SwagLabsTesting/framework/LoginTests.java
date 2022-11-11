package com.sparta.SwagLabsTesting.framework;

import com.sparta.SwagLabsTesting.framework.pom.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginTests {

    private static WebDriver webDriver;
    private LoginPage loginPage;

    private static final String DRIVER_lOCATION = "src/test/resources/chromedriver.exe";

    @BeforeAll
    public static void setupAll() {
        System.setProperty("webdriver.chrome.driver", DRIVER_lOCATION);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setLogLevel(ChromeDriverLogLevel.SEVERE);
        chromeOptions.addArguments("headless");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.get("https://www.saucedemo.com/");
    }
    @BeforeEach
    public void setUp() {
        webDriver.manage().deleteAllCookies();
        loginPage = new LoginPage(webDriver);
    }

    @Test
    @DisplayName("Test login with a \"standard_user\" username and valid password")
    public void testCorrectLogin() {
        loginPage.login("standard_user", "secret_sauce");
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", loginPage.getUrl());
    }

    @AfterAll
    public static void afterAll() {
        webDriver.close(); // closes window
        webDriver.quit(); // destorys window
    }
}