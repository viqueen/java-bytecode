package org.viqueen.java.bytecode.cpool;

/**
 * CONSTANT_Double_info {
 *     u1 tag;
 *     u4 high_bytes;
 *     u4 low_bytes;
 * }
 *
 * @since 1.0.0
 */
public class DoubleInfo extends CPInfo {

    private final double value;

    public DoubleInfo(double value) {
        super(Tag.DOUBLE);
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
