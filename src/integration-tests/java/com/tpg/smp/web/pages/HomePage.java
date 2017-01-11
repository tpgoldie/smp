package com.tpg.smp.web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
    private final WebDriver driver;

    @FindBy(id = "welcome-id")
    private WebElement welcomeMessage;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public HomePageAssert assertThat() { return new HomePageAssert(this); }

    public WebElement getWelcomeMessage() {
        return welcomeMessage;
    }
}
