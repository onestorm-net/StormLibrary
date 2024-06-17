package net.onestorm.library.storage.file.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.onestorm.library.storage.StorageMap;

import java.lang.reflect.Type;

public class StorageMapSerializer implements JsonSerializer<StorageMap> {

    @Override
    public JsonElement serialize(StorageMap storageMap, Type type, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();

        storageMap.forEach((key, value) -> {
            if (value instanceof StorageMap nextMap) {
                jsonObject.add(key, serialize(nextMap, type, context));
            } else {
                jsonObject.add(key, context.serialize(value));
            }
        });

        return jsonObject;
    }
}
