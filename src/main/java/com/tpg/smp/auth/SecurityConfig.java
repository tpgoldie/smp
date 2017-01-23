package com.tpg.smp.auth;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.tpg.smp.auth", "com.tpg.smp.services"})
public class SecurityConfig {
}
