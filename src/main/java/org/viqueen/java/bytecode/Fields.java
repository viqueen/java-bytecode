package org.viqueen.java.bytecode;

import java.util.Collections;
import java.util.List;

public class Fields {

    private final int count;
    private final List<FieldInfo> fields;

    public Fields(int count, List<FieldInfo> fields) {
        this.count = count;
        this.fields = Collections.unmodifiableList(fields);
    }

    public int getCount() {
        return count;
    }

    public List<FieldInfo> getFields() {
        return fields;
    }
}
