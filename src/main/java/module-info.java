import org.viqueen.java.bytecode.decompiler.Decompiler;
import org.viqueen.java.bytecode.decompiler.JavaDecompiler;

module org.viqueen.java.bytecode {
    exports org.viqueen.java.bytecode;
    exports org.viqueen.java.bytecode.cpool;
    exports org.viqueen.java.bytecode.decompiler;
    requires java.base;
    uses Decompiler;
    provides Decompiler with JavaDecompiler;
}