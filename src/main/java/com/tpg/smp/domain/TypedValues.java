package com.tpg.smp.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class TypedValues<T> {
    private final Set<T> values = new HashSet<>();

    public TypedValues(Collection<T> typeValues) {
        values.addAll(typeValues);
    }

    public Set<T> TypedValues() {
        return values;
    }

    public Stream<T> stream() { return values.stream(); }
}
