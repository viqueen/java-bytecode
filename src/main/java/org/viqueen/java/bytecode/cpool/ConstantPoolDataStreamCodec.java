package org.viqueen.java.bytecode.cpool;

import org.viqueen.java.bytecode.ConstantPool;
import org.viqueen.java.datastream.DataStreamCodec;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConstantPoolDataStreamCodec implements DataStreamCodec {

    @Override
    public <TYPE> boolean supports(Class<TYPE> type) {
        return ConstantPool.class.equals(type);
    }

    @Override
    public <TYPE> TYPE decode(
            final DataInputStream stream,
            final Class<TYPE> type,
            final Collection<Annotation> annotations) throws IOException {
        int count = stream.readUnsignedShort();
        List<CPInfo> cpInfos = new ArrayList<>(count - 1);

        for (int index = 1; index < count; index++) {
            CPInfo.Tag tag = CPInfo.Tag.fromValue(stream.readUnsignedByte());
            switch (tag) {
                case CLASS ->
                        cpInfos.add(new ClassInfo(stream.readUnsignedShort()));
                case FIELD_REF, METHOD_REF, INTERFACE_METHOD_REF ->
                        cpInfos.add(new FMIRefInfo(tag, stream.readUnsignedShort(), stream.readUnsignedShort()));
                case STRING ->
                        cpInfos.add(new StringInfo(stream.readUnsignedShort()));
                case INTEGER ->
                        cpInfos.add(new IntegerInfo(stream.readInt()));
                case FLOAT ->
                        cpInfos.add(new FloatInfo(stream.readFloat()));
                case LONG -> {
                    cpInfos.add(new LongInfo(stream.readLong()));
                    cpInfos.add(new Padding());
                    index++;
                }
                case DOUBLE -> {
                    cpInfos.add(new DoubleInfo(stream.readDouble()));
                    cpInfos.add(new Padding());
                }
                case NAME_AND_TYPE ->
                        cpInfos.add(new NameAndTypeInfo(stream.readUnsignedShort(), stream.readUnsignedShort()));
                case UTF8 ->
                        cpInfos.add(new UTF8Info(stream.readUTF()));
                case METHOD_HANDLE ->
                        cpInfos.add(new MethodHandleInfo(stream.readUnsignedByte(), stream.readUnsignedShort()));
                case METHOD_TYPE ->
                        cpInfos.add(new MethodTypeInfo(stream.readUnsignedShort()));
                case INVOKE_DYNAMIC ->
                        cpInfos.add(new InvokeDynamicInfo(stream.readUnsignedShort(), stream.readUnsignedShort()));
            }
        }

        return type.cast(new ConstantPool(count, cpInfos));
    }

}
