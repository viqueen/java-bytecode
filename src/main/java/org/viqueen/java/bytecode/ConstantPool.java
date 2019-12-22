package org.viqueen.java.bytecode;

import org.viqueen.java.bytecode.cpool.CPInfo;
import org.viqueen.java.bytecode.cpool.ClassInfo;
import org.viqueen.java.bytecode.cpool.UTF8Info;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public class ConstantPool {

    private final int count;
    private final List<CPInfo> cpInfos;

    public ConstantPool(int count, List<CPInfo> cpInfos) {
        this.count = count;
        this.cpInfos = unmodifiableList(cpInfos);
    }

    public int getCount() {
        return count;
    }

    public CPInfo get(int index) {
        return cpInfos.get(index - 1);
    }

    public <TYPE extends CPInfo> TYPE get(int index, Class<TYPE> type) {
        return type.cast(cpInfos.get(index - 1));
    }

    public String getClassName(int index) {
        return get(
                get(index, ClassInfo.class).getNameIndex(),
                UTF8Info.class
        ).getValue().replace("/", ".");
    }
}
