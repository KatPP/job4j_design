package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import netscape.javascript.JSObject;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject("{\"name\":\"Alex\"}");
        List<String> list = new ArrayList<>();
        list.add("Alex");
        list.add("Lila");
        JSONArray jsonArray = new JSONArray(list);
        JSONObject object = new JSONObject();
        final Kitty son = new Kitty("Nick", 1);
        final Cat cat = new Cat("Bob", 8, true, new String[]{son.getName()}, son);
        object.put("name", cat.getName());
        object.put("age", cat.getAge());
        object.put("hasChild", cat.isHasChild());
        object.put("childNames", cat.getChildNames());
        object.put("son", cat.getSon());
        System.out.println(object);
        /*
        final Kitty son = new Kitty("Nick", 1);
        final Cat cat = new Cat("Bob", 8, true, new String[]{son.getName()}, son);
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(cat));
        final String mod = "{"
                + "\"name\":\"Jack\","
                + "\"age\":12,"
                + "\"hasChild\":true,"
                + "\"childNames\":[\"Tim\"],"
                + "\"son\":"
                + "{"
                + "\"name\":\"Tim\","
                + "\"age\":3"
                + "}"
                + '}';
        final Cat modCat = gson.fromJson(mod, Cat.class);
        System.out.println(modCat);
         */
    }
}
