package org.viqueen.java.bytecode.cpool;

/**
 * CONSTANT_Float_info {
 *     u1 tag;
 *     u4 bytes;
 * }
 * @since 1.0.0
 */
public class FloatInfo extends CPInfo {

    private final float value;

    public FloatInfo(float value) {
        super(Tag.FLOAT);
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}
