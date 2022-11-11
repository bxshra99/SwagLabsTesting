package com.sparta.SwagLabsTesting.framework.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Menu {

    private WebDriver webDriver;
    private By crossButton = new By.ByClassName("bm-cross-button");
    private By inventoryLink = new By.ByLinkText("All Items");
    private By aboutLink = new By.ByLinkText("About");
    private By logoutLink = new By.ByLinkText("Logout");
    private By resetLink = new By.ByLinkText("Reset App State");

    public Menu(WebDriver webDriver) {
    }


    public Menu xButton(){
        webDriver.findElement(crossButton).click();
        return new Menu(webDriver);

    }

    public InventoryPage goToInventory(){
        webDriver.findElement(inventoryLink).click();
        return new InventoryPage(webDriver);
    }

//    public void goToInventoryPage() {
//        webDriver.get("https://www.saucedemo.com/inventory.html");
//    }

    public void goToAboutPage(){
        webDriver.findElement(aboutLink).click();
    }

    public LoginPage goToLogout(){
        webDriver.findElement(logoutLink).click();
        return new LoginPage(webDriver);
    }


    public InventoryPage resetPage(){
        webDriver.findElement(resetLink).click();
        return new InventoryPage(webDriver);
    }



//    public AboutPage goToTheAboutPage(){
//        webDriver.findElement(aboutLink).click();
//        return new AboutPage(webDriver);
//
//    }



//    public InventoryPage goToAboutPage(){
//        webDriver.findElement(aboutLink).click();
//        return new InventoryPage(webDriver);
//
//    }

    public void close(){
        webDriver.findElement(crossButton).click();
    }


}
