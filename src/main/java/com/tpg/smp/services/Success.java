package com.tpg.smp.services;

public class Success implements ServiceOutcome {
    private final String message;

    public Success(String msg) {
        message = msg;
    }

    public String getMessage() {
        return message;
    }
}
