package com.sparta.SwagLabsTesting.framework.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Cart {
    private final WebDriver webDriver;
    WebDriverWait wait;

    private List<WebElement> cartItem = null;
    private final By removeButton = new By.ByClassName("btn btn_secondary btn_small cart_button");
//    private final By cartItem = new By.ByClassName("cart_item");



    public Cart(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
        this.cartItem = webDriver.findElements(By.className("cart_item"));
        //
    }

    public void clickRemoveButton(int index) {
        cartItem.get(index).findElement(removeButton).click();
    }

    public boolean removeButtonIsPresent(int index) {
        return cartItem.get(index).findElement(removeButton).getText().equals("REMOVE");
    }








}
