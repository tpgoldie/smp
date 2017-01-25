package com.tpg.smp.data;

import com.tpg.smp.util.RandomStringGenerator;
import org.springframework.boot.test.context.TestComponent;

import java.util.HashMap;
import java.util.Map;

@TestComponent
public class PasswordGenerator {
    private final RandomStringGenerator randomStringGenerator = new RandomStringGenerator();

    private Map<String, String> passwordsByIds = new HashMap<>();

    public String getPasswordFor(String id) {
        String pwd = passwordsByIds.get(id);
        return (pwd != null) ? pwd : generatePassword(id);
    }

    private String generatePassword(String id) {
        String pwd = randomStringGenerator.generateRandomString();
        passwordsByIds.put(id, pwd);

        return pwd;
    }
}
