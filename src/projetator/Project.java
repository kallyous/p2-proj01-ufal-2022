package projetator;

public class Project extends Entity {

    private String description;



    public Project(long id) {
        super(id, EntiType.PROJECT);
        description = "";
    }



    // DESCRIÇÃO
    public String description() { return description; }
    public void setDescription(String description) { this.description = description; }


}
