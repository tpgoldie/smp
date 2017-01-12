package com.tpg.smp.web;

import com.tpg.smp.context.RuntimeConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;

public class HomePage {
    @Autowired
    private RuntimeConfig runtimeConfig;

    private final WebDriver driver;

    @FindBy(id = "welcome-id")
    private WebElement welcomeMessage;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.driver.get(String.format("http://localhost:%d/smp/index", runtimeConfig.serverPort()));
    }

    public HomePageAssert assertThat() { return new HomePageAssert(this); }

    public WebElement getWelcomeMessage() {
        return welcomeMessage;
    }
}
