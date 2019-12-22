package org.viqueen.java.bytecode;

import org.viqueen.java.datastream.Unsigned;

/**
 * field_info {
 *     u2             access_flags;
 *     u2             name_index;
 *     u2             descriptor_index;
 *     u2             attributes_count;
 *     attribute_info attributes[attributes_count];
 * }
 *
 * @since 1.0.0
 */
public class FieldInfo {

    @Unsigned(type = Unsigned.Type.TWO)
    private int accessFlags;
    @Unsigned(type = Unsigned.Type.TWO)
    private int nameIndex;
    @Unsigned(type = Unsigned.Type.TWO)
    private int descriptorIndex;

    private Attributes attributes;

    public int getAccessFlags() {
        return accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
