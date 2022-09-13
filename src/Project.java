import java.util.Vector;



public class Project extends Entity {

    String description;



    public Project(long id) {
        super(id);
        users = new Vector<Long>();
        activities = new Vector<Long>();
    }



    // DESCRIÇÃO
    public String description() { return description; }
    public void setDescription(String description) { this.description = description; }



    // OVERRIDES QUE IMPEDEM ASSOCIAÇÕES ENTRE PROJETOS
    public Vector<Long> getProjects() {
        View.say("ERRO: Não se associa projeto a projeto.");
        return null; }
    public boolean addProject(long proj_id) {
        View.say("ERRO: Não se associa projeto a projeto.");
        return false; }
    public boolean removeProject(long proj_id) {
        View.say("ERRO: Não se associa projeto a projeto.");
        return false; }


}
