package projetator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

import static projetator.ConsoleIO.ask;
import static projetator.ConsoleIO.say;



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

    static String data_folder = "data";



    // LAÇO CENTRAL
    public static void main(String[] args)
    {
        // Configura aplicação e carrega bases de dados de usuários, projetos e atividades.
        setup();

        // Laço principal.
        while (!opt.toLowerCase().equals("sair")) {

            // Prompt inicial
            opt = ask(prompt_init, 0);

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

        // Salva dados e relações em JSONs.
        say("Encerrando...");
        View.saveEverything();
    }



    // SETUP - Carrega prompts e configura coisas.
    static void setup() {

        // Testa (de)codificação JSON.
        //EntityDecoder.test();

        // Detecta e/ou cria diretório de dados.
        File directory = new File(data_folder);
        if (!directory.exists()) directory.mkdirs();

        // Inicia base de usuários
        View.user_base = new Vector<Entity>();

        // Inicia base de projetos
        View.proj_base = new Vector<Entity>();

        // Inicia base de atividades
        View.actv_base = new Vector<Entity>();

        // Configura caminho dos arquivos de base de dados.
        View.setDatapath(data_folder);

        // Carrega bases de dados salvas.
        View.loadEverything();


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
