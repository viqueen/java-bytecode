package org.viqueen.java.bytecode.cpool;

/**
 * CONSTANT_Class_info {
 *     u1 tag;
 *     u2 name_index;
 * }
 *
 * @since 1.0.0
 */
public class ClassInfo extends CPInfo {

    private final int nameIndex;

    public ClassInfo(int nameIndex) {
        super(Tag.CLASS);
        this.nameIndex = nameIndex;
    }

    public int getNameIndex() {
        return nameIndex;
    }
}
