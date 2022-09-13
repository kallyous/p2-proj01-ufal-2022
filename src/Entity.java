import java.util.Vector;



public class Entity {
    long id;
    String name;
    Vector<Long> users;
    Vector<Long> projects;
    Vector<Long> activities;


    public Entity(long id) {
        this.id = id;
    }


    // ID
    public long id() { return id; }


    // NOME
    public String name() { return name; }
    public void setName(String name) { this.name = name; }


    // USUÁRIOS
    public Vector<Long> getUsers() {return users; }
    public boolean addUser(long user_id) {
        if (users.contains(user_id)) return false;
        return users.add(user_id);
    }
    public boolean removeUser(long user_id) { return users.remove(user_id); }


    // PROJETOS
    public Vector<Long> getProjects() { return projects; }
    public boolean addProject(long proj_id) {
        if (projects.contains(proj_id)) return false;
        return projects.add(proj_id);
    }
    public boolean removeProject(long proj_id) { return projects.remove(proj_id); }


    // ATIVIDADES
    public Vector<Long> getActivities() {return activities; }
    public boolean addActivity(long activ_id) {
        if (activities.contains(activ_id)) return false;
        return activities.add(activ_id);
    }
    public boolean removeActivity(long activ_id) { return activities.remove(activ_id); }


}