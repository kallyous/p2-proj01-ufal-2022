package projetator;

import static projetator.ConsoleIO.ask;
import static projetator.ConsoleIO.say;



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
                say("Criando novo projeto...");
                long id = genID();
                String name = ask("Nome do projeto");
                Project p = new Project(id);
                p.setName(name);
                proj_base.add(p);
                say(name + " cadastrado com ID " + id);
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



    // DETAIL / ACTIONS  VIEW
    public void detail(Project proj) {

        String opt = "";

        while(!opt.toLowerCase().equals("voltar")) {

            say(prompt_detail);
            say("  Nome:\t" + proj.name());
            say("  ID:\t" + proj.id());
            displayBindings(proj);

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