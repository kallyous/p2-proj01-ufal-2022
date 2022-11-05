package projetator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import static projetator.ConsoleIO.ask;
import static projetator.ConsoleIO.say;

public class View {

    public static Vector<Entity> user_base;
    public static Vector<Entity> actv_base;
    public static Vector<Entity> proj_base;

    private static String user_base_file_path;
    private static String actv_base_file_path;
    private static String proj_base_file_path;

    String prompt_list;
    String prompt_detail;



    public View(String prompt_list, String prompt_detail) {
        this.prompt_list = prompt_list;
        this.prompt_detail = prompt_detail;
    }



    // CRIA E DESTÓI VÍNCULOS ENTRE ENTIDADES
    static void bindingPrompt(Entity source_entity, EntiType target_type) {

        if (source_entity.type() == target_type) {
            say("ERRO: Entidades de mesmo tipo não são associáveis.");
            return; }

        displayEntities(target_type);

        String id_str = ask("\nID para vincular ou desvincular:");
        long id = Long.parseLong(id_str);
        Entity target_entity = getEntityByID(id);

        // Se a entidade alvo foi encontrada.
        if (target_entity != null) {

            // Se não há vínculo, crie.
            if (source_entity.getBinding(id) == null)
                bindEntitiesObj(source_entity, target_entity);

            // Se há vínculo, desfaça.
            else unbindEntitiesObj(source_entity, target_entity);

        // Se entidade não existe, avise.
        } else say("ID " + id + " não encontrado.");

    }



    // EXIBE VÍNCULOS
    static void displayBindings(Entity ent) {

        Vector<User> users = new Vector<>();
        Vector<Project> projs = new Vector<>();
        Vector<Activity> activs = new Vector<>();

        // Coleta todos os bindings da antidade e separa por tipo.
        try {
            for (Binding b : ent.bindings()) {
                Entity e = getEntityByID(b.id());
                if (e.type() == EntiType.USER) users.add( (User) e);
                else if (e.type() == EntiType.PROJECT) projs.add( (Project) e);
                else if (e.type() == EntiType.ACTIVITY) activs.add( (Activity) e);
                else say("Binding desconhecido com " + b.id() + ", ignorando-o.");
            }
        }
        catch (NullPointerException e) {
            say("EXCEÇÃO: NullPointerException no local esperado.");
            return;
        }


        // Exibe pessoas vinculadas.
        if (ent.type() != EntiType.USER) {
            say("  Pessoas vinculadas:");
            for (User u : users)
                say("    ID " + u.id() + " - " + u.name() + ", " + u.role().toString().toLowerCase());
        }

        // Exibe projetos vinculados.
        if (ent.type() != EntiType.PROJECT) {
            if (ent.type() == EntiType.USER) {
                say("  Participa no projetos:", 0);
                for (Project p : projs) say("    ID " + p.id() + " - " + p.name());
            }
            else {
                say("  Pertence ao projeto:");
                for (Project p : projs) say("    ID " + p.id() + " - " + p.name());
                say();
            }
        }

        // Exibe atividades vinculadas.
        if (ent.type() != EntiType.ACTIVITY) {
            say("  Atividades:");
            for (Activity a : activs) say("    ID " + a.id() + " - " + a.name());
        }

    }



    // EXIBE ENTIDADES DA CATEGORIA ESCOLHIDA
    static void displayEntities(EntiType type) {

        if (type == EntiType.USER) {
            say("\nUsuários disponíveis:");
            for (Entity e : user_base) say("  ID " + e.id() + " - " + e.name()); }

        else if (type == EntiType.PROJECT) {
            say("\nProjetos disponíveis:");
            for (Entity e : proj_base)
                say("  ID " + e.id() + " - " + e.name()); }

        else if (type == EntiType.ACTIVITY) {
            say("\nAtividades disponíveis:");
            for (Entity e : actv_base)
                say("  ID " + e.id() + " - " + e.name()); }

    }



    // ASSOCIA ENTIDADES
    static boolean bindEntitiesObj(Entity a, Entity b) {

        if (a.type() == b.type()) {
            say("ERRO: Entidades de mesmo tipo não podem ser vinculadas.");
            return false; }

        if ( !a.addBinding(b.id(), b.type()) ) {
            say("ERRO: Falha ao criar vínculo de " + a.name() + " para " + b.name());
            return false; }

        if ( !b.addBinding(a.id(), a.type()) ) {
            say("ERRO: Falha ao criar vínculo de " + b.name() + " para " + a.name());
            a.removeBinding(b.id());
            return false; }

        say("Vínculo de " + a.name() + " com " + b.name() + " criado com sucesso.");
        return true;

    }



    // DESASSOCIA ENTIDADES
    static boolean unbindEntitiesObj(Entity a, Entity b) {
        a.removeBinding(b.id());
        b.removeBinding(a.id());
        return true;
    }



    // REMOVE ENTIDADE
    static void delete(Entity ent) {

        clearBindings(ent);

        if (user_base.contains(ent)) {
            user_base.remove(ent);
            return; }

        if (proj_base.contains(ent)) {
            proj_base.remove(ent);
            return; }

        if (actv_base.contains(ent)) {
            actv_base.remove(ent);
            return; }
    }



