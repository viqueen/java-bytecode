package org.viqueen.java.datastream;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static java.util.Arrays.asList;

public class DefaultDataStreamCodec implements DataStreamCodec {

    private final Collection<DataStreamCodec> codecs = List.of(
            new PrimitiveDataStreamCodec()
    );
    private final Map<Class<? extends DataStreamCodec>, DataStreamCodec> customCodecs = new HashMap<>();

    @Override
    public <TYPE> boolean supports(final Class<TYPE> type) {
        return true;
    }

    @Override
    public <TYPE> TYPE decode(
            final DataInputStream stream,
            final Class<TYPE> type,
            final Collection<Annotation> annotations) throws IOException {

        Optional<DataStreamCodec> codec = codecs.stream()
                .filter(c -> c.supports(type))
                .findFirst();

        if (codec.isPresent()) {
            return codec.get().decode(stream, type, annotations);
        }

        Optional<Custom> custom = annotations.stream()
                .filter(a -> a.annotationType().equals(Custom.class))
                .map(Custom.class::cast)
                .findFirst();
        if (custom.isPresent()) {
            Class<? extends DataStreamCodec> codecType = custom.get().codec();
            DataStreamCodec customCodec = customCodecs.computeIfAbsent(codecType, (key) -> {
                try {
                    return codecType.getDeclaredConstructor().newInstance();
                } catch (InstantiationException
                        | NoSuchMethodException
                        | InvocationTargetException
                        | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
            if (customCodec.supports(type)) {
                return customCodec.decode(stream, type, annotations);
            }
        }

        try {
            TYPE data = type.getDeclaredConstructor().newInstance();
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                field.set(data, decode(stream, field.getType(), asList(field.getDeclaredAnnotations())));
            }
            return data;
        } catch (InstantiationException
                | IllegalAccessException
                | InvocationTargetException
                | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
