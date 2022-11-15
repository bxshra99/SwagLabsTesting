package com.sparta.SwagLabsTesting.framework.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Menu {

    private final WebDriver webDriver;
    WebDriverWait wait;

    private final By crossButton = new By.ByClassName("bm-cross-button");
    private final By inventoryLink = new By.ById("inventory_sidebar_link");
    private final By aboutLink = new By.ById("about_sidebar_link");
    private final By logoutLink = new By.ById("logout_sidebar_link");
    private final By resetLink = new By.ById("reset_sidebar_link");

    public Menu(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    }

    public void waitUntilElementClickable(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public InventoryPage goToInventoryPage(){
        waitUntilElementClickable(inventoryLink);
        webDriver.findElement(inventoryLink).click();
        return new InventoryPage(webDriver);
    }

    public void goToAboutPage(){
        waitUntilElementClickable(aboutLink);
        webDriver.findElement(aboutLink).click();
    }

    public LoginPage goToLogout(){
        waitUntilElementClickable(logoutLink);
        webDriver.findElement(logoutLink).click();
        return new LoginPage(webDriver);
    }

    public InventoryPage resetPage(){
        waitUntilElementClickable(resetLink);
        webDriver.findElement(resetLink).click();
        return new InventoryPage(webDriver);
    }

    public void close(){
        waitUntilElementClickable(crossButton);
        webDriver.findElement(crossButton).click();
    }
}
