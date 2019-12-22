package org.viqueen.java.bytecode.cpool;

/**
 * CONSTANT_Integer_info {
 *     u1 tag;
 *     u4 bytes;
 * }
 *
 * @since 1.0.0
 */
public class IntegerInfo extends CPInfo {

    private final int value;

    public IntegerInfo(int value) {
        super(Tag.INTEGER);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
