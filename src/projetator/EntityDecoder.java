package projetator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class EntityDecoder {

    // Singleton. Default data path. Use setDatapath() para mudar data path.
    private static EntityDecoder instance = new EntityDecoder("data");
    private static String datapath;

    private EntityDecoder(String new_datapath) {
        datapath = new_datapath;
    }

    @SuppressWarnings("unchecked")
    public static JSONObject encode(Entity entity, EntiType type) {
        JSONObject jobj = new JSONObject();

        jobj.put("id", entity.id());
        jobj.put("nome", entity.name());
        jobj.put("type", entity.type().name());

        if (entity.type().name().equals(EntiType.USER.name()))
            encodeUser((User) entity, jobj);

        return jobj;
    }

    public static Entity decode(JSONObject jobj) {

        long id = (long) jobj.get("id");
        EntiType type = EntiType.valueOf(jobj.get("type").toString());

        Entity e;

        if (type == EntiType.USER) {
            e = new User(id);
            ((User) e).setRole(jobj.get("função").toString());
        }

        else if (type == EntiType.PROJECT)
            e = new Entity(id, type);

        else if (type == EntiType.ACTIVITY)
            e = new Entity(id, type);

        else return null;

        e.setName( (String) jobj.get("nome"));

        return e;
    }

    @SuppressWarnings("unchecked")
    private static void encodeUser(User user, JSONObject jobj) {
        jobj.put("função", user.role());
    }

    public static EntityDecoder getInstance() { return instance; }

    public static void setDatapath(String new_datapath) { datapath = new_datapath; }

    public static String getDatapath() { return datapath; }

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
