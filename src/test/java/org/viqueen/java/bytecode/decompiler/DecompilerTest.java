package org.viqueen.java.bytecode.decompiler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.viqueen.java.bytecode.ClassFile;
import org.viqueen.java.bytecode.ConstantPool;
import org.viqueen.java.bytecode.Interfaces;
import org.viqueen.java.bytecode.cpool.ClassInfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class DecompilerTest {

    private final Decompiler decompiler;
    private DataInputStream inputStream;

    public DecompilerTest(final Decompiler decompiler) {
        this.decompiler = decompiler;
    }

    @Parameterized.Parameters
    public static Collection<Decompiler> decompilers() {
        ServiceLoader<Decompiler> serviceLoader = ServiceLoader.load(Decompiler.class);
        return serviceLoader.stream()
                .map(ServiceLoader.Provider::get)
                .collect(toSet());
    }

    @Before
    public void setup() {
        inputStream = new DataInputStream(
                this.getClass().getResourceAsStream("/DecompileMe.class")
        );
    }

    @Test
    public void testJ13Decompiler() throws IOException {
        ClassFile decompiled = decompiler.decompile(inputStream);

        // class information assertions
        assertThat("ClassFile", decompiled, notNullValue());
        assertThat("ClassFile#magic", decompiled.getMagic(), is(0xCAFEBABE));
        assertThat("ClassFile#minorVersion", decompiled.getMinorVersion(), is(Short.MAX_VALUE * 2 + 1));
        assertThat("ClassFile#majorVersion", decompiled.getMajorVersion(), is(57));

        // constant pool assertions
        ConstantPool constantPool = decompiled.getConstantPool();
        assertThat("ConstantPool", constantPool, notNullValue());
        assertThat("ConstantPool#count", constantPool.getCount(), is(25));

        // this_class
        // The value of the this_class item must be a valid index into the constant_pool table.
        // The constant_pool entry at that index must be a CONSTANT_Class_info structure (§4.4.1)
        // representing the class or interface defined by this class file.
        assertThat("ClassFile#thisClass", constantPool.get(decompiled.getThisClass()), instanceOf(ClassInfo.class));

        // super_class
        // For a class, the value of the super_class item either must be zero or must be a valid index into the constant_pool table.
        //  If the value of the super_class item is nonzero, the constant_pool entry at that index must be a CONSTANT_Class_info structure (§4.4.1)
        //  representing the direct superclass of the class defined by this class file
        assertThat(decompiled.getSuperClass(), not(0));
        assertThat(constantPool.get(decompiled.getSuperClass()), instanceOf(ClassInfo.class));
        assertThat(constantPool.getClassName(decompiled.getSuperClass()), is(Object.class.getCanonicalName()));

        // interfaces_count
        // The value of the interfaces_count item gives the number of direct superinterfaces of this class or interface type.
        //
        // interfaces[]
        // Each value in the interfaces array must be a valid index into the constant_pool table.
        // The constant_pool entry at each value of interfaces[i], where 0 ≤ i < interfaces_count, must be a CONSTANT_Class_info structure (§4.4.1)
        // representing an interface that is a direct superinterface of this class or interface type,
        // in the left-to-right order given in the source for the type.
        Interfaces interfaces = decompiled.getInterfaces();
        assertThat(interfaces, notNullValue());
        assertThat(interfaces.getCount(), is(1));
        assertThat(constantPool.get(interfaces.get(0)), instanceOf(ClassInfo.class));
        assertThat(constantPool.getClassName(interfaces.get(0)), is(Serializable.class.getCanonicalName()));

        // reached end of stream
        assertThat("End of DataInputStream", inputStream.available(), is(0));
    }
}
