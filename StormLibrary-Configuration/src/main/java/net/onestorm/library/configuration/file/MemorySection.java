package net.onestorm.library.configuration.file;

import net.onestorm.library.configuration.Configuration;
import net.onestorm.library.configuration.Section;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class MemorySection implements Section {

    private static final char SEPARATOR = '.';
    private static final boolean SET_OVERWRITE_DEFAULT = true;

    protected Map<String, Object> map = new ConcurrentHashMap<>();

    private final Configuration root;
    private final Section parent;

    public MemorySection() {
        if (!(this instanceof Configuration)) {
            throw new IllegalStateException("Not a root Section");
        }

        this.root = (Configuration) this;
        this.parent = null;
    }

    public MemorySection(Section parent) {
        this.root = parent.getRoot();
        this.parent = parent;
    }

    @Override
    public Configuration getRoot() {
        return root;
    }

    @Override
    public Section getParent() {
        return parent;
    }

    @Override
    public Optional<Section> getSection(String path) {
        Object value = get(path);
        if (value instanceof Section section) {
            return Optional.of(section);
        }
        return Optional.empty();
    }

    @Override
    public Section createSection(String path) {
        int leadingIndex = -1;
        int trailingIndex;

        Section section = this;
        while ((leadingIndex = path.indexOf(SEPARATOR, trailingIndex = leadingIndex + 1)) != -1) {
            String node = path.substring(trailingIndex, leadingIndex);
            Optional<Section> optionalSection = section.getSection(node);
            if (optionalSection.isEmpty()) {
                section = section.createSection(node);
            } else {
                section = optionalSection.get();
            }
        }

        String key = path.substring(trailingIndex);
        if (section == this) {
            Section result = new MemorySection(this);
            map.put(key, result);
            return result;
        }
        return section.createSection(key);

    }

    @Override
    public void set(String path, Object value) {
        set(path, value, SET_OVERWRITE_DEFAULT);
    }

    @Override
    public void set(String path, Object value, boolean overwrite) {
        int leadingIndex = -1;
        int trailingIndex;

        Section section = this;
        while ((leadingIndex = path.indexOf(SEPARATOR, trailingIndex = leadingIndex + 1)) != -1) {
            String node = path.substring(trailingIndex, leadingIndex);
            Optional<Section> optionalSection = section.getSection(node);
            if (optionalSection.isEmpty()) {
                if (value == null) {
                    return; // null is used for deleting a value, but the path doesn't exist
                }
                section = section.createSection(node);
            } else {
                section = optionalSection.get();
            }
        }

        String key = path.substring(trailingIndex);
        if (section == this) {
            if (value == null) {
                map.remove(key);
            } else if (overwrite) {
                map.put(key, value);
            } else {
                map.putIfAbsent(key, value);
            }
        } else {
            section.set(key, value, overwrite);
        }
    }

    @Override
    public Map<String, Object> asMap() {
        return Collections.unmodifiableMap(map);
    }

    @Override
    public List<Object> asList() {
        return List.copyOf(map.values());
    }

    @Override
    public Optional<List<Object>> getList(String path) {
        Object value = get(path);
        if (value instanceof Section section) {
            return Optional.of(section.asList());
        }
        if (value instanceof List) {
            return Optional.of((List<Object>) value);
        }
        return Optional.empty();
    }

    @Override
    public Object get(String path) {
        int leadingIndex = -1;
        int trailingIndex;

        Section section = this;
        while ((leadingIndex = path.indexOf(SEPARATOR, trailingIndex = leadingIndex + 1)) != -1) {
            String node = path.substring(trailingIndex, leadingIndex);
            Optional<Section> optionalSection = section.getSection(node);
            if (optionalSection.isEmpty()) {
                return null;
            }
            section = optionalSection.get();
        }

        String key = path.substring(trailingIndex);
        if (section == this) {
            return map.get(key);
        }
        return section.get(key);
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
}
