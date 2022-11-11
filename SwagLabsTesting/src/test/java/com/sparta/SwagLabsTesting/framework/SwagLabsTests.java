package com.sparta.SwagLabsTesting.framework;

import com.sparta.SwagLabsTesting.framework.pom.Login;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;

public class SwagLabsTests {

    private static WebDriver webDriver;
    private Login login;
//    private Username username;
//    private Password password;
//    private ClickLogin clickLogin;
    private static final String DRIVER_lOCATION = "src/test/resources/chromedriver.exe";

    @BeforeAll
    public static void setupAll() {
        System.setProperty("webdriver.chrome.driver", DRIVER_lOCATION);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setLogLevel(ChromeDriverLogLevel.SEVERE);
        // chromeOptions.addArguments("headless");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.get("https://www.saucedemo.com/");
    }

    @BeforeEach
    public void setUp() {
        webDriver.manage().deleteAllCookies();
        login = new Login(webDriver);

    }

    @AfterAll
    public static void afterAll() {
        webDriver.close(); // closes window
        webDriver.quit(); // destorys window
    }
}
