
public class Binding {

    long id;
    EntiType type;



    public Binding(long id, EntiType type) {
        this.id = id;
        this.type = type;
    }



    public long id() { return id; }

    public EntiType type() { return type; }

}
