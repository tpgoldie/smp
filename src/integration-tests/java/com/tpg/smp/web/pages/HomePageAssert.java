package com.tpg.smp.web.pages;

import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePageAssert extends AbstractAssert<HomePageAssert, HomePage> {
    public HomePageAssert(HomePage page) {
        super(page, HomePageAssert.class);
    }

    public HomePageAssert hasWelcomeMessage() {
        assertThat(actual.getWelcomeMessage().getText()).contains("Welcome To the University of Warwick");
        return this;
    }
}
