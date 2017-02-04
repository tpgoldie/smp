package com.tpg.smp.services;

public abstract class AssertDomainModel<T> {
    T domainModel;

    AssertDomainModel(T domainModel) {
        this.domainModel = domainModel;
    }
}
