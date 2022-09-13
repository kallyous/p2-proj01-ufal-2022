import java.util.Vector;



public class User
{
    long uid;
    String uname;
    String urole;
    Vector<Long> projects;
    Vector<Long> activities;

    public User(long id)
    {
        uid = id;
        projects = new Vector<Long>();
        activities = new Vector<Long>();
    }

    public long id() { return uid; }

    public String name() { return uname; }
    public void setName(String name) { uname = name; }

    public String role() { return urole; }
    public void setRole(String role) { urole = role; }

    public Vector<Long> getProjects() { return projects; }
    public boolean addProject(long proj_id) {
        if (projects.contains(proj_id)) return false;
        return projects.add(proj_id);
    }
    public boolean removeProject(long proj_id) { return projects.remove(proj_id); }

    public Vector<Long> getActivities() {return activities; }
    public boolean addActivity(long activ_id) {
        if (activities.contains(activ_id)) return false;
        return activities.add(activ_id);
    }
    public boolean removeActivity(long activ_id) { return activities.remove(activ_id); }

}
