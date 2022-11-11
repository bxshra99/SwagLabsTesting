package com.sparta.SwagLabsTesting.framework.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
    private final WebDriver webDriver;

    public Login(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private void loginFunction() {
        webDriver.get("https://www.saucedemo.com/");
        WebElement username = webDriver.findElement(By.id("user-name"));
        WebElement password=webDriver.findElement(By.id("password"));
        WebElement login=webDriver.findElement(By.id("login-button"));
        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        login.click();
    }
}
