package net.onestorm.library.paper;

import org.bukkit.plugin.java.JavaPlugin;

public class StormLibrary extends JavaPlugin {

    private static StormLibrary instance;

    @Override
    public void onLoad() {
        instance = this;
    }

    public static StormLibrary getInstance() {
        if (instance == null) {
            throw new IllegalStateException("The StormLibrary plugin is not loaded!");
        }
        return instance;
    }
}
