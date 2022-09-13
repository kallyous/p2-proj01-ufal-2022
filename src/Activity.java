import java.util.Vector;



public class Activity extends Entity {

    String description;



    public Activity(long id) {
        super(id, EntiType.ACTIVITY);
    }



    // DESCRIÇÃO
    public String description() { return description; }
    public void setDescription(String description) { this.description = description; }


}
