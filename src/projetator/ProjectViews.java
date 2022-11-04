package projetator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static projetator.ConsoleIO.ask;
import static projetator.ConsoleIO.say;
import static projetator.ConsoleIO.datetime_formatter;



public class ProjectViews extends View {



    public ProjectViews(String pl, String pd) {
        super(pl, pd);
    }



    // LIST / MAIN  VIEW
    public void list() {

        String opt = "";

        while (!opt.toLowerCase().equals("voltar")) {
            System.out.print(prompt_list);
            opt = System.console().readLine();

            // Rotina de adição de novo projeto.
            if (opt.toLowerCase().equals("novo")) {
                create();
                continue;
            }

            // Rotina de listagem dos usuários.
            if (opt.toLowerCase().equals("listar")) {
                say("\nID \t\t\tNome");
                say("--------------------------------------------------------------------------------");
                for (Entity e : proj_base) say(e.id() + "\t\t\t" + e.name());
                continue;
            }

            // Detalhes de um projeto específico
            try {
                long id = Long.parseLong(opt);
                boolean match = false;
                Project p;
                for (Entity e : proj_base) {
                    if (e.id() == id) {
                        match = true;
                        p = (Project) e;
                        detail(p);
                        break;
                    }
                }
                if (!match) say("Nenhum projeto com ID " + id + " encontrado.");
            } catch (NumberFormatException nfe) {
                say("Opção inválida.");
            }

        }

    }



    // CREATE VIEW
    public void create() {

        say("Criando novo projeto...");

        long id = genID();
        Project p = new Project(id);

        String value = ask("Nome do projeto");
        p.setName(value);

        value = ask("Descrição do projeto");
        p.setDescription(value);


        // Coordenador do projeto
        long coord_id = -1;
        boolean valid = false;
        do {
            try {
                coord_id = Long.parseLong( ask("ID do coordenador do projeto:") );
                valid = true;
            }
            catch (NumberFormatException ex) {
                say("Entre um ID válido de um professor ou pesquisador.");
            }
        } while (!valid);
        p.setCoordinator(coord_id);


        // Data e hora de início do projeto.
        LocalDateTime date_time = null;
        valid = false;
        do {
            try {
                String datetime_str = ask("Data de início do projeto, no formato AAAA/MM/DD hh:mm");
                date_time = LocalDateTime.parse(datetime_str, datetime_formatter);
                valid = true;
            }
            catch (Exception ex) {
                say("Entre uma data válida no formato AAAA/MM/DD hh:mm");
            }
        } while (!valid);
        p.setStartTime(date_time);


        // Data e hora de término do projeto.
        date_time = null;
        valid = false;
        do {
            try {
                String datetime_str = ask("Data de término do projeto, no formato AAAA/MM/DD hh:mm");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                date_time = LocalDateTime.parse(datetime_str, formatter);
                valid = true;
            }
            catch (Exception ex) {
                say("Entre uma data válida no formato AAAA/MM/DD hh:mm");
            }
        } while (!valid);
        p.setEndTime(date_time);


        // Data e hora do início da vigência das bolsas.
        date_time = null;
        valid = false;
        do {
            try {
                String datetime_str = ask("Data de início da vigência das bolsas, no formato AAAA/MM/DD hh:mm");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                date_time = LocalDateTime.parse(datetime_str, formatter);
                valid = true;
            }
            catch (Exception ex) {
                say("Entre uma data válida no formato AAAA/MM/DD hh:mm");
            }
        } while (!valid);
        p.setPayStartTime(date_time);


        // Data e hora do término da vigência das bolsas.
        date_time = null;
        valid = false;
        do {
            try {
                String datetime_str = ask("Data de término da vigência das bolsas, no formato AAAA/MM/DD hh:mm");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                date_time = LocalDateTime.parse(datetime_str, formatter);
                valid = true;
            }
            catch (Exception ex) {
                say("Entre uma data válida no formato AAAA/MM/DD hh:mm");
            }
        } while (!valid);
        p.setPayEndTime(date_time);


        // Adicionar bolsistas
        value = ask("Adicionar bolsistas? (S/N)").toLowerCase();
        if (value.equals("sim") || value.equals("s"))
            say("Não implementado. haha!");


        proj_base.add(p);
        say(p.name() + " cadastrado com ID " + id);
    }



    // DETAIL / ACTIONS  VIEW
    public void detail(Project proj) {

        String opt = "";

        while(!opt.toLowerCase().equals("voltar")) {

            say(prompt_detail);
            say("  ID:\t" + proj.id());
            say("  Nome:\t" + proj.name());
            say("  Início:\t" + proj.startTime().format(datetime_formatter));
            say("  Término:\t" + proj.endTime().format(datetime_formatter));
            say("  Vigência das bolsas:\t" + proj.payStartTime().format(datetime_formatter) + " - " + proj.payEndTime().format(datetime_formatter));
            say("  Descrição do projeto: \t" + proj.description());

            displayBindings(proj);

            say("  Bolsistas: ???");

            opt = ask("\nO que deseja fazer?");


            // NOME, TROCAR
            if (opt.toLowerCase().equals("1")) {
                String name = ask("Mudar nome para?");
                proj.setName(name);
                continue;
            }



            // USUÁRIO, INCLUIR NO PROJETO
            if (opt.toLowerCase().equals("2")) {
                bindingPrompt(proj, EntiType.USER);
                continue; }



            // ATIVIDADE, ADICIONAR AO PROJETO
            if (opt.toLowerCase().equals("3")) {
                bindingPrompt(proj, EntiType.ACTIVITY);
                continue; }



            // EXCLUIR / DELETAR
            if (opt.toLowerCase().equals("del")) {
                delete(proj);
                say(proj.name() + " removido.");
                opt = "voltar";
            }

        }

    }



}