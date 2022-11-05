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
    static String assets_folder = "assets";



    // LAÇO CENTRAL
    public static void main(String[] args)
    {
        // Configura aplicação e carrega bases de dados de usuários, projetos e atividades.
        setup();

        // Laço principal.
        while (!opt.equalsIgnoreCase("sair")) {

            // Prompt inicial
            opt = ask(prompt_init, 0);

            // Chama visão de CRUD de usuários.
            if (opt.equalsIgnoreCase("usuarios")) {
                userViews.list();
                continue;
            }

            // Chama visão de CRUD de projetos.
            if (opt.equalsIgnoreCase("projetos")) {
                projectViews.list();
                continue;
            }

            // Chama visão de CRUD de usuários.
            if (opt.equalsIgnoreCase("atividades")) {
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

        // Detecta e/ou cria diretório de dados.
        File directory = new File(data_folder);
        if (!directory.exists()) directory.mkdirs();

        // Inicia base de usuários
        View.user_base = new Vector<>();

        // Inicia base de projetos
        View.proj_base = new Vector<>();

        // Inicia base de atividades
        View.actv_base = new Vector<>();

        // Configura caminho dos arquivos de base de dados.
        View.setDatapath(data_folder);

        // Carrega bases de dados salvas.
        View.loadEverything();


        // Prompt inicial
        try {
            File file = new File(assets_folder + "/prompt-ini.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine() + "\n";
                prompt_init += data;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo de prompt inicial não encontrado.");
        }


        // Prompt UserListView
        try {
            File file = new File(assets_folder + "/prompt-user-list-view.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine() + "\n";
                prompt_user_list_view += data;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo de prompt da visão de lista de usuários não encontrado.");
        }

        // Prompt UserDetailView
        try {
            File file = new File(assets_folder + "/prompt-user-detail-view.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine() + "\n";
                prompt_user_detail_view += data;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo de prompt da visão de detalhes de usuário não encontrado.");
        }

        // Inicializa visões de usuário
        userViews = new UserViews(prompt_user_list_view, prompt_user_detail_view);


        // Prompt ProjectListView
        try {
            File file = new File(assets_folder + "/prompt-proj-list-view.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine() + "\n";
                prompt_proj_list_view += data;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo de prompt da visão de lista de projeto não encontrado.");
        }

        // Prompt ProjectDetailView
        try {
            File file = new File(assets_folder + "/prompt-proj-detail-view.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine() + "\n";
                prompt_proj_detail_view += data;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo de prompt da visão de detalhes de projeto não encontrado.");
        }

        // Inicializa visões de usuário
        projectViews = new ProjectViews(prompt_proj_list_view, prompt_proj_detail_view);


        // Prompt ActivityListView
        try {
            File file = new File(assets_folder + "/prompt-act-list-view.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine() + "\n";
                prompt_act_list_view += data;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo de prompt da visão de lista de atividades não encontrado.");
        }

        // Prompt ActivityDetailView
        try {
            File file = new File(assets_folder + "/prompt-act-detail-view.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine() + "\n";
                prompt_act_detail_view += data;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo de prompt da visão de detalhes de atividade não encontrado.");
        }

        // Inicializa visões de usuário
        activityViews = new ActivityViews(prompt_act_list_view, prompt_act_detail_view);
    }

}
