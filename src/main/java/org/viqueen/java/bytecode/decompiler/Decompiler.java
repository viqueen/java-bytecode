package org.viqueen.java.bytecode.decompiler;

import org.viqueen.java.bytecode.ClassFile;

import java.io.DataInputStream;

public interface Decompiler {

    ClassFile decompile(final DataInputStream inputStream);

}
