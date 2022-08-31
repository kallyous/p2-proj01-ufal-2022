import java.io.File;                   // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;              // Import the Scanner class to read text files

public class Main {

    static String prompt_init = "";
    static String prompt_userlistview = "";
    static String opt = "";

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

//         User u = new User(uname, 1);
//         System.out.println("O nome é " + u.name);
    }


    // Visão de usuários
    static void userListView() {
        System.out.println("User List View TODO");
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
}
