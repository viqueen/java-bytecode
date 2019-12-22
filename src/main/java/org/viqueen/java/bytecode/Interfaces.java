package org.viqueen.java.bytecode;

import java.util.Collections;
import java.util.List;

public class Interfaces {

    private final int count;
    private final List<Integer> indexes;

    public Interfaces(int count, List<Integer> indexes) {
        this.count = count;
        this.indexes = Collections.unmodifiableList(indexes);
    }

    public int get(int index) {
        return indexes.get(index);
    }

    public int getCount() {
        return count;
    }
}
