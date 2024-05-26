package net.onestorm.library.storage.file.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import net.onestorm.library.storage.MemoryStorageList;
import net.onestorm.library.storage.MemoryStorageMap;
import net.onestorm.library.storage.StorageElement;
import net.onestorm.library.storage.StorageList;
import net.onestorm.library.storage.StorageMap;
import net.onestorm.library.storage.file.AbstractFileStorage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JsonStorage extends AbstractFileStorage {
    private final Gson gson;

    public JsonStorage() {

        GsonBuilder builder = new GsonBuilder();
        builder.disableHtmlEscaping();
        builder.setPrettyPrinting();
        builder.registerTypeHierarchyAdapter(StorageMap.class, new StorageMapSerializer());
        builder.registerTypeHierarchyAdapter(StorageList.class, new StorageListSerializer());

        this.gson = builder.create();
    }

    @Override
    protected void loadFromString(String data) {
        Map<String, Object> result = new ConcurrentHashMap<>();

        JsonObject jsonObject = gson.fromJson(data, JsonObject.class);

        if (jsonObject == null) {
            throw new NullPointerException("Json (String) is null or empty");
        }

        jsonObject.asMap().forEach((key, value) -> {
            if (value instanceof JsonObject nextObject) {
                result.put(key, createStorageMap(nextObject, this));
            } else if (value instanceof JsonArray nextArray) {
                result.put(key, createStorageList(nextArray, this));
            } else if (value instanceof JsonPrimitive nextPrimitive) {
                Object primitive = createPrimitive(nextPrimitive);
                if (primitive == null) {
                    return; // continue
                }
                result.put(key, primitive);
            }
        });

        map = result;
    }

    /**
     * Create a new StorageMap, a internal method used for deserializing json to a JsonStorage
     * @param jsonObject the json object used to create the StorageMap
     * @param parent the parent of StorageMap
     * @return the created StorageMap
     */
    private StorageMap createStorageMap(JsonObject jsonObject, StorageElement parent) {
        StorageMap result = new MemoryStorageMap(parent);
        jsonObject.asMap().forEach((key, jsonElement) -> {
            if (jsonElement instanceof JsonObject nextObject) {
                result.set(key, createStorageMap(nextObject, result));
            } else if (jsonElement instanceof JsonArray nextArray) {
                result.set(key, createStorageList(nextArray, result));
            } else if (jsonElement instanceof JsonPrimitive nextPrimitive) {
                Object value = createPrimitive(nextPrimitive);
                if (value == null) {
                    return; // continue
                }
                result.set(key, value, true);
            }
        });
        return result;
    }

    /**
     * Create a new StorageList, a internal method used for deserializing json to a JsonStorage
     * @param jsonArray the json array used to create the StorageList
     * @param parent the parent of StorageList
     * @return the created StorageList
     */
    private StorageList createStorageList(JsonArray jsonArray, StorageElement parent) {
        StorageList result = new MemoryStorageList(parent);
        jsonArray.forEach(value -> {
            if (value instanceof JsonObject nextObject) {
                result.add(createStorageMap(nextObject, result));
            } else if (value instanceof JsonArray nextArray) {
                result.add(createStorageList(nextArray, result));
            } else if (value instanceof JsonPrimitive nextPrimitive) {
                Object primitive = createPrimitive(nextPrimitive);
                if (primitive == null) {
                    return; // continue
                }
                result.add(primitive);
            }
        });
        return result;
    }

    /**
     * Gets the value from a JsonPrimitive
     * @param jsonPrimitive the json element to get the value from
     * @return the value, or null if null in json
     */
    private Object createPrimitive(JsonPrimitive jsonPrimitive) {
        if (jsonPrimitive.isString()) {
            return jsonPrimitive.getAsString();
        } else if (jsonPrimitive.isNumber()) {
            return jsonPrimitive.getAsNumber();
        } else if (jsonPrimitive.isBoolean()) {
            return jsonPrimitive.getAsBoolean();
        } else {
            return null;
        }
    }

    @Override
    protected String saveToString() {
        return gson.toJson(this);
    }
}
