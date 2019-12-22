package org.viqueen.java.bytecode;

import org.viqueen.java.datastream.DataStreamCodec;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InterfacesDataStreamCodec implements DataStreamCodec {

    @Override
    public <TYPE> boolean supports(Class<TYPE> type) {
        return Interfaces.class.equals(type);
    }

    @Override
    public <TYPE> TYPE decode(
            final DataInputStream stream,
            final Class<TYPE> type,
            final Collection<Annotation> annotations) throws IOException {
        final int count = stream.readUnsignedShort();
        final List<Integer> interfaces = new ArrayList<>();

        for (int index = 0; index < count; index++) {
            interfaces.add(stream.readUnsignedShort());
        }

        return type.cast(new Interfaces(count, interfaces));
    }
}
