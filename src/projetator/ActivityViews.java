package projetator;

import static projetator.ConsoleIO.ask;
import static projetator.ConsoleIO.say;


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
            if (opt.toLowerCase().equals("nova")) {
                say("Criando nova atividade...");
                long id = genID();
                String name = ask("Nome da atividade");
                Activity a = new Activity(id);
                a.setName(name);
                actv_base.add(a);
                say(name + " cadastrada com ID " + id);
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
            } catch (NumberFormatException nfe) {
                say("Opção inválida.");
            }

        }

    }



    // DETAIL / ACTIONS  VIEW
    public void detail(Activity activ) {

        String opt = "";

        while(!opt.toLowerCase().equals("voltar")) {

            say(prompt_detail);
            say("  Nome:\t" + activ.name());
            say("  ID\t" + activ.id());
            displayBindings(activ);

            opt = ask("O que deseja fazer?");



            // NOME, TROCAR
            if (opt.toLowerCase().equals("1")) {
                String name = ask("Mudar nome para?");
                activ.setName(name);
                continue;
            }



            // USUÁRIO, DELEGAR ATIVIDADE
            if (opt.toLowerCase().equals("2")) {
                bindingPrompt(activ, EntiType.USER);
                continue; }



            // PROJETO, INSERIR ATIVIDADE
            if (opt.toLowerCase().equals("3")) {
                bindingPrompt(activ, EntiType.PROJECT);
                continue; }



            // EXCLUIR / DELETAR
            if (opt.toLowerCase().equals("del")) {
                delete(activ);
                say(activ.name() + " removido.");
                opt = "voltar";
            }

        }

    }


}