package projetator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Scanner;

import static projetator.ConsoleIO.datetime_formatter;

public class EntityDecoder {

    public static Entity decode(JSONObject jobj) {

        long id = (long) jobj.get("id");
        EntiType type = EntiType.valueOf(jobj.get("type").toString());

        Entity e;

        if (type == EntiType.USER) {
            e = new User(id);
            e.setName( (String) jobj.get("nome"));
            ((User) e).setRole(jobj.get("função").toString());
        }

        else if (type == EntiType.PROJECT) {
            e = new Project(id);
            decodeProject((Project) e, jobj);
        }

        else if (type == EntiType.ACTIVITY) {
            e = new Activity(id);
            e.setName( (String) jobj.get("nome"));
        }

        else return null;

        decodeBindings(e, jobj);

        return e;
    }


    private static void decodeBindings(Entity e, JSONObject jo) {
        JSONArray ja;
        String id_str;

        try {
            ja = (JSONArray) jo.get("relações");
        }
        catch (NullPointerException ex) { return; }

        if (ja.size() < 1) return;

        for (Object o : ja) {
            JSONObject b = (JSONObject) o;
            id_str = b.keySet().stream().findFirst().get().toString();
            e.addBinding( Long.parseLong(id_str), EntiType.valueOf(b.get(id_str).toString()) );
        }
    }


    private static void decodeProject(Project p, JSONObject jo) {
        String value;
        LocalDateTime date_time;

        p.setName( (String) jo.get("nome"));
        p.setCoordinator( (long) jo.get("coordenador_id") );
        p.setDescription( (String) jo.get("descrição"));

        value = (String) jo.get("projeto_início");
        date_time = LocalDateTime.parse(value, datetime_formatter);
        p.setStartTime(date_time);

        value = (String) jo.get("projeto_fim");
        date_time = LocalDateTime.parse(value, datetime_formatter);
        p.setEndTime(date_time);

        value = (String) jo.get("vigencia_bolsa_início");
        date_time = LocalDateTime.parse(value, datetime_formatter);
        p.setPayStartTime(date_time);

        value = (String) jo.get("vigencia_bolsa_fim");
        date_time = LocalDateTime.parse(value, datetime_formatter);
        p.setPayEndTime(date_time);

    }


    @SuppressWarnings("unchecked")
    public static void test() {
        String filepah = "data/test.json";
        JSONObject jobj = new JSONObject();

        jobj.put("nome", "João das Neves");
        jobj.put("idade", 37);
        jobj.put("peso", 78.27);
        jobj.put("vivo", true);

        JSONArray jarr = new JSONArray();
        jarr.add("André");
        jarr.add("Thiago");
        jarr.add("Júlia");

        jobj.put("mortes", jarr);

        File fobj = new File(filepah); // Não joga exeção.

        // Criação de arquivo e escrita de string JSON.
        try {
            // Cria arquivo se não existe ainda.
            if (fobj.createNewFile())
                System.out.println("Arquivo criado: " + fobj.getName());
            else
                System.out.println("Arquivo já existente: " + filepah);

            // Escreve string JSON no arquivo.
            FileWriter wobj = new FileWriter(filepah);
            wobj.write(jobj.toJSONString());
            wobj.close();
        }
        catch (IOException e) {
            System.out.println("Erro ao criar/escrever arquivo: " + filepah);
            e.printStackTrace();
        }

        String data = "";
        JSONObject rjboj;
        JSONParser jparser = new JSONParser();
        // Abertura e leitura de arquivo.
        try {
            Scanner sobj = new Scanner(fobj);
            System.out.println("Lendo arquivo: " + filepah);
            while (sobj.hasNextLine())
                data += sobj.nextLine();
            sobj.close();
            rjboj = (JSONObject) jparser.parse(data);
            //System.out.println(rjboj);
            System.out.println("Nome: " + rjboj.get("nome"));
            System.out.println("Idade: " + rjboj.get("idade"));
        }
        catch (FileNotFoundException | ParseException e) {
//        catch (Exception e) {
            System.out.println("Erro ao abrir/ler arquivo: " + filepah);
            e.printStackTrace();
        }
    }

}
