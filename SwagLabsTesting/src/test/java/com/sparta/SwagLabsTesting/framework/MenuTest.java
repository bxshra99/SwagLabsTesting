package com.sparta.SwagLabsTesting.framework;

import com.sparta.SwagLabsTesting.framework.pom.InventoryPage;
import com.sparta.SwagLabsTesting.framework.pom.LoginPage;
import com.sparta.SwagLabsTesting.framework.pom.Menu;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

public class MenuTest {

    private static WebDriver webDriver;
    private Menu menu;

    @BeforeAll
    public static void setupAll() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setLogLevel(ChromeDriverLogLevel.SEVERE);
        webDriver = new ChromeDriver(chromeOptions);
    }

    @BeforeEach
    public void setup() {
        webDriver.manage().deleteAllCookies();
        webDriver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(webDriver);
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        menu = inventoryPage.openMenu();
    }

    @Test
    @DisplayName("Testing the Inventory link")
    public void testInventoryLink() {
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", menu.goToInventoryPage().getUrl());
    }

    @Test
    @DisplayName("Testing the Logout Link")
    public void testLogoutLink() {
        Assertions.assertEquals("https://www.saucedemo.com/", menu.goToLogout().getUrl());
    }

    @Test
    @DisplayName("test About Page link")
    public void TestAboutPageLink() {
        menu.goToAboutPage();
        MatcherAssert.assertThat(webDriver.getCurrentUrl(), equalTo("https://saucelabs.com/"));
    }

    @Test
    @DisplayName("test reset Page link")
    public void TestResetLink() {
        MatcherAssert.assertThat(menu.resetPage().getUrl(), equalTo("https://www.saucedemo.com/inventory.html"));
    }

    @AfterAll
    public static void afterAll() {
        webDriver.close(); // closes window
        webDriver.quit(); // destroys window
    }
}
