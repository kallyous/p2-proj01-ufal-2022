import java.util.Vector;



public class User extends Entity {

    String role;



    public User(long id)
    {
        super(id);
        projects = new Vector<Long>();
        activities = new Vector<Long>();
    }



    // ROLE
    public String role() { return role; }
    public void setRole(String role) { this.role = role; }



    // OVERRIDES QUE IMPEDEM ASSOCIAÇÕES ENTRE USUÁRIOS
    public Vector<Long> getUsers() {
        View.say("ERRO: Não se associa usuário a usuário.");
        return null; }
    public boolean addUser(long user_id) {
        View.say("ERRO: Não se associa usuário a usuário.");
        return false; }
    public boolean removeUser(long user_id) {
        View.say("ERRO: Não se associa usuário a usuário.");
        return false; }

}
