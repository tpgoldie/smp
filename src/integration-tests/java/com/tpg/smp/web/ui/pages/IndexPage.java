package com.tpg.smp.web.ui.pages;

import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.IOException;

public class IndexPage {
    private final WebDriver webDriver;

    @FindBy(id = "username-id")
    private HtmlTextInput usernameInput;

    @FindBy(id = "secure-token-id")
    private HtmlTextInput secureTokenInput;

    @FindBy(css = "input[type=submit]")
    private HtmlSubmitInput submitInput;

    @FindBy(id = "welcome-id")
    private WebElement welcomeMessage;

    public IndexPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void setUsername(String value) {
        usernameInput.setText(value);
    }

    public void setSecureToken(String value) {
        secureTokenInput.setText(value);
    }

    public void login() throws IOException {
        submitInput.click();
    }

    public IndexPageAssert assertThat() { return new IndexPageAssert(this); }

    public WebElement getWelcomeMessage() {
        return welcomeMessage;
    }
}
