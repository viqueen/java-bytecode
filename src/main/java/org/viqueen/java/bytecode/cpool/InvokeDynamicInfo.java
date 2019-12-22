package org.viqueen.java.bytecode.cpool;

/**
 * CONSTANT_InvokeDynamic_info {
 *     u1 tag;
 *     u2 bootstrap_method_attr_index;
 *     u2 name_and_type_index;
 * }
 *
 * @since 1.0.0
 */
public class InvokeDynamicInfo extends CPInfo {

    private final int bootstrapMethodAttributeIndex;
    private final int nameAndTypeIndex;

    public InvokeDynamicInfo(int bootstrapMethodAttributeIndex, int nameAndTypeIndex) {
        super(Tag.INVOKE_DYNAMIC);
        this.bootstrapMethodAttributeIndex = bootstrapMethodAttributeIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public int getBootstrapMethodAttributeIndex() {
        return bootstrapMethodAttributeIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }
}
