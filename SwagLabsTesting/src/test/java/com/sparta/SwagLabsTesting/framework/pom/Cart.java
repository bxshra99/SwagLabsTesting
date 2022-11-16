package com.sparta.SwagLabsTesting.framework.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Cart {
    private final WebDriver webDriver;
    WebDriverWait wait;

    private By openMenuButton = new By.ById("react-burger-menu-btn");
    private List<WebElement> cartItems = null;
    private final By removeButton = new By.ByCssSelector(".cart_button");
//    private final By cartItem = new By.ByClassName("cart_item");

    private final By checkoutLink = new By.ById("checkout");

    public List<WebElement> getCartItems() {
        return cartItems;
    }

    public void waitUntilElementClickable(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public Cart(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        this.cartItems = webDriver.findElements(By.className("cart_item"));
        //
    }

    public Menu openMenu() {
        webDriver.findElement(openMenuButton).click();
        return new Menu(webDriver);
    }

    public void clickRemoveButton(int index) {
        cartItems.get(index).findElement(removeButton).click();
    }

    public void removeAllItemsFromCart() {
        for (int i = 0; i < cartItems.size(); i++)
            cartItems.get(i).findElement(removeButton).click();
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public CheckoutPage checkoutButton(){
        waitUntilElementClickable(checkoutLink);
        webDriver.findElement(checkoutLink).click();
        return new CheckoutPage(webDriver);
    }
}
