package com.tpg.smp.persistence.entities;

public enum AdministrativeStaffMemberType {
    Secretary("Secretary"), ViceChancellor("Vice Chancellor"), Chancellor("Chancellor"), Registrar("Registrar");

    private final String description;

    AdministrativeStaffMemberType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() { return getDescription(); }
}
