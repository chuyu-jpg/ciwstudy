package drivers.gson;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {
    public static void main(String[] args) {
        String jsonStr = "{\"k1\":\"v1\"}";
        JsonElement jelement = new JsonParser().parse(jsonStr);
        JsonObject jobject = jelement.getAsJsonObject();


    }
}
