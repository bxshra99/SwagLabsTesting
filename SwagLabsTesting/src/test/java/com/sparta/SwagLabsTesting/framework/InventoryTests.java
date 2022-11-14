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
        chromeOptions.addArguments("headless");
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

    // ---------------------- may be removed (is rewritten) --------------------------
    @Test
    @DisplayName("When the backpack add to cart button is pressed, it will change to a remove button")
    public void addBackpackTest() {
        inventoryPage.clickAddBackpackToCartButton();
        Assertions.assertTrue(inventoryPage.removeBackpackFromCartButtonIsPresent());
    }
    // ----------------------------------------------------------------------------------

    // -------------------- anastasiia
    @Test
    @DisplayName("When the add button is pressed, it will change to a remove button")
    public void testAddButtonChangesToRemove() {
        Assertions.assertTrue(inventoryPage.addButtonIsPresent(0));
        inventoryPage.clickItemButton(0);
        Assertions.assertTrue(inventoryPage.removeButtonIsPresent(0));
    }
    // ---------------------------------

    // ------------ may be removed (checks the same functionality as the prev one)--------
    // ------------ another option is to rewrite them -------------------------------------
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
    // ----------------------------------------------------------------------------------

    // ---------------------- may be removed (is rewritten) --------------------------
    @Test
    @DisplayName("When backpack remove button is pressed, it will change to an add button")
    public void removeBackpackTest() {
        inventoryPage.clickAddBackpackToCartButton();
        inventoryPage.clickRemoveBackpackFromCart();
        Assertions.assertTrue(inventoryPage.addBackpackToCartButtonIsPresent());
    }
    // ----------------------------------------------------------------------------------

    // -------------------- anastasiia
    @Test
    @DisplayName("When the remove button is pressed, it will change to an add button")
    public void testRemoveButtonChangesToAdd() {
        inventoryPage.clickItemButton(0);
        inventoryPage.clickItemButton(0);
        Assertions.assertTrue(inventoryPage.addButtonIsPresent(0));
    }
    // --------------------------------

    // ---------------------- may be removed (is rewritten) --------------------------
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
    // ----------------------------------------------------------------------------------

    // -------------------- anastasiia
    @Test
    @DisplayName("When the basket contains 5 items and two are removed, the basket total is 3")
    public void basketTotalTest() {
        inventoryPage.addOrRemoveSeveralItems(5);
        inventoryPage.addOrRemoveSeveralItems(2);
        Assertions.assertEquals(3, inventoryPage.getCartTotal());
    }
    // --------------------------------

    // ---------------------- may be removed (is rewritten) --------------------------
    // basket total tests
    @Test
    @DisplayName("When one item is added to the basket, the basket total is 1")
    public void addOneItemTest0() {
        inventoryPage.clickAddBackpackToCartButton();
        Assertions.assertEquals(1, inventoryPage.getCartTotal());
    }
    // ----------------------------------------------------------------------------------

    // -------------------- anastasiia
    @Test
    @DisplayName("When one item is added to the basket, the basket total is 1")
    public void addOneItemTest() {
        inventoryPage.clickItemButton(0);
        Assertions.assertEquals(1, inventoryPage.getCartTotal());
    }
    // --------------------------------

    // ---------------------- may be removed (is rewritten) --------------------------
    @Test
    @DisplayName("When three items are added to the basket, the basket total will be 3")
    public void addThreeItemsTest0() {
        inventoryPage.clickAddBackpackToCartButton();
        inventoryPage.clickAddOnesieToCartButton();
        inventoryPage.clickAddRedTShirtToCartButton();
        Assertions.assertEquals(3, inventoryPage.getCartTotal());
    }
    // ----------------------------------------------------------------------------------

    // -------------------- anastasiia
    @Test
    @DisplayName("When three items are added to the basket, the basket total will be 3")
    public void addThreeItemsTest() {
        inventoryPage.addOrRemoveSeveralItems(3);
        Assertions.assertEquals(3, inventoryPage.getCartTotal());
    }
    // --------------------------------

    // ---------------------- may be removed (is rewritten) --------------------------
    @Test
    @DisplayName("When all items are added to the cart, the basket total will be 6")
    public void addAllItemsTest0() {
        inventoryPage.clickAddBackpackToCartButton();
        inventoryPage.clickAddOnesieToCartButton();
        inventoryPage.clickAddRedTShirtToCartButton();
        inventoryPage.clickAddBoltTShirtToCartButton();
        inventoryPage.clickAddBikeLightToCartButton();
        inventoryPage.clickAddFleeceJacketToCartButton();
        Assertions.assertEquals(6, inventoryPage.getCartTotal());
    }
    // ----------------------------------------------------------------------------------

    // -------------------- anastasiia
    @Test
    @DisplayName("When all items are added to the cart, the basket total will be 6")
    public void addAllItemsTest() {
        inventoryPage.addOrRemoveSeveralItems(6);
        Assertions.assertEquals(6, inventoryPage.getCartTotal());
    }
    // --------------------------------

    @AfterAll
    public static void afterAll() {
        webDriver.close(); // closes window
        webDriver.quit(); // destroys window
    }
}
