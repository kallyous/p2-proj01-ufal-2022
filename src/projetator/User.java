package projetator;

public class User extends Entity {

    String role;



    public User(long id) {
        super(id, EntiType.USER);
    }



    // ROLE
    public String role() { return role; }
    public void setRole(String role) { this.role = role; }


}
