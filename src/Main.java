import java.io.File;                   // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;              // Import the Scanner class to read text files
import java.util.Vector;               // Lista de tamanho variável

public class Main {

    static String opt = "";
    static String prompt_init = "";

    static String prompt_userlistview = "";
    static Vector<User> user_base;

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


    // Visão de usuários
    static void userListView() {
        opt = "";
        while (!opt.equals("voltar")) {
            System.out.print(prompt_userlistview);
            opt = System.console().readLine();

            // Rotina de adição de novo usuário.
            if (opt.equals("novo")) {
                say("Criando novo usuário...");
                String name = ask("Nome completo da pessoa");
                String role = ask("Função");
                user_base.add(new User(name, role));
                continue;
            }

            // Rotina de listagem dos usuários.
            if (opt.equals("listar")) {
                say();
                for (User u : user_base) say(u.id() + "  " + u.name() + "  " + u.role());
                continue;
            }
        }
    }



    // Visão de usuários
    static void projectListView() {
        System.out.println("Project List View TODO");
    }



    // Visão de usuários
    static void activityListView() {
        System.out.println("Activity List View TODO");
    }


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
                prompt_userlistview += data;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de prompt da visão de lista de usuários não encontrado.");
            //e.printStackTrace();
        }
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
