package com.sparta.SwagLabsTesting.framework.stepdefs;

import com.sparta.SwagLabsTesting.framework.pom.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

public class LoginDefs {

    private static WebDriver webDriver;
    private static final String DRIVER_LOCATION = "src/test/resources/chromedriver.exe";
    private LoginPage loginPage;

    public LoginDefs() {
        System.setProperty("webdriver.chrome.driver", DRIVER_LOCATION);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setLogLevel(ChromeDriverLogLevel.SEVERE);
        chromeOptions.addArguments("headless");
        webDriver = new ChromeDriver(chromeOptions);
    }

    @Before
    public void setUp() {
        webDriver.manage().deleteAllCookies();
    }

    @After
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.close();
            webDriver.quit();
        }
    }

}