    // DESVINCULA TUDO PARA APAGAR ENTIDADE
    static void clearBindings(Entity ent) {

        Vector<Long> bind_ids = new Vector<Long>();

        for (Binding b : ent.bindings()) {
            Entity e = getEntityByID(b.id());
            e.removeBinding(ent.id());
            bind_ids.add(e.id()); }

        for (long id : bind_ids) ent.removeBinding(id);

    }



    // Acha usuário pelo ID
    static User getUserByID(long id) {
        for (Entity e : user_base)
            if (e.id() == id) return (User) e;
        return null;
    }



    // Acha projeto pelo ID
    static Project getProjectByID(long id) {
        for (Entity e : proj_base)
            if (e.id() == id) return (Project) e;
        return null;
    }



    // Acha atividade pelo ID
    static Activity getActivityByID(long id) {
        for (Entity e : actv_base)
            if (e.id() == id) return (Activity) e;
        return null;
    }



    // Acha entidade pelo ID
    static Entity getEntityByID(long id) {
        Entity e = getUserByID(id);
        if (e != null) return e;
        e = getProjectByID(id);
        if (e != null) return e;
        e = getActivityByID(id);
        return e;
    }



    // IDs de usário.
    static Vector<Long> allUserIDs() {
        Vector<Long> ids = new Vector<Long>();
        for (Entity e : user_base) ids.add(e.id());
        return ids;
    }



    // IDs de projeto.
    static Vector<Long> allProjectIDs() {
        Vector<Long> ids = new Vector<Long>();
        for (Entity e : proj_base) ids.add(e.id());
        return ids;
    }



    // IDs de atividade.
    static Vector<Long> allActivityIDs() {
        Vector<Long> ids = new Vector<Long>();
        for (Entity e : actv_base) ids.add(e.id());
        return ids;
    }



    // Gerador de IDs
    static long genID() {

        long new_id;
        Vector<Long> used_ids = new Vector<Long>();

        used_ids.addAll(allUserIDs());
        used_ids.addAll(allProjectIDs());
        used_ids.addAll(allActivityIDs());

        do new_id = (long) (Math.random() * 1000000L);
        while (used_ids.contains(new_id));

        return new_id;
    }



    // Define caminhos dos arquivos das bases de dados.
    static void setDatapath(String data_folder) {
        user_base_file_path = data_folder + "/user_base.json";
        actv_base_file_path = data_folder + "/activity_base.json";
        proj_base_file_path = data_folder + "/project_base.json";
    }



    // Sava dados.
    static void saveEverything() {
        dumpEntities(user_base, user_base_file_path);
        dumpEntities(actv_base, actv_base_file_path);
        dumpEntities(proj_base, proj_base_file_path);
    }



    // Carrega dados.
    public static void loadEverything() {
        loadEntities(user_base, user_base_file_path);
        loadEntities(actv_base, actv_base_file_path);
        loadEntities(proj_base, proj_base_file_path);
    }



    // Descarrega entidades.
    @SuppressWarnings("unchecked")
    static void dumpEntities(Vector<Entity> base, String data_file_path) {

        JSONArray jarr = new JSONArray();          // Array JSON para salvar codificar a base.
        File fobj = new File(data_file_path);  // Para criar novo arquivo caso não exista.

        // Lê e codifica todas as entidades da base em JSON no array JSON.
        for (Entity e : base) {
            JSONObject jobj = EntityEncoder.encode(e);
            jarr.add(jobj);
        }

        // Salva a string JSON com toda a base no arquivo JSON especificado em data_file_path.
        try {
            fobj.createNewFile();                          // Cria arquivo se não existe ainda.
            FileWriter wobj = new FileWriter(data_file_path);  // Abre arquivo em modo de escrita.
            wobj.write(jarr.toJSONString());                   // Escreve string JSON no arquivo.
            wobj.close();                                      // Fecha arquivo.
        }
        catch (IOException e) {
            System.out.println("Erro ao criar/escrever arquivo: " + data_file_path);
            e.printStackTrace();
        }

    }



    // Carrega entidades
    static void loadEntities(Vector<Entity> base, String data_file_path) {

        JSONParser jparser = new JSONParser();
        JSONArray jarr;
        File fobj = new File(data_file_path);
        String data = "";
        Entity e;

        try {
            Scanner sobj = new Scanner(fobj);
            while (sobj.hasNextLine())
                data += sobj.nextLine();
            sobj.close();
            jarr = (JSONArray) jparser.parse(data);
            for (Object o : jarr) {
                e = EntityDecoder.decode((JSONObject) o);
                if (e != null) base.add(e);
                else say("Erro ao interpretar " + o.toString() + ". Objeto ignorado.");
            }
        }
        catch (FileNotFoundException ex) {
            say("Arquivo " + data_file_path + " não existente. Iniciando com base limpa.");
        }
        catch (ParseException ex) {
            say("Erro de interpretação de JSON com JSONParser().");
            throw new RuntimeException(ex);
        }

    }

}