package projetator;

import java.time.format.DateTimeFormatter;

public class ConsoleIO {

    static DateTimeFormatter datetime_formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    // Atalho para promts
    static String ask(String prompt) {
        say(prompt);
        String answer = System.console().readLine();
        return answer;
    }

    static String ask(String prompt, int newlines) {
        say(prompt, newlines);
        String answer = System.console().readLine();
        return answer;
    }

    static String ask() {
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

    // Atalho para escrever tralha na tela, sem ou com quantidade arbitrária de newlines ao final do texto.
    static void say(String text, int newlines) {
        System.out.print(text);
        for (int i=0; i < newlines; i++)
            System.out.println();
    }
}
