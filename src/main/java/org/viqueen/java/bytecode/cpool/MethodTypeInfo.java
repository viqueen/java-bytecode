package org.viqueen.java.bytecode.cpool;

/**
 * CONSTANT_MethodType_info {
 *     u1 tag;
 *     u2 descriptor_index;
 * }
 *
 * @since 1.0.0
 */
public class MethodTypeInfo extends CPInfo {

    private final int descriptorIndex;

    public MethodTypeInfo(int descriptorIndex) {
        super(Tag.METHOD_TYPE);
        this.descriptorIndex = descriptorIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
