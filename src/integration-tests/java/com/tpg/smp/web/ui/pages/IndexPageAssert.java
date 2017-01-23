package com.tpg.smp.web.ui.pages;

import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

public class IndexPageAssert extends AbstractAssert<IndexPageAssert, IndexPage> {
    public IndexPageAssert(IndexPage page) {
        super(page, IndexPageAssert.class);
    }

    public IndexPageAssert hasWelcomeMessage() {
        String welcomeMsg = "Welcome To the University of Warwick";

        assertThat(actual.getWelcomeMessage().getText()).contains(welcomeMsg);

        return this;
    }
}
