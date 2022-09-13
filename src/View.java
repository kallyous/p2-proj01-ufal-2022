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



    // DESVINCULA TUDO PARA APAGAR ENTIDADE
    static void clearBindings(Entity ent) {

        for (Binding b : ent.bindings()) {
            if (b.type() == EntiType.USER)
                getUserByID(b.id()).removeBinding(ent.id());
            else if (b.type() == EntiType.PROJECT)
                getProjectByID(b.id()).removeBinding(ent.id());
            else if (b.type() == EntiType.ACTIVITY)
                getActivityByID(b.id()).removeBinding(ent.id());
            ent.removeBinding(b.id());
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