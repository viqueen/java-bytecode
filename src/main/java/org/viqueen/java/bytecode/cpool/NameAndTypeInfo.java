package org.viqueen.java.bytecode.cpool;

/**
 * CONSTANT_NameAndType_info {
 *     u1 tag;
 *     u2 name_index;
 *     u2 descriptor_index;
 * }
 *
 * @since 1.0.0
 */
public class NameAndTypeInfo extends CPInfo {

    private final int nameIndex;
    private final int descriptorIndex;

    public NameAndTypeInfo(int nameIndex, int descriptorIndex) {
        super(Tag.NAME_AND_TYPE);
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
