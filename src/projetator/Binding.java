package projetator;


public class Binding {

    private final long id;
    private final EntiType type;



    public Binding(long id, EntiType type) {
        this.id = id;
        this.type = type;
    }



    public long id() { return id; }

    public EntiType type() { return type; }

}
