package com.sparta.SwagLabsTesting.framework;

import com.sparta.SwagLabsTesting.framework.pom.Cart;
import com.sparta.SwagLabsTesting.framework.pom.InventoryPage;
import com.sparta.SwagLabsTesting.framework.pom.LoginPage;
import com.sparta.SwagLabsTesting.framework.pom.Menu;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

public class CartTest {

    private static WebDriver webDriver;
    private Menu menu;

    private InventoryPage inventoryPage;
    private Cart cart;
    private LoginPage loginPage;

    @BeforeAll
    public static void setupAll() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setLogLevel(ChromeDriverLogLevel.SEVERE);
//        chromeOptions.addArguments("headless");
        webDriver = new ChromeDriver(chromeOptions);
    }

    @BeforeEach
    public void setup() {
        webDriver.manage().deleteAllCookies();
        webDriver.get("https://www.saucedemo.com/cart.html");
        loginPage = new LoginPage(webDriver);
        inventoryPage = loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addOrRemoveSeveralItems(3);
        cart = inventoryPage.openCart();
    }

    @Test
    @DisplayName("Cart page returns correct url")
    public void checkoutUrlTest() {
        Assertions.assertEquals("https://www.saucedemo.com/cart.html", cart.getUrl());
    }

//    @Test
//    @DisplayName("Continue shopping button opens the Inventory page")
//    public void testContinueShopping() {
//        inventoryPage = cart.continueShopping();
//        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", inventoryPage.getUrl());
//    }
//
//    @Test
//    @DisplayName("User can delete one item from the cart")
//    public void testRemoveOneItem() {
//        cart.clickRemoveButton(0);
//        Assertions.assertEquals(2, cart.getNumberOfItems());
//    }

    @AfterEach
    public void removeItems() {
        cart.removeAllItemsFromCart();
    }

    @AfterAll
    public static void afterAll() {
        webDriver.close(); // closes window
        webDriver.quit(); // destroys window
    }

}
