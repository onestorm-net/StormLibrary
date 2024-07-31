package net.onestorm.library.storage;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

public class MemoryStorageMap implements StorageMap {

    private static final boolean OVERWRITE_SET_DEFAULT = true;
    private static final boolean OVERWRITE_SET_MAP_DEFAULT = true;
    private static final boolean OVERWRITE_SET_LIST_DEFAULT = true;

    protected Map<String, Object> map = new ConcurrentHashMap<>();
    private final Storage root;
    private final StorageElement parent;

    public MemoryStorageMap() {
        if (!(this instanceof Storage)) {
            throw new IllegalStateException("Not a root storage object");
        }

        this.root = (Storage) this;
        this.parent = null;
    }

    public MemoryStorageMap(StorageElement parent) {
        this.root = parent.getRoot();
        this.parent = parent;
    }

    @Override
    public Storage getRoot() {
        return root;
    }

    @Override
    public StorageElement getParent() {
        return parent;
    }

    @Override
    public Optional<StorageMap> getMap(String path) {
        Object value = get(path);
        if (value instanceof StorageMap storageMap) {
            return Optional.of(storageMap);
        }
        return Optional.empty();
    }

    @Override
    public StorageMap setMap(String path) {
        return setMap(path, OVERWRITE_SET_MAP_DEFAULT);
    }

    @Override
    public StorageMap setMap(String path, boolean overwrite) {
        int leadingIndex = -1;
        int trailingIndex;

        StorageMap storageMap = this;
        char separator = root.getPathSeparator();
        while ((leadingIndex = path.indexOf(separator, trailingIndex = leadingIndex + 1)) != -1) {
            String node = path.substring(trailingIndex, leadingIndex);
            Optional<StorageMap> optionalStorageMap = storageMap.getMap(node);
            if (optionalStorageMap.isEmpty()) {
                storageMap = storageMap.setMap(node); // create missing
            } else {
                storageMap = optionalStorageMap.get();
            }
        }

        String key = path.substring(trailingIndex);
        if (storageMap == this) {
            Object value = map.get(key);
            if (!overwrite && value instanceof StorageMap presentMap) {
                return presentMap;
            }
            StorageMap result = new MemoryStorageMap(this);
            map.put(key, result);
            return result;
        }
        return storageMap.setMap(key, overwrite);
    }

    @Override
    public Optional<StorageList> getList(String path) {
        Object value = get(path);
        if (value instanceof StorageList storageList) {
            return Optional.of(storageList);
        }
        return Optional.empty();
    }

    @Override
    public StorageList setList(String path) {
        return setList(path, OVERWRITE_SET_LIST_DEFAULT);
    }

    @Override
    public StorageList setList(String path, boolean overwrite) {
        int leadingIndex = -1;
        int trailingIndex;

        StorageMap storageMap = this;
        char separator = root.getPathSeparator();
        while ((leadingIndex = path.indexOf(separator, trailingIndex = leadingIndex + 1)) != -1) {
            String node = path.substring(trailingIndex, leadingIndex);
            Optional<StorageMap> optionalStorageMap = storageMap.getMap(node);
            if (optionalStorageMap.isEmpty()) {
                storageMap = storageMap.setMap(node); // create missing
            } else {
                storageMap = optionalStorageMap.get();
            }
        }

        String key = path.substring(trailingIndex);
        if (storageMap == this) {
            Object value = map.get(key);
            if (!overwrite && value instanceof StorageList presentList) {
                return presentList;
            }
            StorageList result = new MemoryStorageList(this);
            map.put(key, result);
            return result;
        }
        return storageMap.setList(key, overwrite);
    }

    @Override
    public void set(String path, Object value) {
        set(path, value, OVERWRITE_SET_DEFAULT);
    }

    @Override
    public void set(String path, Object value, boolean overwrite) {
        int leadingIndex = -1;
        int trailingIndex;

        StorageMap storageMap = this;
        char separator = root.getPathSeparator();
        while ((leadingIndex = path.indexOf(separator, trailingIndex = leadingIndex + 1)) != -1) {
            String node = path.substring(trailingIndex, leadingIndex);
            Optional<StorageMap> optionalStorageMap = storageMap.getMap(node);
            if (optionalStorageMap.isEmpty()) {
                if (value == null) {
                    return; // null is used for deleting a value, but the path doesn't exist
                }
                storageMap = storageMap.setMap(node);
            } else {
                storageMap = optionalStorageMap.get();
            }
        }

        String key = path.substring(trailingIndex);
        if (storageMap == this) {
            if (value == null) {
                map.remove(key);
            } else if (overwrite) {
                map.put(key, value);
            } else {
                map.putIfAbsent(key, value);
            }
        } else {
            storageMap.set(key, value, overwrite);
        }
    }

