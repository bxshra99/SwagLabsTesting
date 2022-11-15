package com.sparta.SwagLabsTesting.framework;

import com.sparta.SwagLabsTesting.framework.pom.LoginPage;
import org.junit.jupiter.api.*;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.*;

import org.openqa.selenium.By;
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
    }

    @BeforeEach
    public void setUp() {
        webDriver.manage().deleteAllCookies();
        loginPage = new LoginPage(webDriver);
    }

    // Happy path
    // Given that I have input the correct login information,
    // When I press login,
    // Then I am taken to the inventory page.
    @Test
    @DisplayName("Test login with a \"standard_user\" username and valid password")
    public void testCorrectLogin() {
        loginPage.login("standard_user", "secret_sauce");
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", loginPage.getUrl());
    }

    // Sad path
    // Given that I have input the wrong username and the right password,
    // When I press login,
    // Then I see a message telling me that my details do not match any username or password in the service.
    @Test
    @DisplayName("Test login with a wrong username and correct password")
    public void testIncorrectUsername() {
        loginPage.login("user_123", "secret_sauce");
        MatcherAssert.assertThat(webDriver.findElement(By.tagName("h3")).getAttribute("data-test"), equalTo("error"));
        MatcherAssert.assertThat(webDriver.findElement(By.tagName("h3")).getText().contains("rUsename and password do not match any user in this service"), equalTo(true));
    }

    // Sad path
    // Given that I have input the correct username and the wrong password,
    // When I press login,
    // Then I see a message telling me that my details do not match any username or password in the service.
    @Test
    @DisplayName("Test login with a existing username and wrong password")
    public void testIncorrectPassword() {
        loginPage.login("standard_user", "pass_word");
        MatcherAssert.assertThat(webDriver.findElement(By.tagName("h3")).getAttribute("data-test"), equalTo("error"));
        MatcherAssert.assertThat(webDriver.findElement(By.tagName("h3")).getText().contains("Username and password do not match any user in this service"), equalTo(true));
    }

    // Sad Path
    // Given that I have input my username but no password,
    // When I press login,
    // Then I see a message that tells me a password is required.
    @Test
    @DisplayName("Test login with a username but no password")
    public void testLoginNoPassword() {
        loginPage.login("standard_user", "");
        MatcherAssert.assertThat(webDriver.findElement(By.tagName("h3")).getAttribute("data-test"), equalTo("error"));
        MatcherAssert.assertThat(webDriver.findElement(By.tagName("h3")).getText().contains("Password is required"), equalTo(true));
    }

    // Sad Path
    // Given that I have input my password but no username,
    // When I press login,
    // Then I see a message that tells me a username is required.
    @Test
    @DisplayName("Test login with no username but a password")
    public void testLoginNoUsername() {
        loginPage.login("", "secret_sauce");
        MatcherAssert.assertThat(webDriver.findElement(By.tagName("h3")).getAttribute("data-test"), equalTo("error"));
        MatcherAssert.assertThat(webDriver.findElement(By.tagName("h3")).getText().contains("Username is required"), equalTo(true));
    }

    @AfterAll
    public static void afterAll() {
        webDriver.close(); // closes window
        webDriver.quit(); // destroys window
    }
}
