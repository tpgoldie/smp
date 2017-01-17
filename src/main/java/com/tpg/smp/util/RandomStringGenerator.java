package com.tpg.smp.util;

import java.math.BigInteger;
import java.security.SecureRandom;

public final class RandomStringGenerator {
    private SecureRandom random = new SecureRandom();

    public String generateRandomString() {
        return new BigInteger(130, random).toString(32);
    }
}
