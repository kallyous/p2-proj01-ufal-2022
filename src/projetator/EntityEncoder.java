package projetator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import static projetator.ConsoleIO.datetime_formatter;
import static projetator.ConsoleIO.say;

public class EntityEncoder {

    @SuppressWarnings("unchecked")
    public static JSONObject encode(Entity entity) {
        JSONObject jobj = new JSONObject();

        jobj.put("id", entity.id());
        jobj.put("nome", entity.name());
        jobj.put("type", entity.type().name());

        encodeBindings(entity, jobj);

        if (entity.type().name().equals(EntiType.USER.name()))
            encodeUser((User) entity, jobj);
        else if (entity.type().name().equals(EntiType.PROJECT.name()))
            encodeProject((Project) entity, jobj);
        else if (entity.type().name().equals(EntiType.ACTIVITY.name()))
            encodeActivity((Activity) entity, jobj);
        else
            say("Entidade " + entity.id() + ", de nome " + entity.name() + ", está com tipo indefinido.");

        return jobj;
    }

    @SuppressWarnings("unchecked")
    private static void encodeUser(User user, JSONObject jobj) {
        jobj.put("função", user.role());
    }

    @SuppressWarnings("unchecked")
    private static void encodeProject(Project p, JSONObject jo) {
        jo.put("coordenador_id", p.coordinator());
        jo.put("descrição", p.description());
        jo.put("projeto_início", p.startTime().format(datetime_formatter));
        jo.put("projeto_fim", p.endTime().format(datetime_formatter));
        jo.put("vigencia_bolsa_início", p.payStartTime().format(datetime_formatter));
        jo.put("vigencia_bolsa_fim", p.payEndTime().format(datetime_formatter));
    }

    @SuppressWarnings("unchecked")
    private static void encodeActivity(Activity actv, JSONObject jobj) {
        jobj.put("descrição", actv.description());
    }

    @SuppressWarnings("unchecked")
    private static void encodeBindings(Entity e, JSONObject jo) {
        JSONArray ja = new JSONArray();
        JSONObject pair;

        for (Binding b : e.bindings()) {
            pair = new JSONObject();
            pair.put(b.id(), b.type().name());
            ja.add(pair);
        }

        jo.put("relações", ja);
    }
}
