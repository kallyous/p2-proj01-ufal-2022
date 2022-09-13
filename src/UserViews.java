import java.util.Vector;



public class UserViews extends View {



    public UserViews(String pl, String pd) {
        super(pl, pd);
    }



    // LIST / MAIN  VIEW
    public void list() {

        String opt = "";

        while (!opt.toLowerCase().equals("voltar")) {
            System.out.print(prompt_list);
            opt = System.console().readLine();

            // Rotina de adição de novo usuário.
            if (opt.toLowerCase().equals("novo")) {
                say("Criando novo usuário...");
                long id = genID();
                String name = ask("Nome completo da pessoa");
                String role = ask("Função");
                User u = new User(id);
                u.setName(name);
                u.setRole(role);
                user_base.add(u);
                say(name + " cadastrado com ID " + id);
                continue;
            }

            // Rotina de listagem dos usuários.
            if (opt.toLowerCase().equals("listar")) {
                say("\nID \t\t\tFunção \t\t\tNome");
                say("--------------------------------------------------------------------------------");
                for (User u : user_base) say(u.id() + "\t\t\t" + u.role() + "\t\t\t" + u.name());
                continue;
            }

            // Detalhes de um usuário específico
            try {
                long id = Long.parseLong(opt);
                boolean match = false;
                for (User u : user_base) {
                    if (u.id() == id) {
                        match = true;
                        detail(u);
                        break;
                    }
                }
                if (!match) say("Ninguém com ID " + id + " encontrado.");
            } catch (NumberFormatException nfe) {
                say("Opção inválida.");
            }
        }
    }



    // DETAIL / ACTIONS  VIEW
    public void detail(User user) {

        String opt = "";


        while(!opt.toLowerCase().equals("voltar")) {


            // INFO BÁSICAS
            say(prompt_detail);
            say("  ID:     " + user.id());
            say("  Nome:   " + user.name());
            say("  Função: " + user.role());


            // INFO PROJETOS
            Vector<Long> projects = user.getProjects();
            if (projects.size() > 0) {
                say("  Projetos:");
                for (Long pid : projects) {
                    Project p = getProjectByID(pid);
                    say("    " + pid + " - " + p.name());
                }
            }
            else
                say("  Não cadastrado em projetos.");


            // INFO ATIVIDADES
            Vector<Long> activities = user.getActivities();
            if (activities.size() > 0) {
                say("  Atividades:");
                for (Long aid : activities) {
                    Activity a = getActivityByID(aid);
                    say("    " + aid + " - " + a.name());
                }
            }
            else
                say("  Nenhuma atividade atribuída.");


            opt = ask("\nO que deseja fazer?");


            // NOME DE USUÁRIO, TROCAR
            if (opt.toLowerCase().equals("1")) {
                String name = ask("Mudar nome para?");
                user.setName(name);
                continue;
            }


            // FUNÇÃO, TROCAR
            if (opt.toLowerCase().equals("2")) {
                String role = ask("Mudar função para?");
                user.setRole(role);
                continue;
            }


            // ATIVIDADE, ATRIBUIR
            if (opt.toLowerCase().equals("3")) {

                say("\nAtividades disponíveis:");
                for (Activity a : activ_base)
                    say("  ID " + a.id() + " - " + a.name());

                String aid_str = ask("\nID da atividade a atribuir/remover?");
                long aid = Long.parseLong(aid_str);
                Activity activ = getActivityByID(aid);

                // Verifica se atividade com este ID existe.
                if (activ != null) {

                    // Desassocia usuário e atividade se já estão associados.
                    if (user.getActivities().contains(aid)) {
                        user.removeActivity(aid);
                        activ.removeUser(user.id());
                        say("\nATENÇÃO: Atividade '" + activ.name() + "' não está mais atribuída a " + user.name() + ".");
                    }

                    // Associa usuário e atividade se não estavam associados.
                    else {
                        user.addActivity(aid);
                        activ.addUser(user.id());
                        say("\nATENÇÃO: Atividade '" + activ.name() + "' agora está atribuída a " + user.name() + ".");
                    }

                } else say("Atividade não existe.");

                continue;
            }


            // PROJETO, CADASTRAR EM
            if (opt.toLowerCase().equals("4")) {
                say("\nProjetos disponíveis:");
                for (Project p : proj_base)
                    say("  ID " + p.id() + " - " + p.name());
                String pid_str = ask("\nID do projeto no qual cadastrar?");
                long pid = Long.parseLong(pid_str);
                Project proj = getProjectByID(pid);
                if (proj != null) {
                    if (user.addProject(pid))
                        say("\nATENÇÃO: " + user.name() + " cadastrado em " + proj.name() + " com sucesso.");
                    else
                        say("\nATENÇÃO: " + user.name() + " já cadastrado em " + proj.name() + ".");
                }
                else
                    say("Projeto não existe.");
                continue;
            }


            // EXCLUIR / DELETAR
            if (opt.toLowerCase().equals("del")) {
                user_base.remove(user);
                say(user.name() + " removido.");
                opt = "voltar";
            }

        }

    }



}