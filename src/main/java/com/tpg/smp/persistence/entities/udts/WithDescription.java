package com.tpg.smp.persistence.entities.udts;

import org.apache.commons.lang3.builder.EqualsBuilder;

public abstract class WithDescription {
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
