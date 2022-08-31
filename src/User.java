public class User
{
    long uid;
    String uname;
    String urole;

    public User()
    {
        uid = genID();
    }

    public User(String name, String role)
    {
        uid = genID();
        uname = name;
        urole = role;
    }

    long genID() {
        return (long) (Math.random() * 1000000L);
    }

    public String name() {
        return uname;
    }

    public long id() {
        return uid;
    }

    public String role() {
        return urole;
    }
}
