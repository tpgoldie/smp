package com.tpg.smp.web.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class UserModel {
    @NotEmpty(message = "{usermodel.invalid}")
    @Size(max = 10, message = "{usermodel.invalid}")
    private String username;

    @Size(min = 8, message = "{usermodel.invalid}")
    private String secureToken;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSecureToken() {
        return secureToken;
    }

    public void setSecureToken(String secureToken) {
        this.secureToken = secureToken;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }

        if (!(obj instanceof UserModel)) { return false; }

        UserModel that = (UserModel) obj;

        return new EqualsBuilder()
            .append(that.username, this.username)
            .append(that.secureToken, this.secureToken)
            .isEquals();
    }

    @Override
    public String toString() { return String.format("%s", format(username)); }

    private String format(String value) {
        return value != null ? value : "";
    }
}
