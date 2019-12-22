package org.viqueen.java.bytecode.decompiler;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.viqueen.java.bytecode.ClassFile;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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
        assertThat("ConstantPool", decompiled.getConstantPool(), notNullValue());

        // reached end of stream
        assertThat("End of DataInputStream", inputStream.available(), is(0));
    }
}
