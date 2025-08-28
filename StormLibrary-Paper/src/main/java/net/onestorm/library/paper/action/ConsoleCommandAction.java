package net.onestorm.library.paper.action;

import net.onestorm.library.paper.context.PlayerContext;
import org.bukkit.Server;
import org.bukkit.command.CommandException;
import org.bukkit.plugin.Plugin;

import java.util.logging.Level;

public class ConsoleCommandAction extends PaperAction {

    private final Plugin plugin;
    private final Server server;
    private final String command;

    public ConsoleCommandAction(Plugin plugin, String command) {
        this.plugin = plugin;
        this.server = plugin.getServer();
        this.command = command;
    }

    @Override
    public void execute(PlayerContext context) {
        try {
            server.dispatchCommand(server.getConsoleSender(), command);
        } catch (CommandException e) {
            plugin.getLogger().log(Level.WARNING, "Console command in action \"" + command + "\" failed", e);
        }
    }
}
