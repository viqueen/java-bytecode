package org.viqueen.java.datastream;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public class PrimitiveDataStreamCodec implements DataStreamCodec {

    private final Set<Class<?>> types = Set.of(
            boolean.class, Boolean.class,
            byte.class, Byte.class,
            char.class, Character.class,
            double.class, Double.class,
            float.class, Float.class,
            int.class, Integer.class,
            long.class, Long.class,
            short.class, Short.class
    );

    @Override
    public <TYPE> boolean supports(Class<TYPE> type) {
        return types.contains(type);
    }

    @Override
    public <TYPE> TYPE decode(
            final DataInputStream stream,
            final Class<TYPE> type,
            final Collection<Annotation> annotations) throws IOException {
        if (type == int.class || type == Integer.class) {
            Optional<Unsigned> unsigned = annotations.stream()
                    .filter(a -> a.annotationType().equals(Unsigned.class))
                    .map(Unsigned.class::cast)
                    .findFirst();
            final int value;
            if (unsigned.isPresent()) {
                value = switch (unsigned.get().type()) {
                    case ONE -> stream.readUnsignedByte();
                    case TWO -> stream.readUnsignedShort();
                    case FOUR -> stream.readInt();
                };
            } else {
                value = stream.readInt();
            }
            //noinspection unchecked
            return (TYPE) Integer.valueOf(value);
        }
        if (type == long.class || type == Long.class) {
            //noinspection unchecked
            return (TYPE) Long.valueOf(stream.readLong());
        }
        if (type == short.class || type == Short.class) {
            //noinspection unchecked
            return (TYPE) Short.valueOf(stream.readShort());
        }
        if (type == double.class || type == Double.class) {
            //noinspection unchecked
            return (TYPE) Double.valueOf(stream.readDouble());
        }
        if (type == float.class || type == Float.class) {
            //noinspection unchecked
            return (TYPE) Float.valueOf(stream.readFloat());
        }
        if (type == boolean.class || type == Boolean.class) {
            //noinspection unchecked
            return (TYPE) Boolean.valueOf(stream.readBoolean());
        }
        if (type == char.class || type == Character.class) {
            //noinspection unchecked
            return (TYPE) Character.valueOf(stream.readChar());
        }
        if (type == byte.class || type == Byte.class) {
            //noinspection unchecked
            return (TYPE) Byte.valueOf(stream.readByte());
        }
        throw new IOException("decodePrimitive failed : " + type);
    }
}
