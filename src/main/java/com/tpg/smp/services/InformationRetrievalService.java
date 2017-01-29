package com.tpg.smp.services;

import com.tpg.smp.domain.Country;
import com.tpg.smp.domain.GenderType;

import java.util.Collection;

public interface InformationRetrievalService {
    Collection<Country> loadCountries();

    Collection<GenderType> loadGenders();
}
