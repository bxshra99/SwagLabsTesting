package com.sparta.SwagLabsTesting.framework.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    private final WebDriver webDriver;

    private final By openMenuButton = new By.ById("react-burger-menu-btn");
    private final By firstNameInput = new By.ById("first-name");
    private final By lastNameInput = new By.ById("last-name");
    private final By postalCodeInput = new By.ById("postal-code");
    private final By cancelButton = new By.ById("cancel");
    private final By continueButton = new By.ById("continue");
    private final By finishCheckoutButton = new By.ById("finish");
    private final By backHomeButton = new By.ById("back-to-products");
    private final By errorBox = new By.ByCssSelector(".error-message-container");

    public CheckoutPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getUrl() {
        return webDriver.getCurrentUrl();
    }

    public Menu openMenu() {
        webDriver.findElement(openMenuButton).click();
        return new Menu(webDriver);
    }

    public void enterFirstName(String firstName) {
        webDriver.findElement(firstNameInput).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        webDriver.findElement(lastNameInput).sendKeys(lastName);
    }

    public void enterPostalCode(String postcode){
        webDriver.findElement(postalCodeInput).sendKeys(postcode);
    }

    public void continueCheckout() {
        webDriver.findElement(continueButton).click();
    }

    public void sendInfoForCheckout(String firstName, String lastName, String postcode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postcode);
        continueCheckout();
    }

    public InventoryPage cancelCheckout() {
        webDriver.findElement(cancelButton).click();
        return new InventoryPage(webDriver);
    }

    public void finishCheckout() {
        webDriver.findElement(finishCheckoutButton).click();
    }

    public String getErrorMessage() {
        return webDriver.findElement(errorBox).getText();
    }

    public InventoryPage pressBackHomeButton() {
        webDriver.findElement(backHomeButton).click();
        return new InventoryPage(webDriver);
    }
}
