package net.onestorm.library.action;

import net.onestorm.library.configuration.Section;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ActionManagerImpl implements ActionManager {

    private final Map<String, ActionBuilder> builderMap = new HashMap<>();
    private final Logger logger;

    public ActionManagerImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void registerActionBuilder(ActionBuilder builder) {
        String name = builder.getName().toLowerCase(Locale.ENGLISH);

        if (builderMap.containsKey(name)) {
            throw new IllegalArgumentException("There already exists a builder with this name: " + name);
        }
        builderMap.put(name, builder);
    }

    @Override
    public void unregisterActionBuilder(ActionBuilder builder) {
        String name = builder.getName().toLowerCase(Locale.ENGLISH);
        builderMap.remove(name);
    }

    @Override
    public List<Action> getActions(Section configuration) {
        List<Action> result = new ArrayList<>();

        configuration.asMap().keySet().forEach(key -> {
            Optional<Section> optionalSection = configuration.getSection(key);

            if (optionalSection.isEmpty()) {
                return; // continue forEach
            }

            Section section = optionalSection.get();

            Optional<String> optionalName = section.getString("type");

            if (optionalName.isEmpty()) {
                logger.warning("Missing \"type\" key in configuration for Action: " + key);
                return; // continue forEach
            }

            String name = optionalName.get();

            ActionBuilder builder = builderMap.get(name);
            if (builder == null) {
                logger.warning("Could not find a builder for Action: " + name);
                return; // continue forEach
            }

            Action action;
            try {
                action = builder.build(section);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Uncaught error while building an Action: " + name, e);
                return; // continue forEach
            }

            result.add(action);
        });

        return result;
    }
}
