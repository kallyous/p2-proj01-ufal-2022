package projetator;

public class User extends Entity {

    Role role;


    public User(long id) {
        super(id, EntiType.USER);
    }


    // ROLE
    public Role role() { return role; }
    public void setRole(Role role) { this.role = role; }
    public void setRoleByString(String role) {
        try { this.role = Role.valueOf(role); }
        catch (Exception ex) { this.role = Role.NAO_ESPECIFICADO; }
    }


}
