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



    // OVERRIDES QUE IMPEDEM ASSOCIAÇÕES ENTRE ATIVIDADES
    public Vector<Long> getActivities() {
        View.say("ERRO: Não se associa atividade a atividade.");
        return null; }
    public boolean addActivity(long activ_id) {
        View.say("ERRO: Não se associa atividade a atividade.");
        return false; }
    public boolean removeActivity(long activ_id) {
        View.say("ERRO: Não se associa atividade a atividade.");
        return false; }


}