    @Override
    public Object get(String path) {
        int leadingIndex = -1;
        int trailingIndex;

        StorageMap storageMap = this;
        char separator = root.getPathSeparator();
        while ((leadingIndex = path.indexOf(separator, trailingIndex = leadingIndex + 1)) != -1) {
            String node = path.substring(trailingIndex, leadingIndex);
            Optional<StorageMap> optionalSection = storageMap.getMap(node);
            if (optionalSection.isEmpty()) {
                return null;
            }
            storageMap = optionalSection.get();
        }

        String key = path.substring(trailingIndex);
        if (storageMap == this) {
            return map.get(key);
        }
        return storageMap.get(key);
    }

    @Override
    public Optional<UUID> getUuid(String path) {
        Object value = get(path);
        if (value instanceof UUID uuid) {
            return Optional.of(uuid);
        } else if (value instanceof String string) {
            try {
                return Optional.of(UUID.fromString(string));
            } catch (IllegalArgumentException e) {
                // ignore
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Boolean> getBoolean(String path) {
        Object value = get(path);
        if (value instanceof Boolean bool) {
            return Optional.of(bool);
        } else if (value instanceof String string) {
            if ("true".equalsIgnoreCase(string)) {
                return Optional.of(true);
            } else if ("false".equalsIgnoreCase(string)) {
                return Optional.of(false);
            }
        }
        return Optional.empty(); // null or not a boolean
    }

    @Override
    public Optional<String> getString(String path) {
        Object value = get(path);
        if (value instanceof String string) {
            return Optional.of(string);
        } else if (value instanceof Number number) {
            return Optional.of(number.toString());
        } else if (value instanceof Boolean bool) {
            return Optional.of(bool.toString());
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Double> getDouble(String path) {
        Object value = get(path);
        if (value instanceof Number number) {
            return Optional.of(number.doubleValue());
        } else if (value instanceof String string) {
            try {
                return Optional.of(Double.parseDouble(string));
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return Optional.empty(); // null or not a double
    }

    @Override
    public Optional<BigDecimal> getBigDecimal(String path) {
        Object value = get(path);
        if (value instanceof BigDecimal bigDecimal) {
            return Optional.of(bigDecimal);
        } else if (value instanceof String string) {
            try {
                return Optional.of(new BigDecimal(string));
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return Optional.empty(); // null or not a big decimal
    }

    @Override
    public Optional<BigInteger> getBigInteger(String path) {
        Object value = get(path);
        if (value instanceof BigInteger bigInteger) {
            return Optional.of(bigInteger);
        } else if (value instanceof String string) {
            try {
                return Optional.of(new BigInteger(string));
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return Optional.empty(); // null or not a big integer
    }

    @Override
    public Optional<Float> getFloat(String path) {
        Object value = get(path);
        if (value instanceof Number number) {
            return Optional.of(number.floatValue());
        } else if (value instanceof String string) {
            try {
                return Optional.of(Float.parseFloat(string));
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return Optional.empty(); // null or not a float
    }

    @Override
    public Optional<Long> getLong(String path) {
        Object value = get(path);
        if (value instanceof Number number) {
            return Optional.of(number.longValue());
        } else if (value instanceof String string) {
            try {
                return Optional.of(Long.parseLong(string));
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return Optional.empty(); // null or not a long
    }

    @Override
    public Optional<Short> getShort(String path) {
        Object value = get(path);
        if (value instanceof Number number) {
            return Optional.of(number.shortValue());
        } else if (value instanceof String string) {
            try {
                return Optional.of(Short.parseShort(string));
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return Optional.empty(); // null or not a short
    }

    @Override
    public Optional<Integer> getInteger(String path) {
        Object value = get(path);
        if (value instanceof Number number) {
            return Optional.of(number.intValue());
        } else if (value instanceof String string) {
            try {
                return Optional.of(Integer.parseInt(string));
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return Optional.empty(); // null or not an integer
    }

    @Override
    public Optional<Byte> getByte(String path) {
        Object value = get(path);
        if (value instanceof Number number) {
            return Optional.of(number.byteValue());
        } else if (value instanceof String string) {
            try {
                return Optional.of(Byte.parseByte(string));
            } catch (NumberFormatException e) {
                // ignore
            }
        }
        return Optional.empty(); // null or not a byte
    }

    @Override
    public Optional<Character> getCharacter(String path) {
        Optional<String> optionalValue = getString(path);

        if (optionalValue.isEmpty()) {
            return Optional.empty(); // not a string
        }

        String value = optionalValue.get();

        if (value.isEmpty()) {
            return Optional.empty(); // string is empty
        }

        return Optional.of(value.charAt(0));
    }

    @Override
    public void forEach(BiConsumer<String, Object> action) {
        map.forEach(action);
    }

}
