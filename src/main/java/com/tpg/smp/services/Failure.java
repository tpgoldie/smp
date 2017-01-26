package com.tpg.smp.services;

public class Failure implements ServiceOutcome {
    private final String message;

    private boolean missingIdentityDetails;

    public Failure(String msg) {
        message = msg;
    }

    public String getMessage() {
        return message;
    }

    public boolean isMissingIdentityDetails() {
        return missingIdentityDetails;
    }

    public void setMissingIdentityDetails() {
        this.missingIdentityDetails = true;
    }
}
