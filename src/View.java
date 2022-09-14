import java.util.Vector;



public class View {

    public static Vector<User> user_base;
    public static Vector<Activity> activ_base;
    public static Vector<Project> proj_base;

    String prompt_list;
    String prompt_detail;



    public View(String pl, String pd) {
        prompt_list = pl;
        prompt_detail = pd;
    }



    // CADASTRAR USUARIO EM PROJETO
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

        Vector<User> users = new Vector<User>();
        Vector<Project> projs = new Vector<Project>();
        Vector<Activity> activs = new Vector<Activity>();

        try {
            for (Binding b : ent.bindings()) {
                Entity e = getEntityByID(b.id());
                if (e.type() == EntiType.USER) users.add( (User) e);
                else if (e.type() == EntiType.PROJECT) projs.add( (Project) e);
                else activs.add( (Activity) e);
            }
        }
        catch (NullPointerException e) {
            say("EXCEÇÃO: NullPointerException no local esperado.");
            return;
        }



        // Exibe pessoas vinculadas.
        if (ent.type() != EntiType.USER) {
            say("  Pessoas vinculadas:");
            for (User u : users) say("    ID " + u.id() + " - " + u.name() + ", " + u.role());
        }

        // Exibe projetos vinculados.
        if (ent.type() != EntiType.PROJECT) {
            if (ent.type() == EntiType.USER) {
                say("  Participa no projetos:");
                for (Project p : projs) say("    ID " + p.id() + " - " + p.name());
            }
            else {
                say_("  Pertence ao projeto:");
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
            for (Entity e : activ_base)
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

        if (activ_base.contains(ent)) {
            activ_base.remove(ent);
            return; }
    }



    // DESVINCULA TUDO PARA APAGAR ENTIDADE
    static void clearBindings(Entity ent) {

        for (Binding b : ent.bindings()) {
            Entity e = getEntityByID(b.id());
            e.removeBinding(ent.id());
        }

    }



    // Acha usuário pelo ID
    static User getUserByID(long id) {
        for (User u : user_base)
            if (u.id() == id) return u;
            return null;
    }



    // Acha projeto pelo ID
    static Project getProjectByID(long id) {
        for (Project p : proj_base)
            if (p.id() == id) return p;
            return null;
    }



    // Acha atividade pelo ID
    static Activity getActivityByID(long id) {
        for (Activity a : activ_base)
            if (a.id() == id) return a;
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
        for (User u : user_base) ids.add(u.id());
        return ids;
    }



    // IDs de projeto.
    static Vector<Long> allProjectIDs() {
        Vector<Long> ids = new Vector<Long>();
        for (Project p : proj_base) ids.add(p.id());
        return ids;
    }



    // IDs de atividade.
    static Vector<Long> allActivityIDs() {
        Vector<Long> ids = new Vector<Long>();
        for (Activity a : activ_base) ids.add(a.id());
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



    // Atalho para escrever linha vazia na tela.
    static void say() {
        System.out.println();
    }



    // Atalho para escrever tralha na tela sem newline ao final.
    static void say_(String text) {
        System.out.print(text);
    }


}