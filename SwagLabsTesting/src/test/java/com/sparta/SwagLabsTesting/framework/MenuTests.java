package com.sparta.SwagLabsTesting.framework;

import com.sparta.SwagLabsTesting.framework.pom.InventoryPage;
import com.sparta.SwagLabsTesting.framework.pom.LoginPage;
import com.sparta.SwagLabsTesting.framework.pom.Menu;
import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

public class MenuTests {

    private static WebDriver webDriver;
    private Menu menu;

    @BeforeAll
    public static void setupAll() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setLogLevel(ChromeDriverLogLevel.SEVERE);
        chromeOptions.addArguments("headless");
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
    @DisplayName("Test the Inventory link")
    public void testInventoryLink() {
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", menu.goToInventoryPage().getUrl());
    }

    @Test
    @DisplayName("Test the Logout link")
    public void testLogoutLink() {
        Assertions.assertEquals("https://www.saucedemo.com/", menu.goToLogout().getUrl());
    }

    @Test
    @DisplayName("Test the About page link")
    public void TestAboutPageLink() {
        menu.goToAboutPage();
        MatcherAssert.assertThat(webDriver.getCurrentUrl(), equalTo("https://saucelabs.com/"));
    }

    @Test
    @DisplayName("Test Reset page link")
    public void TestResetLink() {
        MatcherAssert.assertThat(menu.resetPage().getUrl(), equalTo("https://www.saucedemo.com/inventory.html"));
    }

    @Test
    @DisplayName("Test Close Menu button")
    public void TestCloseMenu() {
        menu.close();
        Assertions.assertEquals("true", webDriver.findElement(By.className("bm-menu-wrap")).getAttribute("aria-hidden"));
    }

    @AfterAll
    public static void afterAll() {
        webDriver.close(); // closes window
        webDriver.quit(); // destroys window
    }
}
