package com.tpg.smp.services;

public class Failure implements ServiceOutcome {
    private final String message;

    public Failure(String msg) {
        message = msg;
    }

    public String getMessage() {
        return message;
    }
}
