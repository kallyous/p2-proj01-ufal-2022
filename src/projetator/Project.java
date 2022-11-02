package projetator;

import java.util.Vector;



public class Project extends Entity {

    String description;



    public Project(long id) {
        super(id, EntiType.PROJECT);
    }



    // DESCRIÇÃO
    public String description() { return description; }
    public void setDescription(String description) { this.description = description; }


}
