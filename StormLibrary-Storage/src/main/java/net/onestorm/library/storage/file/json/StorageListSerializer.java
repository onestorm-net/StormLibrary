package net.onestorm.library.storage.file.json;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.onestorm.library.storage.StorageList;

import java.lang.reflect.Type;

public class StorageListSerializer implements JsonSerializer<StorageList> {

    @Override
    public JsonElement serialize(StorageList storageList, Type type, JsonSerializationContext context) {
        JsonArray jsonArray = new JsonArray();

        storageList.forEach(value -> {
            if (value instanceof StorageList nextList) {
                jsonArray.add(serialize(nextList, type, context));
            } else {
                jsonArray.add(context.serialize(value));
            }
        });

        return jsonArray;
    }
}
