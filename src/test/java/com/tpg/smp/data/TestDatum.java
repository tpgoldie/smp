package com.tpg.smp.data;

public abstract class TestDatum<DM> {
    DM domainModel;

    TestDatum(DM domainModel) {
        this.domainModel = domainModel;
    }

    public DM getDomainModel() {
        return domainModel;
    }
}
