package org.viqueen.java.bytecode.cpool;

/**
 * CONSTANT_String_info {
 *     u1 tag;
 *     u2 string_index;
 * }
 *
 * @since 1.0.0
 */
public class StringInfo extends CPInfo {

    private final int stringIndex;

    public StringInfo(final int stringIndex) {
        super(Tag.STRING);
        this.stringIndex = stringIndex;
    }

    public int getStringIndex() {
        return stringIndex;
    }
}
