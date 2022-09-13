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



    // Gerador de IDs
    static long genID() {
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



    // Atalho para escrever linha vazia na tela.
    static void say() {
        System.out.println();
    }



    // Atalho para escrever tralha na tela sem newline ao final.
    static void say_(String text) {
        System.out.print(text);
    }

}