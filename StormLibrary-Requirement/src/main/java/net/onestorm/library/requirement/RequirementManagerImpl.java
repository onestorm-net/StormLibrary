package net.onestorm.library.requirement;

import net.onestorm.library.requirement.implementation.FallbackRequirement;
import net.onestorm.library.storage.StorageMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequirementManagerImpl implements RequirementManager {

    private final Map<String, RequirementBuilder> builderMap = new HashMap<>();
    private final Logger logger;

    public RequirementManagerImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void registerBuilder(RequirementBuilder builder) {
        String name = builder.getName().toLowerCase(Locale.ENGLISH);

        if (builderMap.containsKey(name)) {
            throw new IllegalArgumentException("There already exists a builder with this name: " + name);
        }
        builderMap.put(name, builder);
    }

    @Override
    public void unregisterBuilder(RequirementBuilder builder) {
        String name = builder.getName().toLowerCase(Locale.ENGLISH);
        builderMap.remove(name);
    }

    @Override
    public List<Requirement> getRequirements(StorageMap storage) {
        List<Requirement> result = new ArrayList<>();

        storage.forEach((key, value) -> {

            if (!(value instanceof StorageMap storageMap)) {
                return; // continue forEach
            }

            Optional<String> optionalName = storageMap.getString("type");
            if (optionalName.isEmpty()) {
                logger.warning("Missing \"type\" key in configuration for Requirement: " + key);
                result.add(new FallbackRequirement());
                return; // continue forEach
            }

            String name = optionalName.get().toLowerCase(Locale.ENGLISH);

            RequirementBuilder builder = builderMap.get(name);
            if (builder == null) {
                logger.warning("Could not find a builder for Requirement: " + name);
                result.add(new FallbackRequirement());
                return; // continue forEach
            }

            Requirement requirement;
            try {
                requirement = builder.build(storage);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Uncaught error while building a Requirement: " + name, e);
                result.add(new FallbackRequirement());
                return; // continue forEach
            }
            result.add(requirement);
        });

        return result;
    }
}
