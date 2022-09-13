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



    // (Des)Associa usuário-atividade.
    static boolean bindingUserActivity(long user_id, long activ_id) {

        // Carrega os envolvidos
        Activity activ = getActivityByID(activ_id);
        User user = getUserByID(user_id);

        // Aborta se usuário não existe.
        if (user == null) {
            say("Não existe usuário com ID " + user_id);
            return false; }

        // Aborta se atividade não existe.
        if (activ == null) {
            say("Não existe atividade com ID " + activ_id);
            return false; }

        // Desassocia usuário e atividade se já estão associados.
        if (user.getActivities().contains(activ_id)) {
            user.removeActivity(activ_id);
            activ.removeUser(user.id());
            say("Atividade '" + activ.name() + "' não está mais atribuída a " + user.name() + ".");
        }

        // Associa usuário e atividade se não estavam associados.
        else {
            user.addActivity(activ_id);
            activ.addUser(user.id());
            say("Atividade '" + activ.name() + "' agora está atribuída a " + user.name() + ".");
        }

        return true;
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