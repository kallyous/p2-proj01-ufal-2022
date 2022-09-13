import java.util.Vector;               // Lista de tamanho variável



public class ActivityViews extends View {



    public ActivityViews(String pl, String pd) {
        super(pl, pd);
    }



    // ACTIVITY LIST VIEW
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
                Activity a = new Activity(id, name);
                activ_base.add(a);
                say(name + " cadastrada com ID " + id);
                continue;
            }

            // Rotina de listagem das atividades.
            if (opt.toLowerCase().equals("listar")) {
                say("\nID \t\t\tNome");
                say("--------------------------------------------------------------------------------");
                for (Activity a : activ_base) say(a.id() + "\t\t\t" + a.name());
                continue;
            }

            // Detalhes de uma atividade específica
            try {
                long id = Long.parseLong(opt);
                boolean match = false;
                for (Activity a : activ_base) {
                    if (a.id() == id) {
                        match = true;
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



    // USER DETAIL VIEW
    public void detail(Activity activ) {
        String opt = "";

        while(!opt.toLowerCase().equals("voltar")) {

            say(prompt_detail);
            say("  Nome:\t" + activ.name());
            say("  ID\t" + activ.id());
            say();

            opt = ask("O que deseja fazer?");

            if (opt.toLowerCase().equals("1")) {
                String name = ask("Mudar nome para?");
                activ.setName(name);
                continue;
            }

            if (opt.toLowerCase().equals("del")) {
                activ_base.remove(activ);
                say(activ.name() + " removida.");
                opt = "voltar";
            }

        }
    }



}