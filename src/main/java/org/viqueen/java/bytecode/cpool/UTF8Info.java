package org.viqueen.java.bytecode.cpool;

/**
 * CONSTANT_Utf8_info {
 *     u1 tag;
 *     u2 length;
 *     u1 bytes[length];
 * }
 *
 * @since 1.0.0
 */
public class UTF8Info extends CPInfo {

    private final String value;

    public UTF8Info(String value) {
        super(Tag.UTF8);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
