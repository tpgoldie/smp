package com.tpg.smp.data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

abstract class TestData<T> {
    private List<T> dataList = new ArrayList<>();

    void add(T datum) { dataList.add(datum); }

    T get(int index) { return dataList.get(index); }

    Stream<T> stream() { return dataList.stream(); }
}
