package com.sparta.SwagLabsTesting.framework;

import com.sparta.SwagLabsTesting.framework.pom.Cart;
import com.sparta.SwagLabsTesting.framework.pom.InventoryPage;
import com.sparta.SwagLabsTesting.framework.pom.LoginPage;
import com.sparta.SwagLabsTesting.framework.pom.Menu;
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
    private Menu menu;
    private Cart cart;
    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";

    @BeforeAll
    public static void setupAll() {
        System.setProperty("webdriver.chrome.driver", DRIVER_LOCATION);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setLogLevel(ChromeDriverLogLevel.SEVERE);
//        chromeOptions.addArguments("headless");
        webDriver = new ChromeDriver(chromeOptions);
    }

    @BeforeEach
    public void setUp(){
        webDriver.manage().deleteAllCookies();
        loginPage = new LoginPage(webDriver);
        inventoryPage = new InventoryPage(webDriver);
//        menu = new Menu(webDriver);
        inventoryPage = loginPage.login("standard_user", "secret_sauce");


    }

    @Test
    @DisplayName("inventory page returns correct url")
    public void inventoryUrlTest() {
        Assertions.assertEquals("https://www.saucedemo.com/inventory.html", inventoryPage.getUrl());
    }

    @Test
    @DisplayName("When the add button is pressed, it will change to a remove button")
    public void testAddButtonChangesToRemove() {
        Assertions.assertTrue(inventoryPage.addButtonIsPresent(0));
        inventoryPage.clickItemButton(0);
        Assertions.assertTrue(inventoryPage.removeButtonIsPresent(0));
    }

    @Test
    @DisplayName("When the remove button is pressed, it will change to an add button")
    public void testRemoveButtonChangesToAdd() {
        inventoryPage.clickItemButton(0);
        inventoryPage.clickItemButton(0);
        Assertions.assertTrue(inventoryPage.addButtonIsPresent(0));
    }

    @Test
    @DisplayName("When the basket contains 5 items and two are removed, the basket total is 3")
    public void basketTotalTest() {
        inventoryPage.addOrRemoveSeveralItems(5);
        inventoryPage.addOrRemoveSeveralItems(2);
        Assertions.assertEquals(3, inventoryPage.getCartTotal());
    }

    @Test
    @DisplayName("When one item is added to the basket, the basket total is 1")
    public void addOneItemTest() {
        inventoryPage.clickItemButton(0);
        Assertions.assertEquals(1, inventoryPage.getCartTotal());
    }

    @Test
    @DisplayName("When three items are added to the basket, the basket total will be 3")
    public void addThreeItemsTest() {
        inventoryPage.addOrRemoveSeveralItems(3);
        Assertions.assertEquals(3, inventoryPage.getCartTotal());
    }

    @Test
    @DisplayName("When all items are added to the cart, the basket total will be 6")
    public void addAllItemsTest() {
        inventoryPage.addOrRemoveSeveralItems(6);
        Assertions.assertEquals(6, inventoryPage.getCartTotal());
    }
    
    // get product name
    @Test
    @DisplayName("Correct product name is returned")
    public void getProductNameTest() {
        Assertions.assertEquals("Sauce Labs Bike Light", inventoryPage.getProductName(1));
    }

    @Test
    @DisplayName("Correct product description is returned")
    public void getProductDescriptionTest() {
        Assertions.assertEquals("This classic Sauce Labs t-shirt is perfect to wear when cozying up to your keyboard to automate a few tests. Super-soft and comfy ringspun combed cotton.", inventoryPage.getProductDescription(5));
    }

    @Test
    @DisplayName("Correct product price is returned")
    public void getProductPriceTest() {
        Assertions.assertEquals(49.99, inventoryPage.getProductPrice(3));
    }

    @Test
    @DisplayName("Correct index is returned")
    public void correctIndexReturnedTest() {
        Assertions.assertEquals(0, inventoryPage.getProductIndexFromName("Sauce Labs Backpack"));
    }

     //testing filter functions
    @Test
    @DisplayName("When HL sort is clicked, the products are sorted by price from highest to lowest")
    public void highLowSortTest() {
        inventoryPage.clickProductSortPriceHL();
        Assertions.assertTrue(inventoryPage.productIsSortedHL());
    }
    @Test
    @DisplayName("When LH sort is clicked, the products are sorted by price from highest to lowest")
    public void lowHighSortTest() {
        inventoryPage.clickProductSortPriceLH();
        Assertions.assertTrue(inventoryPage.productIsSortedLH());
    }
    @Test
    @DisplayName("When AZ sort is clicked, the products are sorted alphabetically")
    public void aTozSortTest() {
        inventoryPage.clickProductSortNameAZ();
        Assertions.assertTrue(inventoryPage.productIsSortedAZ());
    }
    @Test
    @DisplayName("When ZA sort is clicked, the products are sorted reverse alphabetically")
    public void zToaSortTest() {
        inventoryPage.clickProductSortNameZA();
        Assertions.assertTrue(inventoryPage.productIsSortedZA());
    }


//    // testing image link
//    @Test
//    @DisplayName("When an item image is clicked, the item page is shown")
//    public void imageClickTest() {
//        inventoryPage.clickImageLink(1);
//        Assertions.assertEquals("https://www.saucedemo.com/inventory-item.html?id=0", inventoryPage.getUrl());
//    }
//     // testing item link
//    @Test
//    @DisplayName("When an item link is clicked, the item page is shown")
//    public void imageLinkClickTest() {
//        inventoryPage.clickItemLink(5);
//        Assertions.assertEquals("https://www.saucedemo.com/inventory-item.html?id=3", inventoryPage.getUrl());
//    }

    @AfterEach
    public void removeItems() {
        inventoryPage.removeAllItemsFromCart();
    }

    @AfterAll
    public static void afterAll() {
        webDriver.close(); // closes window
        webDriver.quit(); // destroys window
    }

}
