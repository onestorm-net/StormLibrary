package net.onestorm.library.configuration.file.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.onestorm.library.configuration.file.FileConfiguration;
import net.onestorm.library.configuration.file.MemorySection;

import java.lang.reflect.Type;
import java.util.concurrent.ConcurrentHashMap;

public class JsonConfiguration extends FileConfiguration {

    private static final Type MAP_TYPE = new TypeToken<ConcurrentHashMap<String, Object>>() {}.getType();
    private final Gson gson;

    public JsonConfiguration() {

        GsonBuilder builder = new GsonBuilder();
        builder.disableHtmlEscaping();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(MAP_TYPE, new SectionDeserializer(this));
        builder.registerTypeAdapter(MemorySection.class, new SectionSerializer());

        this.gson = builder.create();
    }

    @Override
    protected void loadFromString(String data) {
        map = gson.fromJson(data, MAP_TYPE);
    }

    @Override
    protected String saveToString() {
        return gson.toJson(map, MAP_TYPE);
    }


}
