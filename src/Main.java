import java.io.File;                   // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;              // Import the Scanner class to read text files
import java.util.Vector;               // Lista de tamanho variável



public class Main {

    static String opt = "";
    static String prompt_init = "";

    static UserViews userViews;
    static String prompt_user_list_view = "";
    static String prompt_user_detail_view = "";

    static ProjectViews projectViews;
    static String prompt_proj_list_view = "";
    static String prompt_proj_detail_view = "";

    static ActivityViews activityViews;
    static String prompt_act_list_view = "";
    static String prompt_act_detail_view = "";



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
                userViews.list();
                continue;
            }

            // Chama visão de CRUD de projetos.
            if (opt.toLowerCase().equals("projetos")) {
                projectViews.list();
                continue;
            }

            // Chama visão de CRUD de usuários.
            if (opt.toLowerCase().equals("atividades")) {
                activityViews.list();
                continue;
            }

        }
    }



    // SETUP - Carrega dados e configura coisas
    static void setup() {

        // Inicia base de usuários
        View.user_base = new Vector<User>();

        // Inicia base de projetos
        View.proj_base = new Vector<Project>();

        // Inicia base de atividades
        View.activ_base = new Vector<Activity>();


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

        // Inicializa visões de usuário
        userViews = new UserViews(prompt_user_list_view, prompt_user_detail_view);


        // Prompt ProjectListView
        try {
            File file = new File("assets/prompt-proj-list-view.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine() + "\n";
                prompt_proj_list_view += data;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de prompt da visão de lista de projeto não encontrado.");
            //e.printStackTrace();
        }

        // Prompt ProjectDetailView
        try {
            File file = new File("assets/prompt-proj-detail-view.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine() + "\n";
                prompt_proj_detail_view += data;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de prompt da visão de detalhes de projeto não encontrado.");
            //e.printStackTrace();
        }

        // Inicializa visões de usuário
        projectViews = new ProjectViews(prompt_proj_list_view, prompt_proj_detail_view);


        // Prompt ActivityListView
        try {
            File file = new File("assets/prompt-act-list-view.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine() + "\n";
                prompt_act_list_view += data;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de prompt da visão de lista de atividades não encontrado.");
            //e.printStackTrace();
        }

        // Prompt ActivityDetailView
        try {
            File file = new File("assets/prompt-act-detail-view.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine() + "\n";
                prompt_act_detail_view += data;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de prompt da visão de detalhes de atividade não encontrado.");
            //e.printStackTrace();
        }

        // Inicializa visões de usuário
        activityViews = new ActivityViews(prompt_act_list_view, prompt_act_detail_view);
    }


}
