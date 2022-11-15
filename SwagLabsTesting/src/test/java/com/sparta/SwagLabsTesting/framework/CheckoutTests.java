package com.sparta.SwagLabsTesting.framework;

import com.sparta.SwagLabsTesting.framework.pom.CheckoutPage;
import com.sparta.SwagLabsTesting.framework.pom.LoginPage;
import com.sparta.SwagLabsTesting.framework.pom.InventoryPage;
import com.sparta.SwagLabsTesting.framework.pom.Cart;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

public class CheckoutTests {
    private static WebDriver webDriver;
    private CheckoutPage checkoutPage;

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
        LoginPage loginPage = new LoginPage(webDriver);
        InventoryPage inventoryPage = loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addOrRemoveSeveralItems(3); // adding 3 items to the cart
        Cart cart = inventoryPage.openCart();
        checkoutPage = cart.checkoutButton();
    }

    @Test
    @DisplayName("Test checkout works once all info is correct")
    public void testCorrectCheckout() {
        checkoutPage.sendInfoForCheckout("Amy", "Baker", "EC2Y 5AS");
        Assertions.assertEquals("https://www.saucedemo.com/checkout-step-two.html", checkoutPage.getUrl());
    }

    @Test
    @DisplayName("Test checkout without first name returns an error")
    public void testCheckoutWithoutFirstName() {
        checkoutPage.sendInfoForCheckout("", "Baker", "EC2Y 5AS");
        Assertions.assertEquals("Error: First Name is required", checkoutPage.getErrorMessage());
    }

    @Test
    @DisplayName("Test checkout without last name returns an error")
    public void testCheckoutWithoutLastName() {
        checkoutPage.sendInfoForCheckout("Amy", "", "EC2Y 5AS");
        Assertions.assertEquals("Error: Last Name is required", checkoutPage.getErrorMessage());
    }

    @Test
    @DisplayName("Test checkout without postal code returns an error")
    public void testCheckoutWithoutPostalCode() {
        checkoutPage.sendInfoForCheckout("Amy", "Baker", "");
        Assertions.assertEquals("Error: Postal Code is required", checkoutPage.getErrorMessage());
    }

    @Test
    @DisplayName("Test cancel button from first step of checkout")
    public void testCancelButtonFromFirstStepOfCheckout() {
        InventoryPage inventoryPage = checkoutPage.cancelCheckout();
        Assertions.assertEquals("https://www.saucedemo.com/cart.html", inventoryPage.getUrl());
    }

    @AfterAll
    public static void afterAll() {
        webDriver.close(); // closes window
        webDriver.quit(); // destroys window
    }
}
