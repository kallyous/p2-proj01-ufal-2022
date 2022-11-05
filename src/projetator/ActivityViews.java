package projetator;

import java.time.LocalDateTime;

import static projetator.ConsoleIO.datetime_pattern;
import static projetator.ConsoleIO.datetime_formatter;
import static projetator.ConsoleIO.say;
import static projetator.ConsoleIO.ask;


public class ActivityViews extends View {



    public ActivityViews(String pl, String pd) {
        super(pl, pd);
    }



    // LIST / MAIN  VIEW
    public void list()  {

        String opt = "";

        while (!opt.toLowerCase().equals("voltar")) {
            System.out.print(prompt_list);
            opt = System.console().readLine();

            // Rotina de adição de nova atividade.
            if (opt.equalsIgnoreCase("nova")) {
                create();
                continue;
            }

            // Rotina de listagem das atividades.
            if (opt.toLowerCase().equals("listar")) {
                say("\nID \t\t\tNome");
                say("--------------------------------------------------------------------------------");
                for (Entity e : actv_base) say(e.id() + "\t\t\t" + e.name());
                continue;
            }

            // Detalhes de uma atividade específica
            try {
                long id = Long.parseLong(opt);
                boolean match = false;
                Activity a;
                for (Entity e : actv_base) {
                    if (e.id() == id) {
                        match = true;
                        a = (Activity) e;
                        detail(a);
                        break;
                    }
                }
                if (!match) say("Nenhuma atividade com ID " + id + " encontrada.");
            } catch (NumberFormatException ex) {
                say("Opção inválida.");
            }

        }

    }



    // DETAIL / ACTIONS  VIEW
    public void detail(Activity activ) {

        String opt = "";

        while(!opt.equalsIgnoreCase("voltar")) {

            say(prompt_detail);
            say("  Nome:\t" + activ.name());
            say("  ID:\t" + activ.id());
            say("  Coordenador:\t" + activ.supervisor());
            say("  Início:\t" + activ.startTime().format(datetime_formatter));
            say("  Término:\t" + activ.endTime().format(datetime_formatter));

            say("  Tarefas:");
            for (Pair t : activ.tasks()) say("    Tarefa: " + t.value() + " - Profissional: " + t.key());

            displayBindings(activ);

            opt = ask("O que deseja fazer?");


            // NOME, TROCAR
            if (opt.equalsIgnoreCase("1")) {
                String name = ask("Mudar nome para?");
                activ.setName(name);
                continue; }


            // PROJETO, INSERIR ATIVIDADE
            if (opt.equalsIgnoreCase("2")) {
                bindingPrompt(activ, EntiType.PROJECT);
                continue; }


            // USUÁRIOS, ADICIONAR
            if (opt.equalsIgnoreCase("3")) {
                bindingPrompt(activ, EntiType.USER);
                continue; }


            // USUÁRIO RESPONSÁVEL PELA ATIVIDADE
            if (opt.equalsIgnoreCase("4")) {
                long sup_id = Long.parseLong( ask("ID do usuário responsável pela atividade") );
                boolean valid = false;
                for (Binding b : activ.bindings())
                    if (b.id() == sup_id) valid = true;
                if (valid) activ.setSupervisor(sup_id);
                else say("Forneça o ID de um usuário vinculado à atividade.");
                continue; }


            // TAREFAS E PROFISSIONAIS, ADICIONAR
            if (opt.equalsIgnoreCase("5")) {
                long prof_id = Long.parseLong( ask("ID do usuário a receber a tarefa") );
                boolean valid = false;
                for (Binding b : activ.bindings())
                    if (b.id() == prof_id) valid = true;
                if (!valid) {
                    say("Forneça o ID de um usuário vinculado à atividade para delegar-lhe a tarefa");
                    continue; }
                String desc_name = ask("Nome descritivo da atividade");
                Pair<Long, String> task = new Pair<>(prof_id, desc_name);
                activ.tasks().add(task);
            }


            // EXCLUIR / DELETAR
            if (opt.equalsIgnoreCase("del")) {
                delete(activ);
                say(activ.name() + " removido.");
                opt = "voltar"; }

        }

    }


    // CREATE ACTIVITY
    private void create() {

        String value;
        long id = genID();
        Activity a = new Activity(id);

        say("Criando nova atividade...");

        value = ask("Nome da atividade");
        a.setName(value);

        value = ask("Descrição da atividade");
        a.setDescription(value);

        // Supervisor do projeto
        long supervisor_id = -1;
        boolean valid = false;
        do {
            try {
                supervisor_id = Long.parseLong( ask("ID do supervisor da atividade") );
                valid = true;
            }
            catch (NumberFormatException ex) {
                say("Entre o ID válido de um usuário.");
            }
        } while (!valid);
        a.setSupervisor(supervisor_id);

        // Data e hora de início da atividade.
        LocalDateTime date_time = null;
        valid = false;
        do {
            try {
                String datetime_str = ask("Data de início da atividade, no formato " + datetime_pattern);
                date_time = LocalDateTime.parse(datetime_str, datetime_formatter);
                valid = true;
            }
            catch (Exception ex) {
                say("Entre uma data válida no formato " + datetime_pattern);
            }
        } while (!valid);
        a.setStartTime(date_time);


        // Data e hora de término da atividade.
        date_time = null;
        valid = false;
        do {
            try {
                String datetime_str = ask("Data de término do projeto, no formato " + datetime_pattern);
                date_time = LocalDateTime.parse(datetime_str, datetime_formatter);
                valid = true;
            }
            catch (Exception ex) {
                say("Entre uma data válida no formato " + datetime_pattern);
            }
        } while (!valid);
        a.setEndTime(date_time);

        // Adicionar profissionais
        value = ask("Adicionar profissionais? (S/N)").toLowerCase();
        if (value.equals("sim") || value.equals("s"))
            say("Não implementado. haha!");

        actv_base.add(a);
        say(a.name() + " cadastrada com ID " + id);
    }
}