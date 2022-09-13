import java.util.Vector;               // Lista de tamanho variável



public class ProjectViews extends View {



    public ProjectViews(String pl, String pd) {
        super(pl, pd);
    }



    // PROJECT LIST VIEW
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
                Project p = new Project(id, name);
                proj_base.add(p);
                say(name + " cadastrado com ID " + id);
                continue;
            }

            // Rotina de listagem dos usuários.
            if (opt.toLowerCase().equals("listar")) {
                say("\nID \t\t\tNome");
                say("--------------------------------------------------------------------------------");
                for (Project p : proj_base) say(p.id() + "\t\t\t" + p.name());
                continue;
            }

            // Detalhes de um projeto específico
            try {
                long id = Long.parseLong(opt);
                boolean match = false;
                for (Project p : proj_base) {
                    if (p.id() == id) {
                        match = true;
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



    // PROJECT DETAIL VIEW
    public void detail(Project proj) {
        String opt = "";

        while(!opt.toLowerCase().equals("voltar")) {

            say(prompt_detail);
            say("  Nome:\t" + proj.name());
            say("  ID\t" + proj.id());
            say();

            opt = ask("O que deseja fazer?");

            if (opt.toLowerCase().equals("1")) {
                String name = ask("Mudar nome para?");
                proj.setName(name);
                continue;
            }

            if (opt.toLowerCase().equals("del")) {
                proj_base.remove(proj);
                say(proj.name() + " removido.");
                opt = "voltar";
            }

        }
    }



}