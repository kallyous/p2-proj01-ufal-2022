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



            // INFO PROJETOS E ATIVIDADES VINCULADAS
            displayBindings(user);



            // ENTRADA
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



            // PROJETO, CADASTRAR EM
            if (opt.toLowerCase().equals("3")) {
                bindingPrompt(user, EntiType.PROJECT);
                continue; }



            // ATIVIDADE, ATRIBUIR
            if (opt.toLowerCase().equals("4")) {
                bindingPrompt(user, EntiType.ACTIVITY);
                continue; }



            // EXCLUIR / DELETAR
            if (opt.toLowerCase().equals("del")) {
                delete(user);
                say(user.name() + " removido(a).");
                opt = "voltar";
            }

        }

    }


}