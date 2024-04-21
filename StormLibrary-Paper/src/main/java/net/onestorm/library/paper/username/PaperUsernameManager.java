package net.onestorm.library.paper.username;

import net.onestorm.library.username.UsernameManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.Plugin;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class PaperUsernameManager implements UsernameManager {

    private final Plugin plugin;

    public PaperUsernameManager(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public CompletableFuture<Optional<String>> getUsername(UUID uuid) {
        OfflinePlayer player = plugin.getServer().getOfflinePlayer(uuid);

        return CompletableFuture.completedFuture(Optional.ofNullable(player.getName()));
    }

    @Override
    public CompletableFuture<Optional<UUID>> getUuid(String username) {
        OfflinePlayer player = plugin.getServer().getOfflinePlayerIfCached(username);

        if (player == null) {
            return CompletableFuture.completedFuture(Optional.empty());
        }

        return CompletableFuture.completedFuture(Optional.of(player.getUniqueId()));
    }
}
