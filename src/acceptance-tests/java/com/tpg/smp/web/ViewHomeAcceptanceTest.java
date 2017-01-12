package com.tpg.smp.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SeleniumTest(port = 9091, uri = "smp/index")
public class ViewHomeAcceptanceTest {
    @Autowired
    private WebDriver webDriver;

    private HomePage homePage;

    @Before
    public void setUp() {
        homePage = PageFactory.initElements(webDriver, HomePage.class);
    }

    @Test
    public void viewHomePage_homePageRequest_expectHomePageIsLoaded() {
        HomePageAssert homePageAssert = homePage.assertThat();
        homePageAssert.hasWelcomeMessage();
    }
}
