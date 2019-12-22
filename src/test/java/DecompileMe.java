import java.io.Serializable;

@SuppressWarnings("unused")
public class DecompileMe implements Serializable {

    private final String name;

    public DecompileMe(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
