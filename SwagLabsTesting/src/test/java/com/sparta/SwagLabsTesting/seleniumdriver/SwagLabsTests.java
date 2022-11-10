package com.sparta.SwagLabsTesting.seleniumdriver;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.locators.RelativeLocator;

public class SwagLabsTests {
        private static WebDriver webDriver;

        @BeforeAll
        public static void setupAll() {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setLogLevel(ChromeDriverLogLevel.SEVERE);
            webDriver = new ChromeDriver(chromeOptions);
        }
    @BeforeEach
    public void setup() {
        webDriver.manage().deleteAllCookies();
        webDriver.get("https://www.saucedemo.com/");
    }

    @Test
    @DisplayName("Check Driver")
    public void checkDriver() {
        Assertions.assertEquals("https://www.saucedemo.com/", webDriver.getCurrentUrl());
    }

    @Test
    @DisplayName("Checking Lodin Page Username")
    public void checkUsername(){
        webDriver.get("https://www.saucedemo.com/");
        WebElement username = webDriver.findElement(By.id("user-name"));
        WebElement password=webDriver.findElement(By.id("password"));
        WebElement login=webDriver.findElement(By.id("login-button"));
        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        login.click();
    }
}
