import java.io.File;                   // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;              // Import the Scanner class to read text files
import java.util.Vector;               // Lista de tamanho variável

public class Main {

    static String opt = "";
    static String prompt_init = "";

    static String prompt_user_list_view = "";
    static String prompt_user_detail_view = "";
    static Vector<User> user_base;



    // LAÇO CENTRAL
    public static void main(String[] args)
    {
        // Carrega informações iniciais.
        setup();

        // Laço principal.
        while (!opt.toLowerCase().equals("sair")) {
            // Prompt inicial
            System.out.print(prompt_init);
            opt = System.console().readLine();

            // Chama visão de CRUD de usuários.
            if (opt.toLowerCase().equals("usuarios")) {
                userListView();
                continue;
            }

            // Chama visão de CRUD de projetos.
            if (opt.toLowerCase().equals("projetos")) {
                projectListView();
                continue;
            }

            // Chama visão de CRUD de usuários.
            if (opt.toLowerCase().equals("atividades")) {
                activityListView();
                continue;
            }

        }
    }


    // USER LIST VIEW
    static void userListView() {
        opt = "";

        while (!opt.toLowerCase().equals("voltar")) {
            System.out.print(prompt_user_list_view);
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
                        userDetailView(u);
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
    static void userDetailView(User user) {
        opt = "";

        while(!opt.toLowerCase().equals("voltar")) {

            say(prompt_user_detail_view);
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

        opt = "";
    }



    // PROJECT LIST VIEW
    static void projectListView() {
        System.out.println("Project List View TODO");
    }



    // ACTIVITY LIST VIEW
    static void activityListView() {
        System.out.println("Activity List View TODO");
    }



    // SETUP - Carrega dados e configura coisas
    static void setup() {

        // Inicia base de usuários
        user_base = new Vector<User>();

        // Prompt inicial
        try {
            File file = new File("assets/prompt-ini.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine() + "\n";
                prompt_init += data;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de prompt inicial não encontrado.");
            //e.printStackTrace();
        }

        // Prompt UserListView
        try {
            File file = new File("assets/prompt-user-list-view.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine() + "\n";
                prompt_user_list_view += data;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de prompt da visão de lista de usuários não encontrado.");
            //e.printStackTrace();
        }

        // Prompt UserDetailView
        try {
            File file = new File("assets/prompt-user-detail-view.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine() + "\n";
                prompt_user_detail_view += data;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de prompt da visão de detalhes de usuário não encontrado.");
            //e.printStackTrace();
        }
    }



    // ID GENERATOR
    public static long genID() {
        return (long) (Math.random() * 1000000L);
    }



    // Atalho para promts
    static String ask(String prompt) {
        System.out.println(prompt);
        String answer = System.console().readLine();
        return answer;
    }



    // Atalho para escrever tralha na tela.
    static void say(String text) {
        System.out.println(text);
    }



    // Atalho para escrever tralha na tela.
    static void say() {
        System.out.println();
    }



    // Atalho para escrever tralha na tela sem newline ao final.
    static void say_(String text) {
        System.out.print(text);
    }

}
