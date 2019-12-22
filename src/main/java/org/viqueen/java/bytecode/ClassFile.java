package org.viqueen.java.bytecode;

import org.viqueen.java.bytecode.cpool.ConstantPoolDataStreamCodec;
import org.viqueen.java.datastream.Custom;
import org.viqueen.java.datastream.Unsigned;

/**
 * ClassFile {
 *     u4             magic;
 *     u2             minor_version;
 *     u2             major_version;
 *     u2             constant_pool_count;
 *     cp_info        constant_pool[constant_pool_count-1];
 *     u2             access_flags;
 *     u2             this_class;
 *     u2             super_class;
 *     u2             interfaces_count;
 *     u2             interfaces[interfaces_count];
 *     u2             fields_count;
 *     field_info     fields[fields_count];
 *     u2             methods_count;
 *     method_info    methods[methods_count];
 *     u2             attributes_count;
 *     attribute_info attributes[attributes_count];
 * }
 *
 * @since 1.0.0
 */
@SuppressWarnings("unused")
public class ClassFile {

    @Unsigned(type = Unsigned.Type.FOUR)
    private int magic;
    @Unsigned(type = Unsigned.Type.TWO)
    private int minorVersion;
    @Unsigned(type = Unsigned.Type.TWO)
    private int majorVersion;

    @Custom(codec = ConstantPoolDataStreamCodec.class)
    private ConstantPool constantPool;

    @Unsigned(type = Unsigned.Type.TWO)
    private int accessFlags;
    @Unsigned(type = Unsigned.Type.TWO)
    private int thisClass;
    @Unsigned(type = Unsigned.Type.TWO)
    private int superClass;

    @Custom(codec = InterfacesDataStreamCodec.class)
    private Interfaces interfaces;

    private Fields fields;
    private Methods methods;
    private Attributes attributes;

    public ClassFile() {

    }

    public int getMagic() {
        return magic;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public int getThisClass() {
        return thisClass;
    }

    public int getSuperClass() {
        return superClass;
    }

    public Interfaces getInterfaces() {
        return interfaces;
    }

    public Fields getFields() {
        return fields;
    }

    public Methods getMethods() {
        return methods;
    }

    public Attributes getAttributes() {
        return attributes;
    }
}
