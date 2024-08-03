package net.onestorm.library.menu.element.build;

import net.onestorm.library.menu.element.Element;
import net.onestorm.library.storage.StorageMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ElementFactoryImpl implements ElementFactory {
    private final Map<String, ElementBuilder> builderMap = new HashMap<>();
    private final Logger logger;

    public ElementFactoryImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void registerBuilder(ElementBuilder builder) {
        String name = builder.getName().toLowerCase(Locale.ENGLISH);

        if (builderMap.containsKey(name)) {
            throw new IllegalArgumentException("There already exists a builder with this name: " + name);
        }
        builderMap.put(name, builder);
    }

    @Override
    public void unregisterBuilder(ElementBuilder builder) {
        String name = builder.getName().toLowerCase(Locale.ENGLISH);
        builderMap.remove(name);
    }

    @Override
    public List<Element> createElements(StorageMap storage) {
        List<Element> result = new ArrayList<>();

        storage.forEach((key, value) -> {

            if (!(value instanceof StorageMap storageMap)) {
                return; // continue forEach
            }

            Optional<String> optionalName = storageMap.getString("name");

            if (optionalName.isEmpty()) {
                logger.warning("Missing \"name\" key in configuration for Element: " + key);
                return; // continue forEach
            }

            String name = optionalName.get();

            ElementBuilder builder = builderMap.get(name);
            if (builder == null) {
                logger.warning("Could not find a builder for Element: " + name);
                return; // continue forEach
            }

            Element element;
            try {
                element = builder.build(storageMap);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Uncaught error while building an Element: " + name, e);
                return; // continue forEach
            }

            result.add(element);
        });

        return result;
    }
}
