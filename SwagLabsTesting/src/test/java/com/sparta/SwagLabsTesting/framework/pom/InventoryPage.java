package com.sparta.SwagLabsTesting.framework.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    private WebDriver webDriver;

    private Menu menu;


//    private By inventoryLink = new By.By;

//    private By addButton = new By.ById("add-to-cart-sauce-labs-backpack");
//    private By removeButton = new By.ById("add-to-cart-sauce-labs-bike-light");#
    private By openMenuButton = new By.ById("react-burger-menu-btn");
    private By filterMenu = new By.ByClassName("product_sort_container");
    private By closeMenuButton = new By.ById("react-burger-cross-btn");


    public InventoryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public Menu openMenu() {
        webDriver.findElement(openMenuButton).click();
        return new Menu(webDriver);
    }

    public Menu closeMenu() {
        webDriver.findElement(closeMenuButton).click();
        return new Menu(webDriver);
    }

    public Cart openCart(){
        webDriver.findElement(filterMenu).click();
        return new Cart(webDriver);
    }

    public void addItem(String id) {
        By addButton = new By.ById(id);
    }

    public void removeItem(String id) {
        By removeButton = new By.ById(id);
    }

}
