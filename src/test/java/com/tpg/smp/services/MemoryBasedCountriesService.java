package com.tpg.smp.services;

import com.tpg.smp.domain.Country;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static com.tpg.smp.domain.Country.*;
import static java.util.Arrays.asList;

@Service
public class MemoryBasedCountriesService implements CountriesService {
    private static final SortedSet<Country> COUNTRIES = new TreeSet<>();

    public static Set<Country> countries() {
        if (!COUNTRIES.isEmpty()) { return COUNTRIES; }

        COUNTRIES.addAll(asList(France, Germany, UnitedKingdom, UnitedStates));

        return  COUNTRIES;
    }

    @Override
    public Set<Country> findAll() {
        return null;
    }
}
