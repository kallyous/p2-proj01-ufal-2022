package projetator;

import java.time.LocalDateTime;
import java.util.Vector;

public class Project extends Entity {

    private String description;
    private long coordinator;
    private LocalDateTime start_time;
    private LocalDateTime end_time;
    private LocalDateTime pay_start_time;
    private LocalDateTime pay_end_time;
    private Vector<Pair> pays;



    public Project(long id) {
        super(id, EntiType.PROJECT);
        description = "";
        pays = new Vector<Pair>();
    }



    // DESCRIÇÃO
    public void setDescription(String description) { this.description = description; }
    public String description() { return description; }

    // DATA E HORA DE INÍCIO DO PROJETO
    public void setStartTime(LocalDateTime start_time) { this.start_time = start_time; }
    public LocalDateTime startTime() { return start_time; }

    // DATA E HORA DO TÉRMINO DO PROJETO
    public void setEndTime(LocalDateTime end_time) { this.end_time = end_time; }
    public LocalDateTime endTime() { return end_time; }

    // COORDENADOR DO PROJETO, PELO ID
    public void setCoordinator(long coord_id) { this.coordinator = coord_id; }
    public long coordinator() { return coordinator; }

    // VALORES DAS BOLSAS, POR ID DE BENEFICIADO E VALOR
    public void setPays(Vector<Pair> pays) { this.pays = pays; }
    public Vector<Pair> pays() { return pays ; }

    // INÍCIO DA VIGÊNCIA DAS BOLSAS
    public void setPayStartTime(LocalDateTime pay_start_time) { this.pay_start_time = pay_start_time; }
    public LocalDateTime payStartTime() { return pay_start_time; }

    // FIM DA VIGÊNCIA DAS BOLSAS
    public void setPayEndTime(LocalDateTime pay_end_time) { this.pay_end_time = pay_end_time; }
    public LocalDateTime payEndTime() { return pay_end_time; }

}
