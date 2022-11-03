package projetator;

public class Activity extends Entity {

    private String description;



    public Activity(long id) {
        super(id, EntiType.ACTIVITY);
        description = "";
    }



    // DESCRIÇÃO
    public String description() { return description; }
    public void setDescription(String description) { this.description = description; }


}
