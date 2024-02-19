package net.onestorm.library.configuration.file.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import net.onestorm.library.configuration.Section;
import net.onestorm.library.configuration.file.MemorySection;

import java.lang.reflect.Type;
import java.util.concurrent.ConcurrentHashMap;

public class SectionDeserializer implements JsonDeserializer<ConcurrentHashMap<String, Object>> {

    private final Section root;

    public SectionDeserializer(Section root) {
        this.root = root;
    }

    @Override
    public ConcurrentHashMap<String, Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        JsonObject jsonObject = json.getAsJsonObject();
        jsonObject.asMap().forEach((key, element) -> {
            if (element instanceof JsonObject object) {
                Section section = new MemorySection(root);
                createChildren(section, object, context);
                map.put(key, section);
            } else if (element instanceof JsonArray array) {
                Section section = new MemorySection(root);
                createChildren(section, array, context);
                map.put(key, section);
            } else {
                Object value = context.deserialize(element, Object.class);
                map.put(key, value);
            }
        });

        return map;
    }

    public void createChildren(Section parent, JsonObject object, JsonDeserializationContext context) {
        object.asMap().forEach((key, element) -> createChild(parent, key, element, context));
    }

    public void createChildren(Section parent, JsonArray array, JsonDeserializationContext context) {
        for (int index = 0; index < array.size(); index++) {
            String key = String.valueOf(index);
            JsonElement element = array.get(index);
            createChild(parent, key, element, context);
        }
    }

    public void createChild(Section parent, String key, JsonElement element, JsonDeserializationContext context) {
        if (element instanceof JsonObject object) {
            Section section = parent.createSection(key);
            createChildren(section, object, context);
        } else if (element instanceof JsonArray array) {
            Section section = parent.createSection(key);
            createChildren(section, array, context);
        } else {
            Object value = context.deserialize(element, Object.class);
            parent.set(key, value);
        }
    }
}
