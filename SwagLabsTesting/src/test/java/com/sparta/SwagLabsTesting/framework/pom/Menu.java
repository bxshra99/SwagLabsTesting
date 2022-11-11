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

    public void goToAbout(){
        webDriver.findElement(aboutLink).click();
    }

    public LoginPage goToLogout(){
        webDriver.findElement(logoutLink).click();
        return new LoginPage(webDriver);
    }

    public void reset(){
        webDriver.findElement(resetLink).click();
    }

    public void close(){
        webDriver.findElement(crossButton).click();
    }


}
