package net.onestorm.library.configuration.file.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.onestorm.library.configuration.file.MemorySection;

import java.lang.reflect.Type;

public class SectionSerializer implements JsonSerializer<MemorySection> {

    @Override
    public JsonElement serialize(MemorySection section, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        section.asMap().forEach((key, value) -> {
            if (value instanceof MemorySection nextSection) {
                jsonObject.add(key, serialize(nextSection, type, context));
            } else {
                jsonObject.add(key, context.serialize(value));
            }
        });

        return jsonObject;
    }
}
