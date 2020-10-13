package com.geekmake.plugin.utils;

import org.apache.commons.lang3.StringEscapeUtils;

import com.google.gson.*;

/**
 * @author pez1420@gmail.com
 * @version $Id: GsonUtil.java v 0.1 2020/10/10 4:45 下午 pez1420 Exp $$
 */
public class GsonUtil {

    public static Gson gson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gson = gsonBuilder.create();
    }

    public static String toPrettyFormat(String json) {
        json = StringEscapeUtils.unescapeJava(json.trim());
        JsonParser jsonParser = new JsonParser();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (json.startsWith("[")) {
            JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();
            json = gson.toJson(jsonArray);
        } else if (json.startsWith("{")) {
            JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
            json = gson.toJson(jsonObject);
        }
        return json;
    }
}
