package net.onestorm.library.paper.action;

import net.onestorm.library.action.Action;
import net.onestorm.library.common.factory.BuildException;
import net.onestorm.library.common.factory.Builder;
import net.onestorm.library.common.factory.context.BuildContext;
import net.onestorm.library.common.factory.context.StorageBuildContext;
import net.onestorm.library.storage.StorageMap;
import org.bukkit.plugin.Plugin;

import java.util.Optional;

public class CommandActionBuilder implements Builder<Action> {

    private static final String BUILDER_NAME = "command";

    private final Plugin plugin;

    public CommandActionBuilder(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return BUILDER_NAME;
    }

    @Override
    public Action build(BuildContext context) {
        if (!(context instanceof StorageBuildContext storageContext)) {
            throw new BuildException("Context is not an instance of StorageBuildContext.");
        }

        StorageMap storage = storageContext.getStorage();

        String command = storage.getString("command")
            .orElseThrow(() -> new BuildException("Missing \"command\" key in storage while building: " + BUILDER_NAME));
        Optional<String> executor = storage.getString("executor");

        if (executor.isPresent() && executor.get().equalsIgnoreCase("console")) {
            return new ConsoleCommandAction(plugin, command);
        } else {
            return new PlayerCommandAction(command);
        }

    }
}
