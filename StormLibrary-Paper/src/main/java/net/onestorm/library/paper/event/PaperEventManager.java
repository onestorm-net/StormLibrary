package net.onestorm.library.paper.event;

import net.onestorm.library.event.EventHandler;
import net.onestorm.library.event.EventManager;
import net.onestorm.library.event.build.EventHandlerBuilder;
import net.onestorm.library.event.build.GenericEventHandlerBuilder;
import net.onestorm.library.paper.event.option.PaperEventOption;
import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.EventExecutor;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class PaperEventManager implements EventManager<Event>, EventExecutor, Listener {

    private final Plugin plugin;
    private final Server server;

    public PaperEventManager(Plugin plugin) {
        this.plugin = plugin;
        this.server = plugin.getServer();
    }

    @Override
    public EventHandlerBuilder<Event> register(Class<Event> eventClass) {
        return new GenericEventHandlerBuilder<>(this, eventClass);
    }

    @Override
    public void unregisterBind(Object listener) {
        // todo
    }

    @Override
    public CompletableFuture<Event> callEvent(Event event) {
        server.getPluginManager().callEvent(event);
        return CompletableFuture.completedFuture(event);
    }

    @Override
    public void register(EventHandler<Event> eventHandler) {
        boolean ignoreCancelled = eventHandler.getOptionOrDefault(PaperEventOption.IGNORE_CANCELLED);
        EventPriority eventPriority = eventHandler.getOptionOrDefault(PaperEventOption.PRIORITY);
        Class<? extends Event> eventClass = eventHandler.getEventClass();

        server.getPluginManager().registerEvent(eventClass, this, eventPriority, this, plugin, ignoreCancelled);
    }

    @Override
    public void unregister(EventHandler<Event> eventHandler) {
        // todo
    }

    @Override
    public void execute(@NotNull Listener listener, @NotNull Event event) throws EventException {
        // todo
    }
}
