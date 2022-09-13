import java.util.Vector;



public class Project extends Entity {

    String description;


    public Project(long id) {
        super(id);
    }



    // DESCRIÇÃO
    public String description() { return description; }
    public void setDescription(String description) { this.description = description; }

}
