package org.viqueen.java.bytecode.cpool;

/**
 * CONSTANT_MethodHandle_info {
 *     u1 tag;
 *     u1 reference_kind;
 *     u2 reference_index;
 * }
 *
 * @since 1.0.0
 */
public class MethodHandleInfo extends CPInfo {

    private final int referenceKind;
    private final int referenceIndex;

    public MethodHandleInfo(int referenceKind, int referenceIndex) {
        super(Tag.METHOD_HANDLE);
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
    }

    public int getReferenceKind() {
        return referenceKind;
    }

    public int getReferenceIndex() {
        return referenceIndex;
    }
}
