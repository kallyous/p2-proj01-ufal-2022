
public class Project {

    long pid;
    String pname;
    String pdescription;

    public Project(long id) {
        pid = id;
    }

    public Project(long id, String name) {
        pid = id;
        pname = name;
    }

    public Project(long id, String name, String descriptiom) {
        pid = id;
        pname = name;
        pdescription = descriptiom;
    }

    public long id() { return pid; }

    public String name() { return pname; }
    public void setName(String name) { pname = name; }

    public String description() { return pdescription; }
    public void setDescription(String description) { pdescription = description; }

}
