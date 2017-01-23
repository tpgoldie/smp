package com.tpg.smp.web;

import com.tpg.smp.web.context.SmpWebAppInitializer;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SmpWebAppInitializer.class, webEnvironment = RANDOM_PORT)
@Profile("intTest")
@TestPropertySource(properties = {"spring.profiles.active=intTest"})
public abstract class SmpWebIntegrationTest {
}
