package org.viqueen.java.bytecode.decompiler;

import org.viqueen.java.bytecode.ClassFile;
import org.viqueen.java.datastream.DataStreamCodec;
import org.viqueen.java.datastream.DefaultDataStreamCodec;

import java.io.DataInputStream;
import java.io.IOException;

public class JavaDecompiler implements Decompiler {

    private final DataStreamCodec codec = new DefaultDataStreamCodec();

    @Override
    public ClassFile decompile(final DataInputStream inputStream) throws IOException {
        return codec.decode(inputStream, ClassFile.class);
    }

}
