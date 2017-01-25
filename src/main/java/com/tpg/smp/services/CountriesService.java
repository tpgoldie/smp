package com.tpg.smp.services;

import com.tpg.smp.domain.Country;

import java.util.Set;

public interface CountriesService {
    Set<Country> findAll();
}
