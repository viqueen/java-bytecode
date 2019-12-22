package org.viqueen.java.bytecode.cpool;

import static java.util.Arrays.stream;

/**
 *
 * CONSTANT_Class	7
 * CONSTANT_Fieldref	9
 * CONSTANT_Methodref	10
 * CONSTANT_InterfaceMethodref	11
 * CONSTANT_String	8
 * CONSTANT_Integer	3
 * CONSTANT_Float	4
 * CONSTANT_Long	5
 * CONSTANT_Double	6
 * CONSTANT_NameAndType	12
 * CONSTANT_Utf8	1
 * CONSTANT_MethodHandle	15
 * CONSTANT_MethodType	16
 * CONSTANT_InvokeDynamic	18
 *
 * cp_info {
 *     u1 tag;
 *     u1 info[];
 * }
 *
 * @since 1.0.0
 */
public abstract class CPInfo {

    // TODO : do we need to retain this info ?
    private final Tag tag;

    protected CPInfo(final Tag tag) {
        this.tag = tag;
    }

    public enum Tag {
        PADDING(0),
        UTF8(1),
        INTEGER(3),
        FLOAT(4),
        LONG(5),
        DOUBLE(6),
        CLASS(7),
        STRING(8),
        FIELD_REF(9),
        METHOD_REF(10),
        INTERFACE_METHOD_REF(11),
        NAME_AND_TYPE(12),
        METHOD_HANDLE(15),
        METHOD_TYPE(16),
        INVOKE_DYNAMIC(18);
        private final int value;

        Tag(int value) {
            this.value = value;
        }

        public static Tag fromValue(final int value) {
            return stream(values())
                    .filter(t -> t.value == value)
                    .findFirst()
                    .orElseThrow();
        }
    }
}
