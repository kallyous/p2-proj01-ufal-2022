package projetator;

import java.time.LocalDateTime;
import java.util.Vector;

public class Activity extends Entity {

    private String description;
    private long supervisor;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private Vector<Pair> tasks;


    public Activity(long id) {
        super(id, EntiType.ACTIVITY);
        description = "";
        tasks = new Vector<>();
    }


    // DESCRIÇÃO
    public void setDescription(String description) { this.description = description; }
    public String description() { return description; }


    // RESPONSÁVEL PELA ATIVIDADE
    public void setSupervisor(long supervisor_id) { this.supervisor = supervisor_id; }
    public long supervisor() { return supervisor; }


    // DATA E HORA DE INÍCIO DO PROJETO
    public void setStartTime(LocalDateTime start_time) { this.start_time = start_time; }
    public LocalDateTime startTime() { return start_time; }


    // DATA E HORA DO TÉRMINO DO PROJETO
    public void setEndTime(LocalDateTime end_time) { this.end_time = end_time; }
    public LocalDateTime endTime() { return end_time; }


    // TAREFAS
    public void setTasks(Vector<Pair> task_list) { this.tasks = task_list; }
    public Vector<Pair> tasks() { return this.tasks; }


}
