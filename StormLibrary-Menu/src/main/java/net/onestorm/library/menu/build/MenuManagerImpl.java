package net.onestorm.library.menu.build;

import net.onestorm.library.menu.Menu;
import net.onestorm.library.menu.exception.BuildMenuException;
import net.onestorm.library.storage.StorageMap;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class MenuManagerImpl implements MenuManager {

    private final Map<String, MenuBuilder> builderMap = new HashMap<>();

    @Override
    public Menu createMenu(StorageMap configuration) {
        Optional<String> optionalName = configuration.getString("name");

        if (optionalName.isEmpty()) {
            throw new BuildMenuException("Missing \"name\" key in configuration");
        }

        String name = optionalName.get();

        MenuBuilder builder = builderMap.get(name);
        if (builder == null) {
            throw new IllegalArgumentException("Could not find a builder for Menu: " + name);
        }

        Menu menu;
        try {
            menu = builder.build(configuration);
        } catch (Exception e) {
            throw new BuildMenuException("Uncaught error while building an Action: " + name, e);
        }
        return menu;
    }

    @Override
    public void registerBuilder(MenuBuilder builder) {
        String name = builder.getName().toLowerCase(Locale.ENGLISH);

        if (builderMap.containsKey(name)) {
            throw new IllegalArgumentException("There already exists a builder with this name: " + name);
        }
        builderMap.put(name, builder);
    }

    @Override
    public void unregisterBuilder(MenuBuilder builder) {
        String name = builder.getName().toLowerCase(Locale.ENGLISH);
        builderMap.remove(name);
    }
}
