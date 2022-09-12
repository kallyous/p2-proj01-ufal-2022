
public class Activity {

    long aid;
    String aname;
    String adescription;

    public Activity(long id) {
        aid = id;
    }

    public Activity(long id, String name) {
        aid = id;
        aname = name;
    }

    public Activity(long id, String name, String descriptiom) {
        aid = id;
        aname = name;
        adescription = descriptiom;
    }

    public long id() { return aid; }

    public String name() { return aname; }
    public void setName(String name) { aname = name; }

    public String description() { return adescription; }
    public void setDescription(String description) { adescription = description; }

}
