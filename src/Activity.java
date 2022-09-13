import java.util.Vector;



public class Activity extends Entity {

    String description;


    public Activity(long id) {
        super(id);
        users = new Vector<Long>();
        projects = new Vector<Long>();
    }


    // DESCRIÇÃO
    public String description() { return description; }
    public void setDescription(String description) { this.description = description; }

}
