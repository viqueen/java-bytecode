package org.viqueen.java.bytecode;

import org.viqueen.java.datastream.DataStreamCodec;
import org.viqueen.java.datastream.DefaultDataStreamCodec;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.emptyList;

/**
 * field_info {
 *     u2             access_flags;
 *     u2             name_index;
 *     u2             descriptor_index;
 *     u2             attributes_count;
 *     attribute_info attributes[attributes_count];
 * }
 *
 * @since 1.0.0
 */
public class FieldsDataStreamCodec implements DataStreamCodec {

    // TODO : use service loader ?
    private final DataStreamCodec codec = new DefaultDataStreamCodec();

    @Override
    public <TYPE> boolean supports(Class<TYPE> type) {
        return Fields.class.equals(type);
    }

    @Override
    public <TYPE> TYPE decode(DataInputStream stream, Class<TYPE> type, Collection<Annotation> annotations) throws IOException {
        final int count = stream.readUnsignedShort();
        final List<FieldInfo> fields = new ArrayList<>();

        for (int index = 0; index < count; index++) {
            fields.add(codec.decode(stream, FieldInfo.class, emptyList()));
        }

        return type.cast(new Fields(count, fields));
    }
}
