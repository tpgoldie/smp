package com.tpg.smp.auth;

public class InvalidUser extends WebSiteUser {
    private final String message;

    public InvalidUser(String username, String message) {
        this.message = String.format("%s : %s", username, message);
    }

    public String getMessage() {
        return message;
    }
}
