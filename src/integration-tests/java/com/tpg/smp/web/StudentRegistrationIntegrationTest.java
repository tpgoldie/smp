package com.tpg.smp.web;

import org.junit.Rule;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class StudentRegistrationIntegrationTest {
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(9090);
}
