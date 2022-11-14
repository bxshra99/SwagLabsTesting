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
    // url test
    @Test
    @DisplayName("inventory page returns correct url")
    public void inventoryUrlTest() {
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", inventoryPage.getUrl());
    }
    // testing add to cart methods
    @Test
    @DisplayName("When the backpack add to cart button is pressed, it will change to a remove button")
    public void addBackpackTest() {
        inventoryPage.clickAddBackpackToCartButton();
        Assertions.assertTrue(inventoryPage.removeBackpackFromCartButtonIsPresent());
    }
    @Test
    @DisplayName("When the bike light add to cart button is pressed, it will change to a remove button")
    public void addBikeLightTest() {
        inventoryPage.clickAddBikeLightToCartButton();
        Assertions.assertTrue(inventoryPage.removeBikeLightFromCartButtonIsPresent());
    }
    @Test
    @DisplayName("When the bolt t-shirt add to cart button is pressed, it will change to a remove button")
    public void addBoltTShirtTest() {
        inventoryPage.clickAddBoltTShirtToCartButton();
        Assertions.assertTrue(inventoryPage.removeBoltTShirtFromCartButtonIsPresent());
    }
    @Test
    @DisplayName("When the fleece add to cart button is pressed, it will change to a remove button")
    public void addFleeceTest() {
        inventoryPage.clickAddFleeceJacketToCartButton();
        Assertions.assertTrue(inventoryPage.removeFleeceJacketFromCartButtonIsPresent());
    }
    @Test
    @DisplayName("When the onesie add to cart button is pressed, it will change to a remove button")
    public void addOnesieTest() {
        inventoryPage.clickAddOnesieToCartButton();
        Assertions.assertTrue(inventoryPage.removeOnesieFromCartButtonIsPresent());
    }
    @Test
    @DisplayName("When the red t-shirt add to cart button is pressed, it will change to a remove button")
    public void addRedTShirtTest() {
        inventoryPage.clickAddRedTShirtToCartButton();
        Assertions.assertTrue(inventoryPage.removeRedTShirtFromCartButtonIsPresent());
    }

    // testing remove methods
    @Test
    @DisplayName("When backpack remove button is pressed, it will change to an add button")
    public void removeBackpackTest() {
        inventoryPage.clickAddBackpackToCartButton();
        inventoryPage.clickRemoveBackpackFromCart();
        Assertions.assertTrue(inventoryPage.addBackpackToCartButtonIsPresent());
    }

    @Test
    @DisplayName("When the basket contains 5 items and two are removed, the basket total is 3")
    public void basketTest() {
        inventoryPage.clickAddOnesieToCartButton();
        inventoryPage.clickAddBoltTShirtToCartButton();
        inventoryPage.clickAddBikeLightToCartButton();
        inventoryPage.clickAddRedTShirtToCartButton();
        inventoryPage.clickAddBackpackToCartButton();

        inventoryPage.clickRemoveBoltTShirtFromCart();
        inventoryPage.clickRemoveOnesieFromCart();
        Assertions.assertEquals(3, inventoryPage.getCartTotal());
    }
    // basket total tests
    @Test
    @DisplayName("When one item is added to the basket, the basket total is 1")
    public void addOneItemTest() {
        inventoryPage.clickAddBackpackToCartButton();
        Assertions.assertEquals(1, inventoryPage.getCartTotal());
    }
    @Test
    @DisplayName("When three items are added to the basket, the basket total will be 3")
    public void addThreeItemsTest() {
        inventoryPage.clickAddBackpackToCartButton();
        inventoryPage.clickAddOnesieToCartButton();
        inventoryPage.clickAddRedTShirtToCartButton();
        Assertions.assertEquals(3, inventoryPage.getCartTotal());
    }

    @Test
    @DisplayName("When all items are added to the cart, the basket total will be 6")
    public void addAllItemsTest() {
        inventoryPage.clickAddBackpackToCartButton();
        inventoryPage.clickAddOnesieToCartButton();
        inventoryPage.clickAddRedTShirtToCartButton();
        inventoryPage.clickAddBoltTShirtToCartButton();
        inventoryPage.clickAddBikeLightToCartButton();
        inventoryPage.clickAddFleeceJacketToCartButton();
        Assertions.assertEquals(6, inventoryPage.getCartTotal());
    }


}
