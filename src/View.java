import java.util.Vector;               // Lista de tamanho variável



public class View {

    static Vector<User> user_base;
    static Vector<Activity> activ_base;
    static Vector<Project> proj_base;

    String prompt_list;
    String prompt_detail;



    public View(Vector<User> ub,  Vector<Activity> ab, Vector<Project> pb, String pl, String pd) {
        user_base = ub;
        activ_base = ab;
        proj_base = pb;
        prompt_list = pl;
        prompt_detail = pd;
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