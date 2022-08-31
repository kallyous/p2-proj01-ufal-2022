public class User
{
    long uid;
    String uname;
    String urole;

    public User(long id)
    {
        uid = id;
    }

    public User(long id, String name, String role)
    {
        uid = id;
        uname = name;
        urole = role;
    }

    public long id() { return uid; }

    public String name() { return uname; }
    public void setName(String name) { uname = name; }

    public String role() { return urole; }
    public void setRole(String role) { urole = role; }
}
