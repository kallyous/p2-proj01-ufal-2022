import java.util.Vector;               // Lista de tamanho variável



public class UserViews extends View {



    public UserViews(Vector<User> ub,  Vector<Activity> ab, Vector<Project> pb, String pl, String pd) {
        super(ub, ab, pb, pl, pd);
    }



    // USER LIST VIEW
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
                User u = new User(id, name, role);
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



    // USER DETAIL VIEW
    public void detail(User user) {
        String opt = "";

        while(!opt.toLowerCase().equals("voltar")) {

            say(prompt_detail);
            say("  Nome:\t" + user.name());
            say("  ID\t" + user.id());
            say("  Função:\t" + user.role());
            say();

            opt = ask("O que deseja fazer?");

            if (opt.toLowerCase().equals("1")) {
                String name = ask("Mudar nome para?");
                user.setName(name);
                continue;
            }

            if (opt.toLowerCase().equals("2")) {
                String role = ask("Mudar função para?");
                user.setRole(role);
                continue;
            }

            if (opt.toLowerCase().equals("del")) {
                user_base.remove(user);
                say(user.name() + " removido.");
                opt = "voltar";
            }

        }
    }



}