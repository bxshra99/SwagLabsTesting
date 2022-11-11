package com.sparta.SwagLabsTesting.framework.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private final WebDriver webDriver;

    private By usernameInput = new By.ById("user-name");
    private By passwordInput = new By.ById("password");
    private By loginButton = new By.ById("login-button");

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public void enterUsername(String username) {
        webDriver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password){
        webDriver.findElement(passwordInput).sendKeys(password);
    }

    public InventoryPage clickLogin() {
        webDriver.findElement(loginButton).click();
        return new InventoryPage(webDriver);
    }

    public InventoryPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLogin();
    }
}
