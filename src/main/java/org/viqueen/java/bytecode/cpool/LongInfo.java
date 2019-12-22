package org.viqueen.java.bytecode.cpool;

/**
 * CONSTANT_Long_info {
 *     u1 tag;
 *     u4 high_bytes;
 *     u4 low_bytes;
 * }
 *
 * @since 1.0.0
 */
public class LongInfo extends CPInfo {

    private final long value;

    public LongInfo(long value) {
        super(Tag.LONG);
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
