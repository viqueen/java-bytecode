package org.viqueen.java.datastream;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;

public interface DataStreamCodec {

    default <TYPE> TYPE decode(final DataInputStream stream, Class<TYPE> type) throws IOException {
        return decode(stream, type, Collections.emptyList());
    }

    <TYPE> TYPE decode(final DataInputStream stream, Class<TYPE> type, Collection<Annotation> annotations)
            throws IOException;

    <TYPE> boolean supports(Class<TYPE> type);

}
