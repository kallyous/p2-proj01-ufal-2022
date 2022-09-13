import java.util.Vector;



public class Entity {

    long id;
    EntiType type;
    String name;
    Vector<Binding> bindings;



    public Entity(long id, EntiType type) {
        this.id = id;
        this.type = type;
        bindings = new Vector<Binding>();
    }



    // ID
    public long id() { return id; }



    // Tipo da entidade
    public EntiType type() { return type; }



    // NOME
    public String name() { return name; }
    public void setName(String name) { this.name = name; }



    // VÍNCULOS ENTRE ENTIDADES

    public Binding getBinding(long id) {
        for (Binding b : bindings)
            if (b.id() == id) return b;
        return null; }

    public boolean addBinding(long id, EntiType type) {
        if (getBinding(id) != null) return false;
        if (this.type == type) return false;
        return bindings.add( new Binding(id, type) ); }

    public boolean removeBinding(long id) {
        Binding b = getBinding(id);
        if (b == null) return false;
        return bindings.remove(b);
    }

    public Vector<Binding> bindings() { return bindings; }



    // USUÁRIOS VINCULADOS
    public Vector<Long> getUsers() {
        Vector<Long> users = new Vector<Long>();
        for (Binding b : bindings)
            if (b.type == EntiType.USER) users.add(b.id());
        return users; }



    // PROJETOS VINCULADOS
    public Vector<Long> getProjects() {
        Vector<Long> projs = new Vector<Long>();
        for (Binding b : bindings)
            if (b.type == EntiType.PROJECT) projs.add(b.id());
        return projs; }



    // ATIVIDADES VINCULADAS
    public Vector<Long> getActivities() {
        Vector<Long> activs = new Vector<Long>();
        for (Binding b : bindings)
            if (b.type == EntiType.ACTIVITY) activs.add(b.id());
        return activs; }


}
