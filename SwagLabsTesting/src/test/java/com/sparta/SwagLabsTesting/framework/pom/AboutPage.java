package com.sparta.SwagLabsTesting.framework.pom;

import org.openqa.selenium.WebDriver;

public class AboutPage {
    private final WebDriver webDriver;

    public AboutPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getURL(){
        return webDriver.getCurrentUrl();
    }
}
