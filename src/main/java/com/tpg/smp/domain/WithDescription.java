package com.tpg.smp.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;

import java.io.Serializable;

public abstract class WithDescription implements Serializable {
    private final String description;

    WithDescription(String description) {
        this.description = description;
    }

    public String getDescription() { return description; }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { return true; }
        if (!(obj instanceof WithDescription)) { return false; }

        WithDescription that = (WithDescription) obj;
        return new EqualsBuilder().append(that.description, this.description).isEquals();
    }

    @Override
    public String toString() { return getDescription(); }
}
